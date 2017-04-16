package com.amaoamao.MaomaoChat.Entity;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-4-16.
 */
public class ChannelMember {

    private long relation_id;

    private long channel_id;

    private User member;

    private boolean isOwner;

    public long getRelation_id() {
        return relation_id;
    }

    public void setRelation_id(long relation_id) {
        this.relation_id = relation_id;
    }

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public void setAdmin(boolean admin) {
    }

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }
}
