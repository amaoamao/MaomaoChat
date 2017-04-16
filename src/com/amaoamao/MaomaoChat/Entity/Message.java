package com.amaoamao.MaomaoChat.Entity;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-4-16.
 */
public class Message {
    private long message_id;

    private long time;

    private int messageType;

    private String content;

    private User fromUser;

    public long getMessage_id() {
        return message_id;
    }

    public void setMessage_id(long message_id) {
        this.message_id = message_id;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
