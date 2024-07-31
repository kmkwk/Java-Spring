package com.example.api.adapter.sns;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SnsService {

    private final SocialMedia facebookAdapter;
    private final SocialMedia instagramAdapter;

    public String createBoard(String platform, String title, String content, Long userId) {
        SocialMedia socialMedia = switch (platform) {
            case "facebook" -> facebookAdapter;
            case "instagram" -> instagramAdapter;
            default -> throw new IllegalArgumentException("Unsupported platform: " + platform);
        };

        return socialMedia.post(title, content, userId);
    }

}
