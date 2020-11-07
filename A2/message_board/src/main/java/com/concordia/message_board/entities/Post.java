package com.concordia.message_board.entities;

import java.util.Arrays;

public class Post {
    private String userId;
    private String postId;
    private String title;
    private String content;
    private String postDate;
    private String modifiedDate;
    private String[] hashTage;
    private AttachedPost attachedPost;

    public Post() {
    }

    public Post(String userId, String postId, String title, String content, String postDate, String modifiedDate, String[] hashTage, AttachedPost attachedPost) {
        this.userId = userId;
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.modifiedDate = modifiedDate;
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

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String postDate) {
        this.modifiedDate = modifiedDate;
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
                ", date='" + postDate + '\'' +
                ", hashTage=" + Arrays.toString(hashTage) +
                ", attachedPost=" + attachedPost +
                '}';
    }
}
