package io.pax.forum.domain;

/**
 * Created by AELION on 16/02/2018.
 */
public interface Comment {
    int getId();
    default User getUser(){
        return null;}

    default Topic getTopic(){
        return null;}

    String getName();
}

