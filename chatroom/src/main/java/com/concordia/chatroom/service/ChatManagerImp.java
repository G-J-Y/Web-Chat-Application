package com.concordia.chatroom.service;

import com.concordia.chatroom.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatManagerImp implements ChatManager {
    private LinkedList<Message> messagesStore = new LinkedList<>();
    public LinkedList<Message> getMessagesStore(){
        return this.messagesStore;
    }
    @Override
    public void postMessage(String user, String message) {
        if(user == "") user = "anonymous";
        messagesStore.add(new Message(user,message));
    }

    @Override
    public List<Message> listMessages(String start, String end) {
        if(start == "" && end == "") return messagesStore;
        if(start == "") start = "1900/00/00 00:00:00";
        if(end == "") end = "2050/12/30 00:00:00";
        LinkedList<Message> filteredMessages = new LinkedList<>();
        //Here need to Lambda expression
           for(Message message : messagesStore){
               if(message.compareTo((Object)start) >= 0 && message.compareTo((Object)end) <= 0)
                   filteredMessages.add(message);
           }
        return filteredMessages;
    }

    @Override
    public void clearChat(String start,String end) {
        if(start == "" && end == ""){
            messagesStore.clear();
            return;
        }
        if(start == "") start = "1900/01/01 00:00:00";
        if(end == "") end = "2080/01/01 00:00:00";
        LinkedList<Message> filteredMessages = new LinkedList<>();
        for(Message message : messagesStore){
            if(message.compareTo((Object)start) < 0 || message.compareTo((Object)end) > 0)
                filteredMessages.add(message);
        }
        messagesStore = filteredMessages;
    }
}
