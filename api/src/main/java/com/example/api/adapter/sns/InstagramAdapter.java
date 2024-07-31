package com.example.api.adapter.sns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InstagramAdapter implements SocialMedia{

    private final InstagramPoster instagramPoster;

    @Override
    public String post(String title, String content, Long userId) {
        return instagramPoster.instagramPost(title, content, userId);
    }
}
