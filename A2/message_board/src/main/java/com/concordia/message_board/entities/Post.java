package com.concordia.message_board.entities;

import java.io.InputStream;
import java.sql.Blob;
import java.util.Arrays;

public class Post {
    private String userId;
    private String postId;
    private String title;
    private String content;
    private String postDate;
    //private Blob attachBlob;
    private boolean edited = false;
    private Attachment attachment;

    public Post() {
    }

    public Post(String userId,String postId, String title, String content, String postDate, Attachment attachment){
        this.userId = userId;
        this.title = title;
        this.postId = postId;
        this.content = content;
        this.postDate = postDate;
        //this.attachBlob = attachBlob;
        this.attachment = attachment;
    }

    public Post(Post copyPost){
        this.userId = copyPost.userId;
        this.postId = copyPost.postId;
        this.title = copyPost.title;
        this.content = copyPost.content;
        this.postDate = copyPost.postDate;
        //this.attachBlob = copyPost.attachBlob;
    }

    public boolean isEdited() {
        return edited;
    }

    public void setEdited(boolean edited) {
        this.edited = edited;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    /*public Blob getAttachBlob(){
        return attachBlob;
    }*/

    /*public void setAttachBlob(Blob attachBlob){
        this.attachBlob = attachBlob;
    }*/


    public Attachment getAttachment() {
        return attachment;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", content='" + content + '\'' +
                ", date='" + postDate + '\'' +
                ", attachedPost=" +
                '}';
    }
}
