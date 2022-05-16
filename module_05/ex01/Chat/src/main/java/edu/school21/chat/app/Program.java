package edu.school21.chat.app;

import edu.school21.chat.repositories.*;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.nio.charset.StandardCharsets;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Program {

    public static void main(
            String[] args) throws FileNotFoundException, SQLException {

        Integer id = 0;

        HikariDataSource dataSource = configureHikariDataSource();

        executeSchemaScripts(dataSource);

        UsersRepositoryJdbcImpl usersRepo =
            new UsersRepositoryJdbcImpl(dataSource);

        ChatroomsRepositoryJdbcImpl roomsRepo =
            new ChatroomsRepositoryJdbcImpl(dataSource, usersRepo);

        MessagesRepositoryJdbcImpl messagesRepo =
            new MessagesRepositoryJdbcImpl(dataSource, usersRepo, roomsRepo);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a message ID:");

        try{
            id = scanner.nextInt();
        }
        catch( InputMismatchException e){
            System.out.println("Input not a number!");
            System.exit(-1);
        }
        System.out.println("Message:\n" + messagesRepo.findById(id).orElse(null));
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
