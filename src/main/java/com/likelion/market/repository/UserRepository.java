package com.likelion.market.repository;

import com.likelion.market.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 *
 * UserRepository: User 엔티티를 DB에서 조회·저장·삭제하는 창고 관리인이에요.
 * JpaRepository를 상속받으면 기본 CRUD 메서드가 자동으로 생겨요.
 *
 * findByEmail() → Spring Data JPA가 메서드 이름을 보고 자동으로 쿼리를 만들어줘요!
 * SELECT * FROM users WHERE email = ?
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 유저 찾기 (로그인, 중복 확인에 사용)
    Optional<User> findByEmail(String email);

    // 이메일 중복 확인
    boolean existsByEmail(String email);
}
