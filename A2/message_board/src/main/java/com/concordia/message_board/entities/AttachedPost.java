package com.concordia.message_board.entities;

public class AttachedPost {
    public String path;
    public String postId;
    public String userId;

    public AttachedPost() {
    }

    public AttachedPost(String path, String postId,String userId) {
        this.path = path;
        this.postId = postId;
        this.userId = userId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId(){
        return userId;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "AttachedPost{" +
                "path='" + path + '\'' +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
