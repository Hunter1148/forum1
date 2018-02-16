package io.pax.forum.ws;

import io.pax.forum.dao.TopicDao;

import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.SimpleTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */

    @Path("topics")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public class TopicWs {

        @GET
        public List<Topic> getTopics() throws SQLException {
            TopicDao dao = new TopicDao();
            return dao.listTopics();
        }
    @POST
/*return future wallet with an id*/
    public SimpleTopic createTopic(SimpleTopic topic /* sent wallet has no id*/) {
        User user = topic.getUser();
        if (user == null) {
            throw new NotAcceptableException("406 no user id sent");
        }
        if (topic.getName().length() < 2) {
            throw new NotAcceptableException("406 : wallet name must have at least 2 letters ");

        }

        try {
            int id = new TopicDao().createTopic(user.getId(), topic.getName());
            User boundUser = topic.getUser();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(),boundUser.getName());
            return new SimpleTopic(id, topic.getName(),simpleUser);
        } catch (SQLException e) {

            throw new ServerErrorException("Database error, sorry", 500);
        }

    }
    }



