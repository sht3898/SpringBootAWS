package com.jojoldu.com.Application.web;

import com.jojoldu.com.Application.service.posts.PostsService;
import com.jojoldu.com.Application.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor    // final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해 줌
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PutMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
