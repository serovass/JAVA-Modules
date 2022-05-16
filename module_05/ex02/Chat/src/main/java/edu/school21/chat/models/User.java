package edu.school21.chat.models;

import java.util.List;

public class User {

    private final Integer id;
    String login;
    String password;
    List<Chatroom> userChatroomList;
    List<Chatroom> createdChatroomList;


    public User(Integer id, String login, String password, List<Chatroom> createdChatroomList, List<Chatroom> userChatroomList) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChatroomList = createdChatroomList;
        this.userChatroomList = userChatroomList;
    }

    @Override
    public String toString() {
        return "{" +
                "Id=" + id +
                " Login='" + login + '\'' +
                " Password='" + password + '\'' +
                " UserChatroomList=" + userChatroomList +
                " CreatedChatroomList=" + createdChatroomList +
                "}";
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Chatroom> getCreatedChatroomList() {
        return createdChatroomList;
    }

    public List<Chatroom> getUserChatroomList() {
        return userChatroomList;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedChatroomList(List<Chatroom> createdChatroomList) {
        this.createdChatroomList = createdChatroomList;
    }

    public void setUserChatroomList(List<Chatroom> userChatroomList) {
        this.userChatroomList = userChatroomList;
    }
}