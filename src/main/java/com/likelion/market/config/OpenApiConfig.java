package com.likelion.market.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ✅ 완성된 파일 — 수정하지 않아도 돼요!
 *
 * Swagger(OpenAPI) 설정이에요.
 * 앱 실행 후 브라우저에서 → http://localhost:8080/swagger-ui.html
 *
 * 💡 Swagger UI에서 JWT 인증하는 법:
 *   1. /auth/login 으로 로그인해서 토큰 받기
 *   2. 우상단 [Authorize 🔒] 버튼 클릭
 *   3. Value 칸에 토큰만 붙여넣기 (Bearer 안 써도 돼요!)
 *   4. 이제 자물쇠 모양 API들 다 테스트 가능!
 */
@Configuration
public class OpenApiConfig {

    private static final String SECURITY_SCHEME_NAME = "bearerAuth";

    @Bean
    public OpenAPI marketOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("🦁 멋사 중고마켓 API")
                        .version("v1")
                        .description("고려대학교 14기 멋쟁이사자처럼 백엔드 세션 실습 API입니다."))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
                .components(new Components()
                        .addSecuritySchemes(SECURITY_SCHEME_NAME,
                                new SecurityScheme()
                                        .name(SECURITY_SCHEME_NAME)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
