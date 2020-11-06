package com.concordia.message_board.service;

import org.springframework.stereotype.Service;

public interface PostManager {
    public boolean authentication(String username,String password);

    public String encoding(String username, String password);
}
