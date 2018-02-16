package io.pax.forum.domain.jdbc;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;

import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */
public class SimpleUser implements User {
    int id;
    String name;
    List<Topic> topics;

    public SimpleUser(int userId, String userName, List<Topic> topics) {
        this.id = id;
        this.name = name;
        this.topics = topics;
    }

    public SimpleUser(int id, String name) {
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

    @Override
    public String toString() {
        return this.name + " : " + this.topics;
    }
    @Override
    public List<Topic> getTopics(){return topics;}
}
