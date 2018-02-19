package io.pax.forum.ws;

import io.pax.forum.dao.CommentDao;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.User;
import io.pax.forum.domain.jdbc.SimpleComment;
import io.pax.forum.domain.jdbc.SimpleTopic;
import io.pax.forum.domain.jdbc.SimpleUser;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by AELION on 19/02/2018.
 */
@Path("comments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentWS {

    @GET
    public List<Comment> getComments() throws SQLException {
        CommentDao dao = new CommentDao();
        return dao.listComments();
    }
    @POST
/*return future wallet with an id*/
    public SimpleComment createComment(SimpleComment comment /* sent wallet has no id*/) {
       SimpleUser user = comment.getUser();
        Topic topic = comment.getTopic();

        if (user == null ) {
            throw new NotAcceptableException("406 no user id sent");
        }
        if (comment.getName().length() < 2) {
            throw new NotAcceptableException("406 : wallet name must have at least 2 letters ");

        }

        try {

            int id = new CommentDao().createComment(user.getId(),topic.getId(),comment.getName());
            User boundUser = comment.getUser();

            SimpleUser simpleUser = new SimpleUser(boundUser.getId(),boundUser.getName());


            return new SimpleComment(id, comment.getName(),simpleUser);
        } catch (SQLException e) {

            throw new ServerErrorException("Database error, sorry", 500);
        }

    }
}