package edu.school21.chat.repositories;

import java.util.Optional;
import java.sql.SQLException;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Message;

public interface MessagesRepository {

    Optional<Message> findById(Integer id) throws SQLException;
    public void save(Message message) throws SQLException, NotSavedSubEntityException;

}
