package edu.school21.chat.repositories;

import java.util.Optional;
import java.sql.SQLException;

import edu.school21.chat.models.Chatroom;

public interface ChatroomsRepository {

    Optional<Chatroom> findById(Integer id) throws SQLException;

}
