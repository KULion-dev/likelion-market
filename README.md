# 🦁 멋사 중고마켓 — 실습 프로젝트

고려대학교 14기 멋쟁이사자처럼 백엔드 세션 실습

> 회원가입·로그인이 가능한 **중고거래 API**를 직접 만들어 봐요!
> JWT 인증까지 붙은 진짜 백엔드 서비스예요. 🚀

---

## 📌 한눈에 보기

```
        ┌──────────────────────────────────────────┐
        │  Postman (클라이언트)                     │
        └──────────────────────────────────────────┘
                          │  HTTP 요청
                          ▼
        ┌──────────────────────────────────────────┐
        │  🚪 Spring Security 필터 (문지기)         │
        │     → JWT 토큰 있나? 유효한가? 검사       │
        └──────────────────────────────────────────┘
                          │
                          ▼
        ┌──────────────────────────────────────────┐
        │  🧑‍💼 Controller (점원)                    │
        │     → 요청 받고 적절한 곳으로 전달        │
        └──────────────────────────────────────────┘
                          │
                          ▼
        ┌──────────────────────────────────────────┐
        │  👨‍🍳 Service (주방장)                      │
        │     → 실제 비즈니스 로직 처리             │
        └──────────────────────────────────────────┘
                          │
                          ▼
        ┌──────────────────────────────────────────┐
        │  📦 Repository (창고지기)                  │
        │     → DB에 저장하고 꺼내옴                │
        └──────────────────────────────────────────┘
                          │
                          ▼
                    🗄️  MySQL DB
```

> 💡 **2회차 비유 그대로예요!** Controller=점원, Service=주방장, Repository=창고지기.
> 여기에 이번엔 **문지기(Spring Security 필터)** 가 추가됐어요.

---

## 🚀 시작 전 준비

### 1. DB 생성 (DBeaver)
```sql
CREATE DATABASE likelion_market CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. application.yml 수정
```yaml
spring:
  datasource:
    password: 본인_MySQL_비밀번호  # ← 여기만 바꾸면 돼요!
```

### 3. IntelliJ에서 실행
- `Shift + F10` 으로 실행
- 하단 Run 탭에서 `Started MarketApplication` 확인되면 성공!

### 4. Swagger UI 접속해보기 🎉
브라우저에서 👉 **http://localhost:8080/swagger-ui.html**

> 우리 API 문서가 자동으로 생성돼요! Postman 없이도 여기서 바로 테스트 가능!

> ✅ **여기까지 실행만 되면 OK!** TODO를 안 채워도 앱은 일단 돌아가요.
> (Security 설정에 임시로 `permitAll()`이 깔려 있어서요.)

---

## 🧠 꼭 알고 가야 할 핵심 개념 3가지

### ① JWT가 뭔가요?

> **로그인 성공 증표 같은 문자열**이에요.

```
회원가입 → 로그인 성공 → 서버가 JWT 발급
                              │
                              ▼
       다음 요청부터는 JWT를 들고 가서 "저 로그인한 사람이에요" 증명
```

JWT는 점 두 개로 구분된 3덩어리예요:
```
eyJhbGc...   .   eyJzdWIi...   .   SflKxw...
   Header           Payload          Signature
   (알고리즘)     (이메일 등 정보)   (위변조 방지 서명)
```

> 💡 **jwt.io** 사이트에 발급받은 토큰 붙여보면 내용물 다 보여요!

### ② @AuthenticationPrincipal 이 뭔가요?

```java
@PostMapping
public ... createProduct(
    @AuthenticationPrincipal UserDetails userDetails  // ← 이거!
) {
    String email = userDetails.getUsername();  // 로그인한 사람 이메일
}
```

> **JwtFilter가 토큰에서 꺼낸 유저 정보를, Controller에서 바로 쓸 수 있게 해주는 마법**이에요.
> 로그인한 사람이 누군지 매번 토큰 파싱할 필요 없이 위 한 줄로 끝!

### ③ BCrypt 비밀번호 암호화

```
입력한 비밀번호: "1234"
              ↓
