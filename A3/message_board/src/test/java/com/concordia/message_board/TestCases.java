package com.concordia.message_board;

import com.concordia.message_board.entities.Post;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestCases {

    @Test
    public void Test1()
    {
        System.out.println("Starting Test...");
        Post post1 = new Post();
        post1.setMembership("Mcgill");
    }

}
