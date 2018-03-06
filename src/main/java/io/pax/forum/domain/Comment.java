package io.pax.forum.domain;

/**
 * Created by AELION on 16/02/2018.
 */
public interface Comment {
    int getId();
    default User getUser(){
        return getUser();}

 /*   default Topic getTopic(){
        return getTopic();}*/

    String getName();
}

