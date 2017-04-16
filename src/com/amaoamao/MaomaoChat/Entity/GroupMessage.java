package com.amaoamao.MaomaoChat.Entity;

/**
 * Copyright (c) 2017 Peter Mao). All rights reserved.
 * Created by mao on 17-4-16.
 */
public class GroupMessage extends Message {
    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
