package com.likelion.market.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 *
 * User 엔티티: 회원 정보를 저장하는 테이블이에요.
 * @Entity    → 이 클래스가 DB 테이블과 매핑된다는 표시
 * @Table     → 실제 테이블 이름 지정
 * @Id        → Primary Key
 * @Column    → 컬럼 이름과 제약조건 설정
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT
    private Long id;

    @Column(nullable = false, unique = true)  // NOT NULL, UNIQUE
    private String email;

    @Column(nullable = false)
    private String password;   // BCrypt 암호화된 비밀번호가 저장돼요

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;   // 기본값은 일반 유저

    // 편의 생성자
    public User(String email, String password, String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.role = Role.USER;
    }

    // 역할(권한) 구분
    public enum Role {
        USER, ADMIN
    }
}
