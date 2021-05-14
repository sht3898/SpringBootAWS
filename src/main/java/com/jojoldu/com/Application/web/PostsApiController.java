package com.jojoldu.com.Application.web;

import com.jojoldu.com.Application.service.posts.PostsService;
import com.jojoldu.com.Application.web.dto.PostsResponseDto;
import com.jojoldu.com.Application.web.dto.PostsSaveRequestDto;
import com.jojoldu.com.Application.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor    // final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해 줌
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id){
        return postsService.findById(id);
    }
}
