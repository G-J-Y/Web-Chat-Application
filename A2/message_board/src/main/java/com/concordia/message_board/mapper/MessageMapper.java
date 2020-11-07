package com.concordia.message_board.mapper;

import com.concordia.message_board.entities.Post;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageMapper {
    Connection conn;

    public void clearTable() throws SQLException {
        Statement stm = conn.createStatement();
        stm.execute("DROP TABLE IF EXISTS Message;");
        System.out.println("Table deleted");
    }

    public void insertIntoDB(Post post) throws SQLException {

        String postId = post.getPostId();
        String userId = post.getUserId();
        String title = post.getTitle();
        String postDate = post.getPostDate();
        String modifiedDate = post.getModifiedDate();
        String content = post.getContent();

        Statement stm = conn.createStatement();
        //stm.execute("CREATE TABLE IF NOT EXISTS Message(PostId Int PRIMARY KEY, UserId int, Title VARCHAR(255), PostDate DATETIME, ModifiedDate DATETIME, Content VARCHAR(255))");
        String query = "INSERT INTO Message(postid, userid, title, postdate, modifieddate, content) value(?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,postId);
        statement.setString(2,userId);
        statement.setString(3,title);
        statement.setString(4,postDate);
        statement.setString(5,modifiedDate);
        statement.setString(6,content);

        // make changes in database
        statement.execute();
        // close the connections
        statement.close();
        conn.close();
        // debug
        System.out.println("Insert Message to Database");
    }


}
