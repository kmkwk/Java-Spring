package com.example.api.adapter.sns;

import com.example.api.service.SnsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SnsServiceTest {

    @Autowired
    private SnsService snsService;

    @Test
    public void post_instagram() {
        Long userId = 1L;
        String title = "제목";
        String content = "내용";
        String platform = "instagram";

        String post = snsService.createBoard(platform, title, content, userId);

        assertThat(post).isEqualTo("인스타그램 게시글 등록");
    }


    @Test
    public void post_facebook() {
        Long userId = 1L;
        String title = "제목";
        String content = "내용";
        String platform = "facebook";

        String post = snsService.createBoard(platform, title, content, userId);

        assertThat(post).isEqualTo("페이스북 게시글 등록");
    }

}