package io.pax.forum.domain;

import io.pax.forum.domain.User;

/**
 * Created by AELION on 16/02/2018.
 */
public interface Topic {

    int getId();
    default User getUser(){
        return null;}

    String getName();
}
