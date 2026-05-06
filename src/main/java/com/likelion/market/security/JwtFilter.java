package com.likelion.market.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 🔧 TODO가 있는 파일 — 빈칸을 채워보세요!
 *
 * JwtFilter: 모든 요청에서 JWT 토큰을 검사하는 필터예요.
 *
 * 3회차 강의에서 배운 필터 체인 기억하죠?
 * Request → [Filter1 → Filter2 → ... → JwtFilter → ...] → Controller
 *
 * OncePerRequestFilter: 요청당 딱 한 번만 실행되는 필터예요.
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        // 1단계: Authorization 헤더에서 토큰 꺼내기
        String authHeader = request.getHeader("Authorization");

        // TODO ①: authHeader가 있고 "Bearer "로 시작하는지 확인하세요
        //   힌트: authHeader != null && authHeader.startsWith("Bearer ")
        if (/* TODO ① */ false) {

            // TODO ②: "Bearer " 뒤의 실제 토큰 문자열만 추출하세요
            //   힌트: authHeader.substring(7)
            String token = /* TODO ② */ null;

            // TODO ③: jwtUtil.validateToken()으로 토큰이 유효한지 확인하세요
            if (/* TODO ③ */ false) {

                // TODO ④: jwtUtil.getEmailFromToken()으로 이메일을 꺼내세요
                String email = /* TODO ④ */ null;

                // 5단계: 이메일로 유저 정보 로드 (이건 완성됐어요)
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);

                // 6단계: SecurityContext에 인증 정보 저장 (이건 완성됐어요)
                // → 이 순간부터 이 요청은 "인증된 요청"이 돼요
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        // 다음 필터로 넘기기 (항상 실행!)
        filterChain.doFilter(request, response);
    }
}