DB 저장 비번:  "$2a$10$N9qo8uLOickgx2ZMRZoMye..."
```

> 같은 비밀번호 `"1234"`도 매번 다른 해시값이 나와요. (단방향이라 복호화 ❌)
> **DBeaver에서 직접 확인해보세요!** 신기해요.

---

## 📁 프로젝트 구조 (총 16개 파일)

```
src/main/java/com/likelion/market/
│
├── MarketApplication.java          ✅ 완성 (앱 시작점)
│
├── entity/                         ✅ 완성 (DB 테이블 = 자바 클래스)
│   ├── User.java                       └ 회원
│   └── Product.java                    └ 상품 (User와 N:1 관계)
│
├── repository/                     ✅ 완성 (DB 창고지기)
│   ├── UserRepository.java
│   └── ProductRepository.java
│
├── dto/                            ✅ 완성 (요청·응답 데이터 그릇)
│   ├── AuthDto.java                    └ 회원가입/로그인용
│   └── ProductDto.java                 └ 상품 등록/조회용
│
├── security/                       🔐 인증 관련
│   ├── JwtUtil.java                ✅ 완성 (토큰 만들고 검증)
│   ├── CustomUserDetailsService    ✅ 완성 (Security용 유저 로더)
│   └── JwtFilter.java              🔧 TODO ①②③④
│
├── config/
│   └── SecurityConfig.java         🔧 TODO ①②
│
├── service/                        👨‍🍳 비즈니스 로직 (주방장)
│   ├── AuthService.java            🔧 TODO ①~⑥
│   └── ProductService.java         🔧 TODO ①②③
│
└── controller/                     🧑‍💼 API 엔드포인트 (점원)
    ├── AuthController.java         🔧 TODO ①②
    └── ProductController.java      🔧 TODO ①②
```

> 🎯 **🔧 표시된 6개 파일만 채우면 끝!** 나머지는 다 완성돼 있어요.

---

## 📋 STEP별 TODO 목록 (이 순서대로!)

### 🔵 STEP 1. 회원가입·로그인 만들기

> **목표**: Postman으로 회원가입·로그인 → DBeaver에서 BCrypt 해시 확인!

| 파일 | TODO | 할 일 |
|------|------|------|
| `AuthService.java` | ① | 이메일 중복 확인 |
| `AuthService.java` | ② | 비밀번호 BCrypt 암호화 |
| `AuthService.java` | ③ | User DB 저장 |
| `AuthService.java` | ④ | 이메일로 유저 조회 |
| `AuthService.java` | ⑤ | 비밀번호 검증 |
| `AuthService.java` | ⑥ | JWT 토큰 발급 |
| `AuthController.java` | ① | 회원가입 API 완성 |
| `AuthController.java` | ② | 로그인 API 완성 |

> ✨ **STEP 1 완료 체크포인트**
> - [ ] Postman으로 `POST /auth/signup` → `201 Created`
> - [ ] DBeaver에서 `users` 테이블에 `$2a$10$...` 형태의 해시 비밀번호 저장됨
> - [ ] Postman으로 `POST /auth/login` → JWT 토큰 응답 받음
> - [ ] jwt.io에 토큰 붙여보면 Payload에 내 이메일 보임

---

### 🟢 STEP 2. JWT 인증 필터 붙이기

> **목표**: 토큰 없이 요청하면 401, 토큰 있으면 통과되게 만들기!

| 파일 | TODO | 할 일 |
|------|------|------|
| `JwtFilter.java` | ① | Authorization 헤더 확인 |
| `JwtFilter.java` | ② | "Bearer " 뒤 토큰 추출 |
| `JwtFilter.java` | ③ | 토큰 유효성 검증 |
| `JwtFilter.java` | ④ | 토큰에서 이메일 추출 |
| `SecurityConfig.java` | ① | URL별 권한 설정 |
| `SecurityConfig.java` | ② | JwtFilter 등록 |

> ⚠️ **꼭 주의!** `SecurityConfig`에 임시로 깔려 있는
> `.anyRequest().permitAll()` 줄은 **TODO ① 채운 뒤 반드시 삭제**하세요!
> 안 지우면 토큰 검사가 무력화돼요.

> ✨ **STEP 2 완료 체크포인트**
> - [ ] 토큰 없이 `POST /api/products` 요청 → `401 Unauthorized`
> - [ ] 잘못된 토큰으로 요청 → `401 Unauthorized`
> - [ ] 정상 토큰으로 요청 → 통과 (다음 단계에서 채울 거라 응답은 TODO 메시지)
> - [ ] **http://localhost:8080/swagger-ui.html** 가 여전히 잘 열림
>       (안 열리면 SecurityConfig TODO ①에 swagger 경로 `permitAll` 빠진 것!)

---

### 🟡 STEP 3. 상품 API에 인증 연결하기

> **목표**: "로그인한 내가" 상품 등록하고, "내" 상품만 따로 조회!

| 파일 | TODO | 할 일 |
|------|------|------|
| `ProductService.java` | ① | 유저 조회 후 상품 저장 |
| `ProductService.java` | ② | (위에 포함) |
| `ProductService.java` | ③ | 내 상품 목록 조회 |
| `ProductController.java` | ① | 상품 등록 API 완성 |
| `ProductController.java` | ② | 내 상품 조회 API 완성 |

> ✨ **STEP 3 완료 체크포인트**
> - [ ] 토큰으로 상품 등록 → `products` 테이블에 `user_id` 채워져서 저장
> - [ ] `GET /api/products/mine` → 내가 등록한 상품만 보임
> - [ ] 다른 계정으로 로그인하면 다른 결과가 나옴

---

## 🔐 인증 요청이 처리되는 흐름 (그림으로)

```
[Postman]
   │  POST /api/products
   │  Headers: Authorization: Bearer eyJhbGc...
   ▼
