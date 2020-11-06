package com.concordia.message_board.service;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

@Service
public class PostManagerImp implements PostManager {
    @Override
    public boolean authentication(String username, String password) {
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
                    String temName = eElement
                            .getElementsByTagName("username")
                            .item(0)
                            .getTextContent();
                    System.out.println("Name----->"+temName);
                    String temPassword= eElement
                            .getElementsByTagName("password")
                            .item(0)
                            .getTextContent();
                    System.out.println("Password--->"+temPassword);

                    if(temName.equals(username)&&encoding(username,password).equals(temPassword)) {
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
    public String encoding(String username, String password) {

        String hashPassword =  new SimpleHash("SHA-256", password, username+"soen387", 1024).toString();

        return hashPassword;
    }

}

