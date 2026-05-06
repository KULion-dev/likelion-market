package com.likelion.market.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 *
 * DTO(Data Transfer Object): API 요청/응답에 사용하는 데이터 클래스예요.
 * 엔티티를 직접 노출하지 않고 DTO를 사용하는 게 좋은 습관이에요.
 */
public class AuthDto {

    // ── 회원가입 요청 ──────────────────────────────
    @Getter
    @Setter
    @NoArgsConstructor
    public static class SignupRequest {
        private String email;
        private String password;
        private String nickname;
    }

    // ── 로그인 요청 ────────────────────────────────
    @Getter
    @Setter
    @NoArgsConstructor
    public static class LoginRequest {
        private String email;
        private String password;
    }

    // ── 로그인 응답 (JWT 토큰 포함) ────────────────
    @Getter
    @AllArgsConstructor
    public static class LoginResponse {
        private String token;
        private String email;
        private String nickname;
    }
}
