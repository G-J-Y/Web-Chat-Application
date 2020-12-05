package com.concordia.message_board.entities;

import java.util.ArrayList;
import java.util.List;

public class ConcordiaUser {
    private List<String> conNamesList;
    //extra info from database and list all posts for concordia
    private List<Post> conPostsList;

    public ConcordiaUser() {
        conNamesList = new ArrayList<>();
        conPostsList = new ArrayList<>();
    }

    public List<Post> getConPostsList() {
        return conPostsList;
    }

    public void setConPostsList(List<Post> conPostsList) {
        this.conPostsList = conPostsList;
    }

    public List<String> getConNamesList() {
        return conNamesList;
    }

    public void setConNamesList(List<String> conNamesList) {
        this.conNamesList = conNamesList;
    }

    public void addUserIdToList(String userId){
        conNamesList.add(userId);
    }
    public void addPostToList(Post post){
        conPostsList.add(post);
    }
    @Override
    public String toString() {
        String li = "";
        for(String str:conNamesList){
            li += " "+str;
        }
        li +="following is data for concordia:/n";
        for(Post post:conPostsList){
            li +="/n";
            li += post;
        }
        return li;
    }
}
