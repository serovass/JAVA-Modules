package edu.school21.chat.models;

import java.util.List;

public class Chatroom {

    private final Integer id;
    private String roomName;
    private User roomOwner;
    private List<Message> messageList;

    public Chatroom(Integer id, String roomName, User roomOwner, List<Message> messageList) {
        this.id = id;
        this.roomName = roomName;
        this.roomOwner = roomOwner;
        this.messageList = messageList;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "{" +
                "Id=" + id +
                " RoomName='" + roomName + '\'' +
                " RoomOwner=" + roomOwner +
                " MessageList=" + messageList +
                "}";
    }

    public Integer getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public User getRoomOwner() {
        return roomOwner;
    }

    public List<Message> getMessageList() {
        return messageList;
    }


    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setRoomOwner(User roomOwner) {
        this.roomOwner = roomOwner;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

}