package com.amaoamao.MaomaoChat.Entity;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-4-16.
 */
public class Channel {

    private long channel_id;

    private String name;

    private String channel_avatar;

    private String description;

    private List<ChannelMember> members;

    public long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(long channel_id) {
        this.channel_id = channel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChannel_avatar() {
        return channel_avatar;
    }

    public void setChannel_avatar(String channel_avatar) {
        this.channel_avatar = channel_avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ChannelMember> getMembers() {
        return members;
    }

    public void setMembers(List<ChannelMember> members) {
        this.members = members;
    }
}
