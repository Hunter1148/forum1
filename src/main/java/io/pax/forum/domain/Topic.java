package io.pax.forum.domain;

import io.pax.forum.domain.User;

import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public interface Topic {

    int getId();
    default User getUser(){
        return getUser() ;}

    String getName();
    List<? extends Comment> getComments();
}
