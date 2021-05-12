package com.jojoldu.com.Application.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 클래스 내 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor  // 기본 생성자 자동 추가, public Posts(){}와 같은 효과
@Entity // 테이블과 링크될 클래스임을 나타냄
public class Posts {

    @Id // 해당 테이블의 pk 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pk의 생성 규칙을 나타냄, IDENTITY 옵션은 auto_increment
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타내며 굳이 선언하지 않아도 해당 클래스의 필드는 모두 칼럼이 됨
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)    // 기본값 이외에 추가로 변경이 필요한 옵션이 있을때 사용
    private String content;

    private String author;

    @Builder    // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언 시 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
