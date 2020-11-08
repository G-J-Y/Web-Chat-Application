package com.concordia.message_board.service;

import com.concordia.message_board.entities.Post;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;

@Service
public class PostManagerImp implements PostManager {
    private ArrayList<Post> searchedPosts = new ArrayList<>();
    private ArrayList<Post> recentPosts = new ArrayList<>();
    @Override
    public boolean authentication(String userId, String password) {
        try {
            File inputFile = new File("users.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("user");
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                   // System.out.println("Student roll no : "
                   //          + eElement.getAttribute("rollno"));
                    String temPassword= eElement
                            .getElementsByTagName("password")
                            .item(0)
                            .getTextContent();
                    System.out.println("Password--->"+temPassword);

                    String temUserId = eElement
                            .getElementsByTagName("userId")
                            .item(0)
                            .getTextContent();
                    System.out.println("Name----->"+temUserId);


                    if(temUserId.equals(userId)&&encoding(userId,password).equals(temPassword)) {
                        System.out.println("I found it");
                        return true;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String encoding(String userId, String password) {

        String hashPassword =  new SimpleHash("SHA-256", password, userId+"soen387", 1024).toString();

        return hashPassword;
    }

}

