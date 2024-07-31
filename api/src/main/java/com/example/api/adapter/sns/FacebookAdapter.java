package com.example.api.adapter.sns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FacebookAdapter implements SocialMedia {

    private final FacebookPoster facebookPoster;

    @Override
    public String post(String title, String content, Long userId) {
        return facebookPoster.facebookPost(title, content, userId);
    }
}
