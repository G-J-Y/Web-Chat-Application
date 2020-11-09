package com.concordia.message_board.mapper;

import com.concordia.message_board.entities.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessageMapper {

//    private String jdbcName ="com.mysql.cj.jdbc.Driver";
//    private Connection conn;
//
//    public MessageMapper(){
//    }
//
//    public Connection getCon() throws Exception{
//        Class.forName(jdbcName);
//        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/concordia?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", "root","");
//        return conn;
//    }
//
//    public void clearTable() throws Exception {
//        conn = getCon();
//        Statement stm = conn.createStatement();
//        stm.execute("DROP TABLE IF EXISTS post;");
//        System.out.println("Table deleted");
//    }
//
//    public void insertIntoDB(Post post) throws Exception {
//        conn = getCon();
//
//        String postId = post.getPostId();
//        String userId = post.getUserId();
//        String title = post.getTitle();
//        String postDate = post.getPostDate();
//        String content = post.getContent();
//        Blob attachment = post.getAttachment();
//
//        //stm.execute("CREATE TABLE IF NOT EXISTS Message(PostId Int PRIMARY KEY, UserId int, Title VARCHAR(255), PostDate DATETIME, Content VARCHAR(255))");
//       String query = "INSERT INTO post(postid, userid, title, postdate, content, attachment) value(?,?,?,?,?,?)";
//        PreparedStatement statement = conn.prepareStatement(query);
//
//        statement.setString(1,postId);
//        statement.setString(2,userId);
//        statement.setString(3,title);
//        statement.setString(4,postDate);
//        statement.setString(5,content);
//        statement.setBlob(6,attachment);
//
//        // make changes in database
//        statement.execute();
//        // close the connections
//        statement.close();
//        conn.close();
//        // debug
//        System.out.println("Insert Message to Database");
//    }
//
//    public List<Post> getAllPost() throws Exception {
//        conn = getCon();
//        List<Post> allPost = new ArrayList<>();
//        Statement stm = conn.createStatement();
//        ResultSet result = stm.executeQuery("select * from post");
//
//        while (result.next()){
//            Post post = extractPostFromResultSet(result);
//            allPost.add(post);
//        }
//        conn.close();
//        return allPost;
//    }
//    // get User's Posts
//    public List<Post> getUserPost() throws Exception {
//        conn = getCon();
//        List<Post> userPost = new ArrayList<>();
//        Statement stm = conn.createStatement();
//        ResultSet result = stm.executeQuery("select * from post where userId = 1");
//
//        while (result.next()){
//            Post post = extractPostFromResultSet(result);
//            userPost.add(post);
//        }
//        conn.close();
//        return userPost;
//    }
//
//    private Post extractPostFromResultSet(ResultSet rs) throws SQLException {
//
//        Post post = new Post();
//        post.setPostId( rs.getString("postId") );
//        post.setUserId( rs.getString("userId") );
//        post.setTitle( rs.getString("title") );
//        post.setPostDate( rs.getString("postDate") );
//        post.setContent( rs.getString("content") );
//        post.setAttachment( rs.getBlob("attachment") );
//        return post;
//    }

    public String getPostTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        return date;
    }

}
