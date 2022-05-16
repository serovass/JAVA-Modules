package school21.spring.service.repositories;

import school21.spring.service.models.*;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private static final String  SELECT_BY_ID_QUERY =
            "SELECT * FROM service.user WHERE userId = ?";

    private static final String  SELECT_BY_EMAIL_QUERY =
            "SELECT * FROM service.user WHERE email = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM service.user";

    private static final String UPDATE_QUERY =
            "UPDATE service.user SET email = ? WHERE userId = ?";

    private static final String INSERT_QUERY =
            "INSERT INTO service.user (email) VALUES (?)";

    private static final String DELETE_QUERY =
            "DELETE FROM service.user WHERE userId = ?";

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<User> findById(Long id) {

        User result = null;
        try{

            try (Connection connection = this.dataSource.getConnection();
                 PreparedStatement query =
                         connection.prepareStatement(SELECT_BY_ID_QUERY);) {

                query.setLong(1, id);
                ResultSet resultSet = query.executeQuery();

                if (resultSet.next()){
                    result = new User(
                            resultSet.getLong("userId"),
                            resultSet.getString("email"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }

    public Optional<User> findByEmail(String email) {
        User result = null;
        try{

            try (Connection connection = this.dataSource.getConnection();
                 PreparedStatement query =
                         connection.prepareStatement(SELECT_BY_EMAIL_QUERY);) {

                query.setString(1, email);
                ResultSet resultSet = query.executeQuery();

                if (resultSet.next()){
                    result = new User(
                            resultSet.getLong("userId"),
                            resultSet.getString("email"));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(result);
    }


    @Override
    public List<User> findAll() {

        List<User> result = new ArrayList<>();

        try{
            try (Connection connection = this.dataSource.getConnection();
                 Statement query = connection.createStatement();) {

                ResultSet resultSet = query.executeQuery(
                        SELECT_ALL_QUERY);

                while (resultSet.next()) {
                    result.add(new User(
                            resultSet.getLong("userId"),
                            resultSet.getString("email")
                    ));
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    public void update(User user){

        if (!findById(user.getUserId()).isPresent())
            return;
        System.out.println(user);
        try{
            try (Connection connection = this.dataSource.getConnection();
                 PreparedStatement query =
                         connection.prepareStatement(UPDATE_QUERY);) {

                query.setString(1, user.getEmail());
                query.setLong(2, user.getUserId());
                query.execute();

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void save(User user){

        try{
            try (Connection connection = this.dataSource.getConnection();
                 PreparedStatement query =
                         connection.prepareStatement(INSERT_QUERY);) {

                query.setString(1, user.getEmail());

                query.execute();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete (Long id){

        if (!findById(id).isPresent())
            return;

        try{
            try (Connection connection = this.dataSource.getConnection();
                 PreparedStatement query =
                         connection.prepareStatement(DELETE_QUERY);) {

                query.setLong(1, id);

                query.execute();
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}