package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message {

    private Integer id;
    private User author;
    private Chatroom room;
    private String message;
    private LocalDateTime timestamp;

    public Message(Integer id, User author, Chatroom room, String message, LocalDateTime timestamp) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.message = message;
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object e) {
        return super.equals(e);
    }

    @Override
    public String toString() {
        return "{" +
                "\nId=" + id +
                "\nAuthor=" + author +
                "\nRoom=" + room +
                "\nMessage='" + message + '\'' +
                "\nTimestamp=" + timestamp.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                "\n}";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getMessage() {
        return message;
    }

    public void setText(String Message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return timestamp;
    }

    public void setDate(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
