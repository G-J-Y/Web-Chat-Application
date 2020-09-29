package com.concordia.chatroom.service;

import com.concordia.chatroom.entity.Message;

import java.util.List;

public interface ChatManager {
   void postMessage(String user, String message);

   List<Message> listMessages(String start, String end);

   void clearChat(String start,String end);

}
