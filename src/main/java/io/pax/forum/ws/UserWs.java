package io.pax.forum.ws;

import io.pax.forum.dao.UserDao;
import io.pax.forum.domain.Comment;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.SimpleUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 15/02/2018.
 */

@Path("users")//chemin relatif pour avoir "/cryptos/api/users"
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserWs {

    @GET
    public List<User> getUsers() throws SQLException {
        UserDao dao = new UserDao();
        return dao.listUsers();
    }
    @GET
    @Path("{id}") //this is a pathparam
    public User getUser(@PathParam("id") int userId) throws SQLException {
        return new UserDao().findUserWithTopics(userId);


    }
    @POST
    public User createUser(SimpleUser user /* sent topic has no id*/) {
        String userName = user.getName();

        if (user == null) {
            throw new NotAcceptableException("406 no user name sent");
        }
        if (user.getName().length() < 2) {
            throw new NotAcceptableException("406 : user name must have at least 2 letters ");

        }

        try {
            int id = new UserDao().createUser(user.getName());

            List<Topic> topics = new ArrayList<>();
            List<Comment> comments = new ArrayList<>();

           return new SimpleUser(id,userName,topics, comments);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServerErrorException("Database error, sorry",500);
        }

    }

}