┌──────────────────────────────────────────────────────────┐
│  JwtFilter                                               │
│  ① Authorization 헤더 있나? "Bearer "로 시작?            │
│  ② "Bearer " 뒤 토큰 부분만 잘라내기                     │
│  ③ jwtUtil.validateToken(token) → 유효한가?              │
│  ④ jwtUtil.getEmailFromToken(token) → 이메일 꺼내기      │
│  ⑤ SecurityContext에 "이 사람 인증됨" 표시               │
└──────────────────────────────────────────────────────────┘
   │
   ▼
┌──────────────────────────────────────────────────────────┐
│  ProductController                                       │
│  @AuthenticationPrincipal UserDetails userDetails        │
│       ↑ 위에서 SecurityContext에 넣어둔 정보가 여기로!   │
│  String email = userDetails.getUsername();               │
└──────────────────────────────────────────────────────────┘
   │
   ▼
┌──────────────────────────────────────────────────────────┐
│  ProductService                                          │
│  email로 User 찾고 → 그 유저로 Product 만들어 저장       │
└──────────────────────────────────────────────────────────┘
```

---

## 🌐 Swagger UI로 테스트하기 (추천!)

브라우저에서 👉 **http://localhost:8080/swagger-ui.html**

### 사용 순서

```
1. /auth/signup 펼치기 → [Try it out] → 정보 입력 → [Execute]
       ↓
2. /auth/login 펼치기 → [Try it out] → 로그인 → 응답에서 token 복사 📋
       ↓
3. 페이지 우상단 [Authorize 🔒] 버튼 클릭
       ↓
4. Value 칸에 토큰만 붙여넣기 (Bearer 안 써도 돼요!) → [Authorize]
       ↓
5. 이제 자물쇠 표시된 API들 다 테스트 가능! 🎉
```

> 💡 한 번 [Authorize]하면 모든 요청에 자동으로 토큰이 붙어요. Postman보다 편해요!

> ⚠️ 토큰은 24시간 후 만료돼요. 401 뜨면 다시 로그인해서 토큰 갱신 → 다시 [Authorize].

---

## 🧪 Postman 테스트 시나리오

```
1. POST /auth/signup       → 회원가입 (201)
2. POST /auth/login        → 로그인 → 토큰 복사! 📋
3. GET  /api/products      → 전체 상품 (로그인 불필요)
4. POST /api/products      → 상품 등록
                             Headers: Authorization: Bearer {복사한토큰}
