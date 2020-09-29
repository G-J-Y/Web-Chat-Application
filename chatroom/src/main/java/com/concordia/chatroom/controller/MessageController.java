package com.concordia.chatroom.controller;

import com.concordia.chatroom.entity.Message;
import com.concordia.chatroom.service.ChatManager;
import com.concordia.chatroom.service.ChatManagerImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class MessageController {
    @Autowired
    ChatManagerImp chatManager;

    @RequestMapping(value = "/getMessages")
    public String getMessages(@RequestParam(value = "from",required = false) String from,
                              @RequestParam(value = "to",required = false) String to,
                              @RequestParam(value = "format",required = false) String format,
                              Map<String,Object> map){
        map.put("recording",new Message("User","Hello World"));
        chatManager.listMessages(from,to);
        chatManager.postMessage("Fred","I love coding");
        chatManager.postMessage("Taylor","Welcome to NewYork");
        map.put("messages",chatManager.getMessagesStore());
        return "chatRecording";
    }
    @RequestMapping(value = {"","/","/chatroom"},method = RequestMethod.POST)
    public String chatRoom(@RequestParam(value = "user",required = false) String user,
                           @RequestParam(value = "message",required = true) String message,
                           Map<String,Object> map, HttpServletRequest request,HttpServletRequest response){
                            Cookie[] cookies = request.getCookies();
                            for(Cookie coo: cookies){
                                System.out.println(coo.getName()+":"+coo.getValue());
                            }
                            if(user != ""){
                                Cookie cookie = new Cookie("user",user);
                                cookie.setMaxAge(60*60);
                            }
                            if(user == "") user = "anonymous";
                            chatManager.postMessage(user,message);
                            map.put("messages",chatManager.getMessagesStore());
        return "chatRoom";
    }
}
