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

    public SimpleComment(int id, String name, SimpleUser user, SimpleTopic topic) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.topic = topic;
    }

    public SimpleComment(int id, String name) {
        this.id = id;

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
