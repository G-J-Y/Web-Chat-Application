package com.concordia.message_board.entities;

import java.sql.Blob;

public class Attachment {
    //public String path;
    public String postId;
    public String userId;
    public String fileName;
    public String fileType;
    public int fileSize;
    public Blob blob;

    public Attachment() {
    }

    public Attachment(String postId, String userId, String fileName, String fileType, int fileSize, Blob blob) {
        //this.path = path;
        this.postId = postId;
        this.userId = userId;
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.blob = blob;
    }

    /*public String getPath() {
        return path;
    }*/

    /*public void setPath(String path) {
        this.path = path;
    }*/

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

    public Blob getBlob(){
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    @Override
    public String toString() {
        return "AttachedPost{" +
                ", postId='" + postId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
