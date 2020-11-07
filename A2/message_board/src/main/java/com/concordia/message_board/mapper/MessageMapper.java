package com.concordia.message_board.mapper;

import com.concordia.message_board.entities.Post;

import java.sql.*;
import java.util.List;

public class MessageMapper {
    Connection conn;

    public void clearTable() throws SQLException {
        Statement stm = conn.createStatement();
        stm.execute("DROP TABLE IF EXISTS post;");
        System.out.println("Table deleted");
    }

    public void insertIntoDB(Post post) throws SQLException {

        String postId = post.getPostId();
        String userId = post.getUserId();
        String title = post.getTitle();
        String postDate = post.getPostDate();
        String content = post.getContent();
        Blob attachment = post.getAttachment();

        Statement stm = conn.createStatement();
        //stm.execute("CREATE TABLE IF NOT EXISTS Message(PostId Int PRIMARY KEY, UserId int, Title VARCHAR(255), PostDate DATETIME, Content VARCHAR(255))");
        String query = "INSERT INTO post(postid, userid, title, postdate, content, attachment) value(?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,postId);
        statement.setString(2,userId);
        statement.setString(3,title);
        statement.setString(4,postDate);
        statement.setString(5,content);
        //statement.setBlob(6,);

        // make changes in database
        statement.execute();
        // close the connections
        statement.close();
        //conn.close();
        // debug
        System.out.println("Insert Message to Database");
    }

    public List<Post> getAllPost() throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery("select count(*) from post");
        if (result.next()){

        }

        //conn.close();
        return null;
    }

    private Post extractPostFromResultSet(ResultSet rs) throws SQLException {

        Post post = new Post();

        post.setPostId( rs.getString("postId") );
        post.setUserId( rs.getString("userid") );
        post.setTitle( rs.getString("title") );
        post.setPostDate( rs.getString("postDate") );
        post.setContent( rs.getString("content") );
        post.setAttachment( rs.getBlob("attachment") );

        return post;
    }
}
