package com.concordia.message_board.entities;

import java.util.Arrays;

public class Post {
    private String userId;
    private String postId;
    private String content;
    private String date;
    private String[] hashTage;
    private AttachedPost attachedPost;

    public Post() {
    }

    public Post(String userId, String postId, String content, String date, String[] hashTage, AttachedPost attachedPost) {
        this.userId = userId;
        this.postId = postId;
        this.content = content;
        this.date = date;
        this.hashTage = hashTage;
        this.attachedPost = attachedPost;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String[] getHashTage() {
        return hashTage;
    }

    public void setHashTage(String[] hashTage) {
        this.hashTage = hashTage;
    }

    public AttachedPost getAttachedPost() {
        return attachedPost;
    }

    public void setAttachedPost(AttachedPost attachedPost) {
        this.attachedPost = attachedPost;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userId='" + userId + '\'' +
                ", postId='" + postId + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", hashTage=" + Arrays.toString(hashTage) +
                ", attachedPost=" + attachedPost +
                '}';
    }
}
