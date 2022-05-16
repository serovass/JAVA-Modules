package school21.spring.service.repositories;

import java.sql.*;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import school21.spring.service.models.User;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {

    private static final String  SELECT_BY_ID_QUERY =
            "SELECT * FROM service.user WHERE userId = ?";

    private static final String  SELECT_BY_EMAIL_QUERY =
            "SELECT * FROM service.user WHERE userEmail = ?";

    private static final String SELECT_ALL_QUERY =
            "SELECT * FROM service.user";

    private static final String UPDATE_QUERY =
            "UPDATE service.user SET userEmail = ? WHERE userId = ?";

    private static final String INSERT_QUERY =
            "INSERT INTO service.user (userEmail) VALUES (?)";

    private static final String DELETE_QUERY =
            "DELETE FROM service.user WHERE userId = ?";

    private JdbcTemplate template;

    public UsersRepositoryJdbcTemplateImpl(DataSource dataSource) {

        this.template = new JdbcTemplate(dataSource);
    }

    private RowMapper<User> userEntityMappeer =
            (resultSet, rowNum) ->
                    new User(resultSet.getLong("userid"),
                            resultSet.getString("email"));

    @Override
    public Optional<User> findById(Long id) {

        List<User> users =
                template.query(SELECT_BY_ID_QUERY, userEntityMappeer, id);

        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users.get(0));
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {

        List<User> users =
                template.query(SELECT_BY_EMAIL_QUERY, userEntityMappeer, email);

        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users.get(0));
        }
    }

    @Override
    public List<User> findAll() {

        return template.query(SELECT_ALL_QUERY, userEntityMappeer);
    }

    @Override
    public void save(User user) {

        template.update(INSERT_QUERY, user.getEmail());
    }

    @Override
    public void update(User user) {

        template.update(UPDATE_QUERY, user.getEmail(), user.getUserId());
    }

    @Override
    public void delete(Long id) {

        template.update(DELETE_QUERY, id);
    }
}

