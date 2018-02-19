package io.pax.forum.dao;

import io.pax.forum.domain.jdbc.SimpleTopic;
import io.pax.forum.domain.Topic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by AELION on 16/02/2018.
 */
public class TopicDao {
    JdbcConnector connector = new JdbcConnector();

    public List<Topic> listTopics() throws SQLException {

        List<Topic> topics = new ArrayList<>();
        Connection conn = this.connector.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM topic");

        while (rs.next()) {
            String name = rs.getString("name");
            int id = rs.getInt("id");

            topics.add(new SimpleTopic(id, name));

        }

        rs.close();
        stmt.close();
        conn.close();

        return topics;

    }




    public int  createTopic(int userId, String name) throws SQLException {
        String query = "INSERT INTO topic (name,user_Id) VALUES (?,?)";
        System.out.println(query);

        Connection conn = this.connector.getConnection();
        PreparedStatement statements = conn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        statements.setString (1,name);
        statements.setInt(2, userId);
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


    }

}

