package com.concordia.message_board.entities;

import java.util.ArrayList;
import java.util.List;

public class CompUser {
    private List<String> encsNamesList;
    private List<String> conNamesList;
    private List<String> compNamesList;

    public CompUser() {
        compNamesList = new ArrayList<>();
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
        String li = "";
        for(String str:compNamesList){
            li += " "+str;
        }
        return li;
    }
}
