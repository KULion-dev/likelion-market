package com.likelion.market.controller;

import com.likelion.market.dto.AuthDto;
import com.likelion.market.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 🔧 TODO가 있는 파일 — 빈칸을 채워보세요!
 *
 * AuthController: 회원가입·로그인 API를 담당하는 컨트롤러예요.
 * 2회차에서 배운 Controller(점원) 역할이에요!
 *
 * Postman에서 테스트할 API:
 *   POST http://localhost:8080/auth/signup  (회원가입)
 *   POST http://localhost:8080/auth/login   (로그인)
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 회원가입 API
     * POST /auth/signup
     *
     * TODO ①: authService.signup() 호출하고 201 Created 반환하세요
     *   힌트: authService.signup(request);
     *         return ResponseEntity.status(201).body("회원가입 성공!");
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthDto.SignupRequest request) {
        // TODO ① — 여기를 채워보세요
        return ResponseEntity.ok("TODO: 아직 구현 안 됨");
    }

    /**
     * 로그인 API
     * POST /auth/login
     *
     * TODO ②: authService.login() 호출하고 JWT 포함된 응답 반환하세요
     *   힌트: AuthDto.LoginResponse response = authService.login(request);
     *         return ResponseEntity.ok(response);
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDto.LoginRequest request) {
        // TODO ② — 여기를 채워보세요
        return ResponseEntity.ok("TODO: 아직 구현 안 됨");
    }
}
