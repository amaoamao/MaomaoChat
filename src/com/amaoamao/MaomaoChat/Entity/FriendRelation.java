package com.amaoamao.MaomaoChat.Entity;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-4-16.
 */
public class FriendRelation {

    private long relation_id;

    private User user1;

    private User user2;

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public long getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(long relation_id) {
        this.relation_id = relation_id;
    }
}
