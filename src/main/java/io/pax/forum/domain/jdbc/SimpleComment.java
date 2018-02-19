package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Comment;

/**
 * Created by AELION on 16/02/2018.
 */
public class SimpleComment implements Comment {
    int id;
    String name;
    SimpleUser user;
    SimpleTopic topic;

    public SimpleComment() {
    }

    public SimpleComment(int id, String name, SimpleUser simpleUser) {
        this.id = id;
        this.name = name;
        this.user= simpleUser;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    @Override
    public SimpleTopic getTopic() {
        return topic;
    }

    public void setTopic(SimpleTopic topic) {
        this.topic = topic;
    }

    public SimpleComment(int id, String name, SimpleUser user, SimpleTopic topic) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.topic = topic;
    }

    public SimpleComment(int id, String name) {
        this.id = id;
        this.name = name;

    }





    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }


    public SimpleUser getSimpleUser() {
        return getSimpleUser();
    }
}
