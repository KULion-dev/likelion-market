package com.likelion.market.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 *
 * JwtUtil: JWT 토큰을 만들고 검증하는 유틸 클래스예요.
 *
 * 3회차 강의에서 배운 내용:
 * - Header: 알고리즘 정보 (HS256)
 * - Payload: 사용자 정보 (email)
 * - Signature: 위변조 방지 서명
 *
 * 💡 jwt.io에서 이 토큰을 붙여보면 내용이 보여요!
 *    → Payload는 암호화가 아니라 인코딩이에요.
 */
@Component
public class JwtUtil {

    private final Key key;
    private final long expiration;

    public JwtUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expiration
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.expiration = expiration;
    }

    /**
     * 이메일로 JWT 토큰 생성
     * 사용법: String token = jwtUtil.generateToken(user.getEmail());
     */
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)              // Payload: email 저장
                .setIssuedAt(new Date())        // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + expiration))  // 만료 시간
                .signWith(key, SignatureAlgorithm.HS256)  // 서명
                .compact();
    }

    /**
     * 토큰에서 이메일 추출
     * 사용법: String email = jwtUtil.getEmailFromToken(token);
     */
    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    /**
     * 토큰 유효성 검증
     * 사용법: boolean valid = jwtUtil.validateToken(token);
     */
    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // 내부 헬퍼: 토큰에서 Claims(페이로드) 파싱
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
