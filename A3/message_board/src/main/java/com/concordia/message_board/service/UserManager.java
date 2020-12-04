package com.concordia.message_board.service;

import java.util.List;

public interface UserManager {
    public List<String> getList(String status);
    public void initializeFactory(String filePath);
}
