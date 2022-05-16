package edu.school21.chat.repositories;

import java.util.Optional;
import java.sql.SQLException;

import edu.school21.chat.models.User;

public interface UsersRepository {

    Optional<User> findById(Integer id) throws SQLException;

}
