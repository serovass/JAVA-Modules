package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import java.sql.*;
import java.util.Optional;
import java.util.ArrayList;
import com.zaxxer.hikari.HikariDataSource;

public class ChatroomsRepositoryJdbcImpl implements ChatroomsRepository {

    private static final String CHATROOMS_QUERY =
        "SELECT * FROM Chat.Chatroom WHERE Id=?";

    private final HikariDataSource dataSource;
    private final UsersRepositoryJdbcImpl usersRepo;

    public ChatroomsRepositoryJdbcImpl(
        HikariDataSource dataSource, UsersRepositoryJdbcImpl usersRepo) {

        this.dataSource = dataSource;
        this.usersRepo = usersRepo;
    }

    @Override
    public Optional<Chatroom> findById(Integer id) {

        Chatroom result = null;
        ResultSet resultSet = null;

        try {
            try (Connection connection = this.dataSource.getConnection();){
                PreparedStatement query =
                    connection.prepareStatement(CHATROOMS_QUERY);

                query.setInt(1, id);

                resultSet = query.executeQuery();

                if (resultSet.next()) {

                    result = new Chatroom(
                            resultSet.getInt("Id"),
                            resultSet.getString("RoomName"),
                            this.usersRepo.findById(resultSet.getInt("RoomOwner")).
                                orElse(null),
                            null
                    );
     
                }
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return (Optional.ofNullable(result));
    }
}
