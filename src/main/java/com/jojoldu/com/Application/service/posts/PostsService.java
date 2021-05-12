package com.jojoldu.com.Application.service.posts;

import com.jojoldu.com.Application.domain.posts.PostsRepository;
import com.jojoldu.com.Application.web.dto.PostsSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor    // final 이 선언된 모든 필드를 인자값으로 하는 생성자를 생성해 줌
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }
}
