package com.likelion.market.service;

import com.likelion.market.dto.AuthDto;
import com.likelion.market.entity.User;
import com.likelion.market.repository.UserRepository;
import com.likelion.market.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 🔧 TODO가 있는 파일 — 핵심 실습 파일이에요!
 *
 * AuthService: 회원가입·로그인 비즈니스 로직을 담당해요.
 * 2회차에서 배운 Service 레이어 기억하죠? 주방장 역할이에요!
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    /**
     * 회원가입
     *
     * TODO ①: 이메일 중복 확인
     *   힌트: userRepository.existsByEmail(request.getEmail())
     *         true면 throw new RuntimeException("이미 사용 중인 이메일입니다.");
     *
     * TODO ②: 비밀번호 암호화
     *   힌트: passwordEncoder.encode(request.getPassword())
     *         → BCrypt로 암호화된 문자열이 나와요
     *         → DBeaver에서 저장된 값 확인해보세요!
     *
     * TODO ③: User 객체 생성 후 DB 저장
     *   힌트: User user = new User(이메일, 암호화된비번, 닉네임);
     *         userRepository.save(user);
     */
    public void signup(AuthDto.SignupRequest request) {
        // TODO ① — 이메일 중복 확인


        // TODO ② — 비밀번호 암호화
        String encodedPassword = null; // ← null 지우고 암호화 코드 작성


        // TODO ③ — User 저장

    }

    /**
     * 로그인
     *
     * TODO ④: 이메일로 유저 조회
     *   힌트: userRepository.findByEmail(request.getEmail())
     *         .orElseThrow(() -> new RuntimeException("존재하지 않는 이메일입니다."));
     *
     * TODO ⑤: 비밀번호 검증
     *   힌트: passwordEncoder.matches(입력한비번, DB에저장된비번)
     *         false면 throw new RuntimeException("비밀번호가 틀렸습니다.");
     *
     * TODO ⑥: JWT 토큰 발급 후 반환
     *   힌트: String token = jwtUtil.generateToken(user.getEmail());
     *         return new AuthDto.LoginResponse(token, user.getEmail(), user.getNickname());
     */
    public AuthDto.LoginResponse login(AuthDto.LoginRequest request) {
        // TODO ④ — 유저 조회


        // TODO ⑤ — 비밀번호 검증


        // TODO ⑥ — JWT 발급
        return null; // ← null 지우고 실제 반환값 작성
    }
}
