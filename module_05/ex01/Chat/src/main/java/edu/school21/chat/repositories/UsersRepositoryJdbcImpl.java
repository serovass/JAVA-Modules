package edu.school21.chat.repositories;

import edu.school21.chat.models.*;

import java.sql.*;
import java.util.Optional;
import java.util.ArrayList;
import com.zaxxer.hikari.HikariDataSource;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String USER_QUERY =
        "SELECT * FROM Chat.User WHERE Id=?";

    private final HikariDataSource dataSource;

    public UsersRepositoryJdbcImpl(HikariDataSource dataSource) {

        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Integer id) {

        User result = null;
        ResultSet resultSet = null;

        try {
            try (Connection connection = this.dataSource.getConnection();){
                PreparedStatement query =
                    connection.prepareStatement(USER_QUERY);

                query.setInt(1, id);

                resultSet = query.executeQuery();

                if (resultSet.next()) {

                    result = new User(
                        resultSet.getInt("Id"),
                        resultSet.getString("Login"),
                        resultSet.getString("Password"),
                        null,
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
