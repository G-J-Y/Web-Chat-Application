package com.concordia.message_board.controller;

import com.concordia.message_board.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class BoardController {
    @Autowired
    private PostManager postManager;

    @Value("${display.number}")
    private String number;

    @GetMapping("/")
    public String logIn(){
        return "login";
    }

    @PostMapping("/authentication")
    public String authentication(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 Map<String,Object> map, HttpSession session){

        System.out.println("Number---->"+number);
       if(postManager.authentication(username, password)) return "Ok";

        return "error";
    }
}
