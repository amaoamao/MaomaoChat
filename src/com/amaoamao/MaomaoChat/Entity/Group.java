package com.amaoamao.MaomaoChat.Entity;

import java.util.List;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-4-16.
 */
public class Group {

    private long group_id;

    private String name;

    private String group_avatar;

    private String description;

    private List<GroupMember> members;

    public long getGroup_id() {
        return group_id;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup_avatar() {
        return group_avatar;
    }

    public void setGroup_avatar(String group_avatar) {
        this.group_avatar = group_avatar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }
}
