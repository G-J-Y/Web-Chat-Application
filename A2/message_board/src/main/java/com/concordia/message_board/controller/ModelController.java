package com.concordia.message_board.controller;

import com.concordia.message_board.entities.Post;
import com.concordia.message_board.mapper.MessageMapper;
import com.concordia.message_board.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.synchronoss.cloud.nio.multipart.util.IOUtils;


import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;
import java.util.UUID;

import static java.sql.JDBCType.BLOB;

@Controller
public class ModelController {

    @Autowired
    PostManager postManager;
    @PostMapping("/creatPost")
    public String createPost(Post post, HttpSession session){
        System.out.println(session.getAttribute("userId"));
        return "postMessage";
    }

    @GetMapping("/ok")
    public String ok(HttpSession session){
        System.out.println(session.getAttribute("userId"));
        return "Ok";
    }



    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public String post(@RequestParam(value = "title",required = false) String title,
                       @RequestParam("content") String content,
                       @RequestParam(value = "file",required = false) MultipartFile file,
                       Model model, HttpSession session) throws Exception {


        //messageMapper = new MessageMapper();
        String date = postManager.getPostTime();
        String postID = UUID.randomUUID().toString().substring(0,12);
        InputStream in = null;
        /*if (file.isEmpty()){
            model.addAttribute("uploadMessage", "The file is empty!"); //error
            return "postMessage";
        }*/
        if (!file.isEmpty())
            in = file.getInputStream();
        String userId = (String)session.getAttribute("userId");
        //----------------------------------------------------

        //--------------------------------------------------------

        Post post = new Post(userId,postID,title,content,date, (Blob) in);

        postManager.createPost(post);


        //get All post from DB
         List<Post> posts = postManager.getAllPost();
         model.addAttribute("posts", posts);

        return "viewMessage";
    }

    @PostMapping("/delete")
    public String deleteByUserId(String userId){
        postManager.deleteByPostId(userId);
        return "viewMessage";
    }

    @GetMapping("/allPosts")
    public String allPosts(Model model) throws Exception {

        //   List<Post> posts = messageMapper.getAllPost();

        //    model.addAttribute("posts", posts);

        return "viewMessage";
    }
}
