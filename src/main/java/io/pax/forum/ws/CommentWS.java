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



/*return future wallet with an id*/

/**
 * SimpleComment createComment(void)
 * @return SimpleComment from scratch
 */
/**
 public SimpleComment createComment() throws SQLException {
 Boolean doNext = true;

 if(!doNext) {
 SimpleUser simpleUser = new SimpleUser(99, "Casper", null, null);
 SimpleComment simpleComment = new SimpleComment(99, "Commentaire de test", simpleUser);
 throw new NotAcceptableException("407: Le commentaire de : " + simpleUser.getName() + " est " + simpleComment.getName());
 } else {
 SimpleUser simpleUser = new SimpleUser(99, "Casper", null, null);
 SimpleComment simpleComment = new SimpleComment(99, "Commentaire de test", simpleUser);
 System.out.println("******** Retour de méthode createComment *************");
 return simpleComment;
 }
 }
 **/


@POST
//return future comment with an id
public Comment createComment(SimpleComment comment) { //sent comment, has no id
    Boolean doNext = false;
    User user = comment.getUser();
    Topic topic =  comment.getTopic();

    if (user == null) {
        //400x: navigator sent wrong informations
        throw new NotAcceptableException("No user id sent");

    }

    if (topic == null) {
        //400x: navigator sent wrong informations
        throw new NotAcceptableException("No topic id sent");

    }

    if (comment.getName().length() < 5) {
        throw new NotAcceptableException("406: Comment name must have at least 5 letters");
    }


        try {
            int id = new CommentDao().createComment(user.getId(), topic.getId(), comment.getName());

            User boundUser = comment.getUser();
            Topic boundTopic = comment.getTopic();
            SimpleUser simpleUser = new SimpleUser(boundUser.getId(), boundUser.getName());
            SimpleTopic simpleTopic = new SimpleTopic(boundTopic.getId(), boundTopic.getName());

            return new SimpleComment(id, comment.getName(), simpleUser, simpleTopic);

        } catch (SQLException e) {
            throw new ServerErrorException("Database error, sorry", 500);
          }

}
}