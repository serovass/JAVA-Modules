package edu.school21.chat.app;

import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Program {

    public static void main(
            String[] args) throws FileNotFoundException, SQLException {

        HikariDataSource dataSource = configureHikariDataSource();

        executeSchemaScripts(dataSource);

        UsersRepositoryJdbcImpl usersRepo =
                new UsersRepositoryJdbcImpl(dataSource);

        ChatroomsRepositoryJdbcImpl roomsRepo =
                new ChatroomsRepositoryJdbcImpl(dataSource, usersRepo);

        try{

            User creator = new User(5, "Bill", "bill",
                    new ArrayList(), new ArrayList());

            User author = creator;
            Chatroom room = new Chatroom(
                    5, "Fakes", creator, new ArrayList());
            Message message = new Message(
                    null, author, room, "Hello!", LocalDateTime.now());
            MessagesRepository messagesRepository =
                    new MessagesRepositoryJdbcImpl(
                            dataSource, usersRepo, roomsRepo);
            messagesRepository.save(message);
            System.out.println(message.getId());
        }
        catch(NotSavedSubEntityException e){
            System.out.println(e.getMessage());
        }

    }

    private static HikariDataSource configureHikariDataSource(){

        HikariConfig hikaConfig = new HikariConfig();

        hikaConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikaConfig.addDataSourceProperty("prepStmtCacheSize", "250");
        hikaConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikaConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikaConfig.setUsername("postgres");
        hikaConfig.setPassword("123");

        return new HikariDataSource(hikaConfig);
    }

    private static void executeSchemaScripts(
            HikariDataSource dataSource) throws FileNotFoundException {

        executeResourceScripts(dataSource, "/schema.sql");
        executeResourceScripts(dataSource, "/data.sql");
    }

    private static void executeResourceScripts(
        HikariDataSource dataSource, String fileName) throws FileNotFoundException {

        String queryString = getInputStreamString(
                getResourceAsStream(fileName));

        try {
            try (Connection connection = dataSource.getConnection();){
                connection.createStatement().execute(queryString);
            }
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    private static String getInputStreamString(InputStream stream) {

        try (InputStreamReader streamReader =
                    new InputStreamReader(stream, StandardCharsets.UTF_8);

             BufferedReader reader = new BufferedReader(streamReader)) {

            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    private static InputStream getResourceAsStream(String fileName) {

        InputStream inputStream = File.class.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IllegalArgumentException("file " + fileName + " is not found!");
        } else {
            return inputStream;
        }

    }
}
