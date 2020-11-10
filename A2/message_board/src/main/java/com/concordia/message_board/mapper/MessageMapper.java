package com.concordia.message_board.mapper;

import com.concordia.message_board.entities.Attachment;
import com.concordia.message_board.entities.Post;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MessageMapper {

    private String jdbcName ="com.mysql.cj.jdbc.Driver";
    private Connection conn;
    private String serverName = "root";
    private String password = "";

    public MessageMapper(){
    }

    public Connection getCon() throws Exception{
        Class.forName(jdbcName);
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/concordia?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT", serverName, password);
        return conn;
    }

    public void clearTable() throws Exception {
        conn = getCon();
        Statement stm = conn.createStatement();
        stm.execute("DROP TABLE IF EXISTS post;");
        System.out.println("Table deleted");
    }

    public void insertIntoDB(Post post) throws Exception {
        conn = getCon();

        String postId = post.getPostId();
        String userId = post.getUserId();
        String title = post.getTitle();
        String postDate = post.getPostDate();
        String content = post.getContent();
        Attachment attachment = post.getAttachment();

        //String query = "INSERT INTO post(postid, userid, title, postdate, content, attachment) value(?,?,?,?,?,?)";
        String query = "INSERT INTO post(postid, userid, title, postdate, content) value(?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,postId);
        statement.setString(2,userId);
        statement.setString(3,title);
        statement.setString(4,postDate);
        statement.setString(5,content);
        //statement.setObject(6,attachment);
        //statement.setBlob(6,attachment);
        statement.execute();
        statement.close();
        conn.close();
        //insert attachment
        if (attachment != null){
            insertAttach(attachment);
        }
        // debug
        System.out.println("Insert Post to Database");
    }

    public List<Post> getAllPost() throws Exception {
        conn = getCon();
        List<Post> allPost = new ArrayList<>();
        Statement stm = conn.createStatement();
        ResultSet result = stm.executeQuery("select * from post");

        while (result.next()){
            Post post = extractPostFromResultSet(result);
            allPost.add(post);
        }
        conn.close();
        return allPost;
    }
    // get User's Posts
    public List<Post> getUserPost(String id) throws Exception {
        conn = getCon();
        List<Post> userPost = new ArrayList<>();
        String query = "select * from post where userId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,id);
        ResultSet result = statement.executeQuery();

        while (result.next()){
            Post post = extractPostFromResultSet(result);
            userPost.add(post);
        }
        conn.close();
        return userPost;
    }

    private Post extractPostFromResultSet(ResultSet rs) throws SQLException {

        Post post = new Post();
        post.setPostId( rs.getString("postId") );
        post.setUserId( rs.getString("userId") );
        post.setTitle( rs.getString("title") );
        post.setPostDate( rs.getString("postDate") );
        post.setContent( rs.getString("content") );
        //post.setAttachBlob( rs.getBlob("attachment") );

        Attachment attachment = extractAttachFromResultSet(post.getPostId());
        post.setAttachment(attachment);

        return post;
    }

    private Attachment extractAttachFromResultSet(String postId) throws SQLException {

        String query = "select * from attach where attachPostId = ?";
        PreparedStatement statement = conn.prepareStatement(query);
        statement.setString(1,postId);
        ResultSet rs = statement.executeQuery();

        Attachment attachment = new Attachment();
        if (rs.next() == true) {
            attachment.setAttachId(rs.getString("attachId"));
            attachment.setPostId(rs.getString("attachPostId"));
            attachment.setFileName(rs.getString("fileName"));
            attachment.setFileType(rs.getString("fileType"));
            attachment.setFileSize(rs.getLong("fileSize"));
            attachment.setBlob(rs.getBlob("fileBlob"));
        }
        //post.setAttachBlob( rs.getBlob("attachment") );
        return attachment;
    }

    public void insertAttach(Attachment attachment) throws Exception {

        conn = getCon();
        String attachId = attachment.getAttachId();
        String postId = attachment.getPostId();
        String fileName = attachment.getFileName();
        String fileType = attachment.getFileType();
        long fileSize = attachment.getFileSize();
        Blob blob = attachment.getBlob();

        String query = "INSERT INTO attach(attachId, attachPostId, fileName, fileType, fileSize, fileBlob) value(?,?,?,?,?,?)";
        PreparedStatement statement = conn.prepareStatement(query);

        statement.setString(1,attachId);
        statement.setString(2,postId);
        statement.setString(3,fileName);
        statement.setString(4,fileType);
        statement.setLong(5,fileSize);
        statement.setBlob(6,blob);
        statement.execute();
        statement.close();
        conn.close();

    }

    public String getPostTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        return date;
    }

}
