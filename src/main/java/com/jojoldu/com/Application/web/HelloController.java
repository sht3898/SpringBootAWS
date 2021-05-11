package com.jojoldu.com.Application.web;

import com.jojoldu.com.Application.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌, 예전에는 @ResponseBody를 각 메소드마다 선언했음
public class HelloController {

    @GetMapping("/hello")   // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 줌
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloResponseDto(@RequestParam("name") String name,
                                             @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
