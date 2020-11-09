package com.concordia.message_board.controller;

import com.concordia.message_board.entities.Post;
import com.concordia.message_board.mapper.MessageMapper;
import com.concordia.message_board.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class BoardController {
    @Autowired
    private PostManager postManager;
    private MessageMapper messageMapper;

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
        if(postManager.authentication(username, password)) return "postMessage";

        return "error";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(@RequestParam("title") String title,
                       @RequestParam("content") String content,
                       @RequestParam("file") MultipartFile file,
                       Model model) throws Exception{

        messageMapper = new MessageMapper();
        String date = messageMapper.getPostTime();
        String postID = UUID.randomUUID().toString();
        InputStream in = null;
        byte[] bytes = null;
        Blob blob = null;
        /*if (file.isEmpty()){
            model.addAttribute("uploadMessage", "The file is empty!"); //error
            return "postMessage";
        }*/
        if (!file.isEmpty())
            //in = file.getInputStream();
            bytes = file.getBytes();
            blob = new SerialBlob(bytes);

        Post post = new Post("1",postID,title,content,date, blob);

        messageMapper.insertIntoDB(post);
        //get All post from DB
        List<Post> posts = messageMapper.getAllPost();
        model.addAttribute("posts", posts);

        return "viewMessage";
    }

    @GetMapping("/allPosts")
    public String allPosts(Model model) throws Exception {

        List<Post> posts = messageMapper.getAllPost();

        model.addAttribute("posts", posts);

        return "viewMessage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(@RequestParam("file") MultipartFile file, Model model){
        if (file.isEmpty()){
            model.addAttribute("uploadMessage", "The file is empty!");
            return "postMessage";
        }
        try{
            byte[] bytes = file.getBytes();
            //Path path = Paths.get("E:\\fileUpload/" + file.getOriginalFilename());
            Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\SOEN387\\A2\\fileUpload/" + file.getOriginalFilename());
            Files.write(path, bytes);
            model.addAttribute("uploadMessage", "success");

        }catch (Exception e){
            e.printStackTrace();
        }
        return "postMessage";
    }
}
