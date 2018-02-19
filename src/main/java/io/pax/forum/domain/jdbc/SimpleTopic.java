package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;

import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class SimpleTopic implements Topic {


    int id;
    String name;
    SimpleUser user;
    SimpleComment comments;

    public SimpleTopic(int id, String name) {
        this.name = name;
        this.id = id;

    }

    public SimpleTopic() {
    }

    public SimpleTopic(int id, String name, SimpleUser simpleUser) {
        this.name = name;
        this.id = id;
        this.user = simpleUser;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public User getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<? extends Comment> getComments() {

        return (List<? extends Comment>) comments;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SimpleWallet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
