package com.concordia.message_board.entities;

import java.util.ArrayList;
import java.util.List;

public class ConcordiaUser {
    private List<String> conNamesList;

    public ConcordiaUser() {
        conNamesList = new ArrayList<>();
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
    @Override
    public String toString() {
        String li = "";
        for(String str:conNamesList){
            li += " "+str;
        }
        return li;
    }
}
