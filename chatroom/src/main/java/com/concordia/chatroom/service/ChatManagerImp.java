package com.concordia.chatroom.service;

import com.concordia.chatroom.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Service
public class ChatManagerImp implements ChatManager {
    private ArrayList<Message> messagesStore = new ArrayList<>();
    public ArrayList<Message> getMessagesStore(){
        return this.messagesStore;
    }
    @Override
    public void postMessage(String user, String message) {
        /*if(user == "")
            user = "anonymous";*/
        messagesStore.add(new Message(user,message));
    }

    @Override
    public List<Message> listMessages(String start, String end) {
        //if(start == null && end == null) return messagesStore;
        //if(start == null) start = "1900/00/00 00:00:00";
        //if(end == null) end = "2050/12/30 00:00:00";
        ArrayList<Message> filteredMessages = new ArrayList<>();
        //Here need to Lambda expression
           for(Message message : messagesStore){
               if(message.compareTo((Object)start) >= 0 && message.compareTo((Object)end) <= 0)
                   filteredMessages.add(message);
           }
        return filteredMessages;
    }

    @Override
    public void clearChat(String start,String end) {
        /*if(start == null && end == null){
            messagesStore.clear();
            return;
        }
        if(start == null) start = "1900/01/01 00:00:00";
        if(end == null) end = "2080/01/01 00:00:00";*/
        ArrayList<Message> filteredMessages = new ArrayList<>();
        for(Message message : messagesStore){
            if(message.compareTo((Object)start) < 0 || message.compareTo((Object)end) > 0)
                filteredMessages.add(message);
        }
        messagesStore = filteredMessages;
    }

    public boolean validateFormat(String dateStr){
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try{
            Date date = format.parse(dateStr);
            //return true;
        }catch(Exception e){
            System.out.println("false");
            return false;
        }

        int[] ans = new int[6];
        Date current = new Date();
        //int year,month,day,hour,minute,second ;
        ans[0] = Integer.valueOf(dateStr.substring(0, 4));
        ans[1] = Integer.valueOf(dateStr.substring(5, 7));
        ans[2] = Integer.valueOf(dateStr.substring(8, 10));
        ans[3] = Integer.valueOf(dateStr.substring(11, 13));
        ans[4] = Integer.valueOf(dateStr.substring(14, 16));
        ans[5] = Integer.valueOf(dateStr.substring(17, 19));

        Calendar calender = Calendar.getInstance();
        //int year = calender.getWeekYear();
        if(ans[0] < 0 || ans[1] < 1 || ans[1] > 12 || ans[2] < 0 || ans[2] > 31 ||
                ans[3] < 0 || ans[3] > 23 || ans[4] < 0 || ans[4] > 59 ||ans[5] < 0 || ans[5] >59){
            //throw new DateTimeException("Invalid Date Time");
            return false;
        }
        return true;
    }
}
