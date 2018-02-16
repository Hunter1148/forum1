package io.pax.forum.dao;

import io.pax.forum.domain.Comment;
import io.pax.forum.domain.Topic;
import io.pax.forum.domain.jdbc.SimpleComment;
import io.pax.forum.domain.jdbc.SimpleTopic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */

    public class CommentDao {
        JdbcConnector connector = new JdbcConnector();

        public List<Comment> listComments() throws SQLException {

            List<Comment> comments = new ArrayList<>();
            Connection conn = this.connector.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM comment");

            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("id");

                comments.add(new SimpleComment(id,name));

            }

            rs.close();
            stmt.close();
            conn.close();

            return comments;

        }




        public int  createComment(int userId,int topicId, String name) throws SQLException {
            String query = "INSERT INTO comment (text,user_id,topic_id) VALUES (?,?,?)";
            System.out.println(query);

            Connection conn = this.connector.getConnection();
            PreparedStatement statements = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
            statements.setString (1,name);
            statements.setInt(2, userId);
            statements.setInt(3, topicId);
            statements.executeUpdate();


            //  int rows = statements.executeUpdate(query);
            // if (rows!= 1){
            //throw new SQLException("something wring happpened with "+query);
            //}
            ResultSet keys = statements.getGeneratedKeys();
            keys.next();


            int id = keys.getInt(1);

            statements.close();
            conn.close();
            return id;



        }

        public static void main(String[] args) throws SQLException {

            CommentDao dao = new CommentDao();
            dao.createComment(1,3,"lololololol");
        }

    }


