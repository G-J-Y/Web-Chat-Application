package com.concordia.message_board.entities;

import java.util.ArrayList;
import java.util.List;

public class SoenUser {
    private List<String> encsNamesList;
    private List<String> conNamesList;
    private List<String> soenNamesList;

    public SoenUser() {
        soenNamesList = new ArrayList<>();
    }

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

    public List<String> getSoenNamesList() {
        return soenNamesList;
    }

    public void addUserIdToList(String userId){
        encsNamesList.add(userId);
        conNamesList.add(userId);
        soenNamesList.add(userId);
    }
    @Override
    public String toString() {
        String li = "";
        for(String str:soenNamesList){
            li += " "+str;
        }
        return li;
    }
}
