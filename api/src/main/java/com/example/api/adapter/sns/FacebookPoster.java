package com.example.api.adapter.sns;


import org.springframework.stereotype.Component;

@Component
public class FacebookPoster {

    public String facebookPost(String title, String content, Long userId) {
        String message = "페이스북 게시글 등록";

        System.out.println(message);

        return message;
    }
}
