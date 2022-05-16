package edu.school21.chat.repositories;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.*;

import java.sql.*;
import java.util.Optional;
import com.zaxxer.hikari.HikariDataSource;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private static final String MESSAGE_QUERY =
        "SELECT * FROM Chat.Message WHERE Id=?";

    private final HikariDataSource dataSource;

    ChatroomsRepositoryJdbcImpl roomsRepo;
    UsersRepositoryJdbcImpl usersRepo;

    public MessagesRepositoryJdbcImpl(
            HikariDataSource dataSource,
            UsersRepositoryJdbcImpl usersRepo,
            ChatroomsRepositoryJdbcImpl roomsRepo) {

        this.roomsRepo = roomsRepo;
        this.usersRepo = usersRepo;
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Integer id) {

        Message result = null;
        ResultSet resultSet = null;

        try {
            try (Connection connection = this.dataSource.getConnection();){
                PreparedStatement query =
                    connection.prepareStatement(MESSAGE_QUERY);

                query.setInt(1, id);

                resultSet = query.executeQuery();

                if (resultSet.next()) {

                    result = new Message(
                        resultSet.getInt("Id"),
                        this.usersRepo.findById(resultSet.getInt("Author")).
                            orElse(null),
                        this.roomsRepo.findById(resultSet.getInt("Room")).
                            orElse(null),
                        resultSet.getString("Message"),
                        resultSet.getTimestamp("timestamp").toLocalDateTime()
                    );

                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (Optional.ofNullable(result));
    }

    @Override
    public void save(Message message) throws SQLException, NotSavedSubEntityException {

        if(!usersRepo.findById(message.getAuthor().getId()).isPresent()){
            throw new NotSavedSubEntityException();
        }

        if (!roomsRepo.findById(message.getRoom().getId()).isPresent()){
            throw new NotSavedSubEntityException();
        }

        ResultSet resultSet = null;
        try {
            try (Connection connection = this.dataSource.getConnection();
                 PreparedStatement query =
                         connection.prepareStatement("INSERT INTO Chat.Message (Author, Room, Message, timestamp) VALUES (?, ?, ?, ?) RETURNING *")) {

                query.setLong(1, message.getAuthor().getId());
                query.setLong(2, message.getRoom().getId());
                query.setString(3, message.getMessage());
                query.setTimestamp(4, Timestamp.valueOf(message.getDate()));

                resultSet = query.executeQuery();
                resultSet.next();
                message.setId(resultSet.getInt("Id"));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
