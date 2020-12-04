package com.concordia.message_board.entities;

import java.util.ArrayList;
import java.util.List;

public class EncsUser {
    private List<String> encsNamesList;
    protected List<String> conNamesList;

    public EncsUser(){
        encsNamesList = new ArrayList<>();
    }

    public List<String> getEncsNamesList() {
        return encsNamesList;
    }

    public void setEncsNamesList(List<String> conNamesList) {
        this.conNamesList = conNamesList;
    }

    public void addUserIdToList(String userId){
        this.encsNamesList.add(userId);
        this.conNamesList.add(userId);
        this.toString();
    }

    @Override
    public String toString() {
        String li = "";
        for(String str:encsNamesList){
            li += " "+str;
        }
        return li;
    }
}