5. GET  /api/products/mine → 내 상품만 조회
                             Headers: Authorization: Bearer {복사한토큰}
```

### 📨 요청 예시

**회원가입**
```json
POST /auth/signup
{
  "email": "lion@korea.ac.kr",
  "password": "1234",
  "nickname": "사자123"
}
```

**상품 등록**
```json
POST /api/products
Headers: Authorization: Bearer eyJhbGciOiJIUzI1NiJ9...
{
  "title": "에어팟 프로 2세대",
  "description": "거의 새 거예요",
  "price": 200000
}
```

---

## 🎁 보너스 미션 (시간 남으면!)

- [ ] `GET /api/products/{id}` — 상품 상세 조회
- [ ] `DELETE /api/products/{id}` — 본인 상품만 삭제 (남의 거 삭제 시 403)
- [ ] `GET /api/me` — 내 정보 조회
- [ ] `@Email`, `@NotBlank` 어노테이션으로 입력값 검증
- [ ] `@RestControllerAdvice`로 예외를 깔끔한 JSON 응답으로 변환

---

## 💡 자주 막히는 포인트

### Q1. 앱 실행 시 빨간 글씨 잔뜩 나와요 😱
> Run 탭의 **마지막 빨간 줄**부터 읽으세요. 거기 답이 있어요.
> - `Access denied for user 'root'` → `application.yml` 비밀번호 확인
> - `Communications link failure` → MySQL 서버 켜져 있는지 확인
> - `Unknown database` → DBeaver에서 `CREATE DATABASE` 했는지 확인

### Q2. Postman에서 401 Unauthorized 떠요
> ✔️ Headers 탭에 `Authorization` 키 추가했나요?
> ✔️ 값이 `Bearer eyJ...` 형태인가요? (**`Bearer ` 뒤에 공백 1칸!**)
> ✔️ 토큰 만료 안 됐나요? (24시간 유효)
> ✔️ `SecurityConfig`의 임시 `.permitAll()` 줄 안 지웠나요?

### Q3. DBeaver에 데이터가 안 보여요
> - **F5** (새로고침)
> - 왼쪽에서 `likelion_market` DB 선택돼 있는지
> - `users` 테이블 우클릭 → `View Data` → `All Rows`

### Q4. "왜 username인데 email이 나와요?"
> Spring Security가 부르는 **"username = 로그인 식별자"** 의미예요.
> 우리 프로젝트에선 그게 email이라서 `userDetails.getUsername()`이 email을 돌려줘요.
> (`CustomUserDetailsService`에서 그렇게 만들어 놨어요!)

### Q5. 컴파일 에러는 없는데 TODO를 안 채운 부분이 동작 안 해요
> 정상이에요. TODO에 `if (false)`, `return null` 같은 임시 코드를 넣어둬서
> **컴파일은 되지만 실제 로직은 비어있는 상태**예요. 차근차근 채워보세요!

### Q6. Swagger UI에서 401 Unauthorized 나와요
> ✔️ 우상단 [Authorize 🔒] 클릭해서 토큰 등록했나요?
> ✔️ 토큰만 넣어야 해요! `Bearer ` 안 써도 돼요. (Swagger가 알아서 붙여줘요)
> ✔️ 토큰 만료됐을 수도 있어요. 다시 `/auth/login` → 새 토큰 → 다시 [Authorize].

### Q7. Swagger UI 페이지 자체가 안 열려요 (404 / 401)
> - **404**: 앱 잘 떴나요? Run 탭에 빨간 글씨 없는지 확인. 의존성 새로 받아야 할 수 있어요 (`./gradlew build`).
> - **401**: STEP 2 진행하면서 SecurityConfig TODO ①에 swagger 경로 빠뜨린 거예요.
>   힌트 주석에 있는 `/swagger-ui/**`, `/v3/api-docs/**` 라인 추가해주세요.

---

## 🦁 마지막으로

> **막히면 README → 강의자료 → 옆 친구 → 운영진** 순서로 도움 받기!
> 에러 메시지를 그대로 복붙해서 검색하는 습관이 백엔드 개발자의 첫걸음이에요.

화이팅이에요! 🔥
# likelion-market
