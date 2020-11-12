package com.concordia.message_board.controller;

import com.concordia.message_board.entities.Attachment;
import com.concordia.message_board.entities.Post;
import com.concordia.message_board.mapper.MessageMapper;
import com.concordia.message_board.service.PostManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.synchronoss.cloud.nio.multipart.util.IOUtils;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import static java.sql.JDBCType.BLOB;

@Controller
public class ModelController {

    @Autowired
    PostManager postManager;
    MessageMapper messageMapper;

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
                       @RequestParam(value = "content") String content,
                       @RequestParam(value = "file",required = false) MultipartFile file,
                       Model model, HttpSession session) throws Exception {

        messageMapper = new MessageMapper();
        String date = messageMapper.getPostTime();
        String postID = UUID.randomUUID().toString().substring(0,12);
        String userId = (String)session.getAttribute("userId");

        Blob blob = null;
        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        Long fileSize = file.getSize();
        Attachment attachment = null;

        if (!file.isEmpty()) {
            //in = file.getInputStream();
            byte[] bytes = file.getBytes();
            blob = new SerialBlob(bytes);

            String attachId = UUID.randomUUID().toString().substring(0,12);
            attachment = new Attachment(attachId,postID,fileName,fileType,fileSize,blob);
        }


        Post post = new Post(userId,postID,title,content,date, attachment);
        messageMapper.insertIntoDB(post);

        //postManager.createPost(post);
        //List<Post> posts = postManager.getAllPost();

        //get current user's posts from DB
        List<Post> posts = messageMapper.getUserPost(userId);
        model.addAttribute("posts", posts);

        return "postMessage";
    }

    @RequestMapping(value = "/updatePost", method = RequestMethod.POST)
    public String repost(@RequestParam(value = "postId",required = false) String postId,
                         @RequestParam(value = "title",required = false) String title,
                         @RequestParam("content") String content,
                         @RequestParam(value = "file",required = false) MultipartFile file,
                         Model model, HttpSession session) throws Exception {

        messageMapper = new MessageMapper();
        String date = messageMapper.getPostTime();
        String userId = (String)session.getAttribute("userId");
        //get post from Database
        Post oldPost = messageMapper.extractSpecificPost(postId);
        oldPost.setTitle(title);
        oldPost.setPostDate(date);
        oldPost.setContent(content);

        if (!file.isEmpty()) {
            Boolean delete = messageMapper.deleteAttach(oldPost.getPostId());

            String fileName = file.getOriginalFilename();
            String fileType = file.getContentType();
            Long fileSize = file.getSize();
            byte[] bytes = file.getBytes();
            Blob blob = new SerialBlob(bytes);

            String attachId = UUID.randomUUID().toString().substring(0,12);
            Attachment attachment = new Attachment(attachId, oldPost.getPostId(), fileName, fileType, fileSize, blob);
            oldPost.setAttachment(attachment);
        }

        messageMapper.updatePost(oldPost);
        List<Post> posts = messageMapper.getUserPost(userId);
        model.addAttribute("posts", posts);

        return "postMessage";
    }

    @PostMapping("/delete")
    public String deleteByPostId(String postId){
        postManager.deleteByPostId(postId);
        return "redirect:/afterDelete";
    }

    @GetMapping("/allPosts")
    public String allPosts(Model model) throws Exception {

        messageMapper = new MessageMapper();
        List<Post> posts = messageMapper.getAllPost();
        model.addAttribute("posts", posts);

        return "viewMessage";
    }

    @GetMapping("/afterDelete")
    public String afterDelete(Model model,
                              HttpSession session) throws Exception {
        messageMapper = new MessageMapper();
        String userId = (String)session.getAttribute("userId");
        List<Post> posts = messageMapper.getUserPost(userId);
        model.addAttribute("posts", posts);
        return "postMessage";
    }

    @GetMapping("/back")
    public String back(Model model,
                              HttpSession session) throws Exception {
        messageMapper = new MessageMapper();
        String userId = (String)session.getAttribute("userId");
        List<Post> posts = messageMapper.getUserPost(userId);
        model.addAttribute("posts", posts);
        return "postMessage";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(@RequestParam(value = "postId",required = false) String postId,
                       @RequestParam(value = "file",required = false) Attachment file,
                       Model model, HttpSession session) throws Exception {


        Post oldPost = messageMapper.extractSpecificPost(postId);
        model.addAttribute("post",oldPost);
        return "editMessage";
    }

    @RequestMapping(value = "/file/{postId}", method = RequestMethod.GET)
    public String updateStudy(@PathVariable ("postId") String postId,
                              Model model,
                              HttpServletResponse response) throws Exception {

        messageMapper = new MessageMapper();
        Post post = messageMapper.extractSpecificPost(postId);
        //give a file name
        String filename = post.getAttachment().getFileName();
        // set contentType in response
        response.setContentType(post.getAttachment().getFileType());
        // set the download type
        response.setHeader("Content-Disposition", "attachment;filename= " + filename);
        // get a output stream from response
        ServletOutputStream out = response.getOutputStream();

        out.write(messageMapper.getAttachData(postId));
        out.flush();
        out.close();

        return "viewMessage";
    }

}
