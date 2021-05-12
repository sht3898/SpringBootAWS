package com.jojoldu.com.Application.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
// 보통 ibatis 나 MyBatis 등에서 Dao 라고 불리는 DB Layer 접근자
// 단순 인터페이스를 생성 후, JpaRepository<Entity, PK 타입>를 상속하면 기본적인 CRUD 메소드가 자동으로 생성
// Entity 클래스와 기본 Entity Repository 는 함께 위치해야 함
public interface PostsRepository extends JpaRepository<Posts, Long> {
}
