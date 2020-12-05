package com.concordia.message_board.entities;

import java.util.ArrayList;
import java.util.List;

public class CompUser {
    private List<String> encsNamesList;
    private List<String> conNamesList;
    private List<String> compNamesList;

    private List<Post> encsPostsList;
    private List<Post> conPostsList;
    private List<Post> compPostsList;

    public CompUser() {
        compNamesList = new ArrayList<>();
        compPostsList = new ArrayList<>();
    }
    //-------------------setter and getter for post---------------


    public List<Post> getEncsPostsList() {
        return encsPostsList;
    }

    public void setEncsPostsList(List<Post> encsPostsList) {
        this.encsPostsList = encsPostsList;
    }

    public List<Post> getConPostsList() {
        return conPostsList;
    }

    public void setConPostsList(List<Post> conPostsList) {
        this.conPostsList = conPostsList;
    }

    public List<Post> getCompPostsList() {
        return compPostsList;
    }

    public void setCompPostsList(List<Post> compPostsList) {
        this.compPostsList = compPostsList;
    }

    //----------------------------------------------------
    //--------------------add post---------------------

    public void addPostToList(Post post){
       // System.out.println(compPostsList.size()+" :<<<<*************************comp List size");
        encsPostsList.add(post);
        compPostsList.add(post);
        conPostsList.add(post);
    }
    //--------------------------------------------------
    public List<String> getEncsNamesList() {
        return encsNamesList;
    }

    public void setEncsNamesList(List<String> encsNamesList) {
        this.encsNamesList = encsNamesList;
    }

    public List<String> getConNamesList() {
        return conNamesList;
    }

    public void setConNamesList(List<String> conNamesList) {
        this.conNamesList = conNamesList;
    }

    public List<String> getCompNamesList() {
        return compNamesList;
    }

    public void setCompNamesList(List<String> compNamesList) {
        this.compNamesList = compNamesList;
    }
    public void addUserIdToList(String userId){
        encsNamesList.add(userId);
        conNamesList.add(userId);
        compNamesList.add(userId);
    }
    @Override
    public String toString() {
        String li = "The following is the users in comp:";
        for(String str:compNamesList){
            li += " "+str;
        }
        li +="following is data for comp:/n";
        for(Post post:compPostsList){
            li +="/n";
            li += post;
        }
        return li;
    }
}
