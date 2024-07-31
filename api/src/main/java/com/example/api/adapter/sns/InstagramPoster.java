package com.example.api.adapter.sns;

import org.springframework.stereotype.Component;

@Component
public class InstagramPoster {

    public String instagramPost(String title, String content, Long userId) {
        String message = "인스타그램 게시글 등록";

        System.out.println(message);

        return message;
    }
}
