package com.likelion.market.config;

import com.likelion.market.security.JwtFilter;
import com.likelion.market.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 🔧 TODO가 있는 파일 — 빈칸을 채워보세요!
 *
 * SecurityConfig: Spring Security의 전체 설정을 담당해요.
 * 3회차 강의 슬라이드 40번에서 본 코드예요!
 *
 * 중요: WebSecurityConfigurerAdapter는 Spring Security 5.7부터 deprecated됐어요.
 *       지금 방식(SecurityFilterChain Bean)이 최신 방법이에요.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // CSRF 비활성화 (REST API는 세션 안 쓰므로 불필요)
            .csrf(AbstractHttpConfigurer::disable)

            // 세션 사용 안 함 (JWT 방식이므로 Stateless)
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // TODO ①: URL별 접근 권한 설정하세요
            //   힌트:
            //   .authorizeHttpRequests(auth -> auth
            //       .requestMatchers("/auth/**").permitAll()      // 회원가입·로그인은 누구나
            //       .requestMatchers("/api/products").permitAll() // 상품 목록은 누구나
            //       .requestMatchers(                              // Swagger UI도 누구나
            //           "/swagger-ui/**", "/swagger-ui.html",
            //           "/v3/api-docs/**", "/v3/api-docs.yaml"
            //       ).permitAll()
            //       .anyRequest().authenticated()                 // 나머지는 로그인 필요
            //   )
            .authorizeHttpRequests(auth -> auth
                /* TODO ① — 여기를 채워보세요 */
                .anyRequest().permitAll()  // 임시: 일단 다 허용 (TODO 완성 후 삭제)
            )

            // TODO ②: JwtFilter를 UsernamePasswordAuthenticationFilter 앞에 추가하세요
            //   힌트: .addFilterBefore(new JwtFilter(jwtUtil, userDetailsService),
            //                          UsernamePasswordAuthenticationFilter.class)
            /* TODO ② — 여기를 채워보세요 */
            ;

        return http.build();
    }

    /**
     * ✅ 완성된 Bean — 비밀번호 암호화에 사용해요
     * BCrypt: 단방향 해시 함수. 같은 비밀번호도 매번 다른 해시값이 나와요.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
