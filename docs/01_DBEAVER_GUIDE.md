# 🐬 DBeaver 사용법 가이드

> MySQL 데이터베이스를 시각적으로 다루는 무료 도구예요.
> 우리는 회원·상품 데이터가 잘 저장됐는지 눈으로 확인할 때 사용해요.

---

## 0. 설치 (아직 안 했다면)

👉 https://dbeaver.io/download/ → **Community Edition** 다운로드 → 설치

> 💡 **Mac에서 brew 쓰는 분**: `brew install --cask dbeaver-community` 한 줄로 끝!

---

## 📋 빠른 시작 — 복붙용 요약

> 처음 한 번만 따라하면 돼요. 막히면 아래 [상세 가이드](#-mysql-연결하기-상세-가이드) 참고!

### 🔑 입력할 연결 정보

```
Server Host  : localhost
Port         : 3306
Database     : likelion_market
Username     : root
Password     : (본인 MySQL 비밀번호 — application.yml에 적은 거랑 똑같이!)
```

### ⚠️ Driver properties 탭에서 꼭 추가!

```
allowPublicKeyRetrieval = true
useSSL                  = false
serverTimezone          = Asia/Seoul
```

### 🎬 6단계로 끝내기

```
① DBeaver 좌상단 🔌 콘센트 아이콘 클릭
② "MySQL" 검색 → 선택 → Next
③ Main 탭에 위 연결 정보 입력
④ Driver properties 탭에 위 3줄 추가  ⚠️ 꼭!
⑤ Test Connection → Driver Download (처음만) → ✅ Connected
⑥ Finish → 좌측 트리에 localhost 생기면 끝!
```

---

## 🪜 MySQL 연결하기 (상세 가이드)

### 0단계. 사전 준비
- ✅ **MySQL이 켜져 있어야 해요.**
  - **Mac**: `brew services start mysql` (또는 시스템 설정 → MySQL 시작)
  - **Windows**: `Win + R` → `services.msc` → MySQL 우클릭 → 시작
- ✅ **MySQL root 비밀번호**를 알고 있어야 해요. (설치할 때 정한 비번)

---

### 1단계. 새 연결 만들기

DBeaver 좌상단 **🔌 콘센트 아이콘** 클릭
또는 메뉴: `Database` → `New Database Connection`

---

### 2단계. MySQL 선택

검색창에 `MySQL` 입력 → **MySQL** 아이콘 클릭 → `Next`

> ⚠️ "MySQL"이 두 개 보일 수 있어요. **첫 번째 거 (그냥 MySQL)** 고르면 돼요.

---

### 3단계. 연결 정보 입력

**Main 탭에 이렇게 입력:**

| 항목 | 값 |
|---|---|
| **Server Host** | `localhost` |
| **Port** | `3306` |
| **Database** | `likelion_market` |
| **Username** | `root` |
| **Password** | 본인 MySQL 비밀번호 (`application.yml`에 적은 거랑 똑같이) |

> 💡 `Save password locally` 체크하면 다음에 비번 안 물어봐요.

---

### 4단계. SSL/타임존 옵션 추가 ⚠️ 가장 많이 빼먹는 부분!

상단 탭 중 **"Driver properties"** 클릭 → 아래 항목 추가/수정:

| Property | Value |
|---|---|
| `allowPublicKeyRetrieval` | `true` |
| `useSSL` | `false` |
| `serverTimezone` | `Asia/Seoul` |

**추가하는 법**: 빈 칸을 더블클릭하면 입력 가능. 또는 우클릭 → `Add new property`

> ⚠️ 이거 안 하면 `Public Key Retrieval is not allowed` 에러가 나요!

---

### 5단계. 드라이버 다운로드 (처음 한 번만!)

**`Test Connection`** 버튼 클릭 → "Download driver files?" 팝업 → `Download` 클릭
(MySQL 드라이버가 자동으로 설치돼요)

---

### 6단계. 연결 완료

`Test Connection` → **✅ Connected** 뜨면 성공! → `Finish` 클릭

좌측 트리에 `localhost` 또는 `MySQL - localhost` 가 생기면 끝!

---

## 🔍 데이터 확인하는 법

### 트리 펼치기
좌측 트리에서:
```
likelion_market (왼쪽 화살표 클릭)
  └─ Tables
      ├─ users     ← 회원 정보
      └─ products  ← 상품 정보
```

### 데이터 보기
**테이블 우클릭 → `View Data` → `All Rows`** 하면 표 형태로 보여요.

> 💡 앱을 실행해야 테이블이 자동 생성돼요 (`ddl-auto: update` 옵션 덕분).
> Tables 폴더가 비어있으면 → 앱 실행 → DBeaver에서 **F5** (새로고침)

---

## 🧪 회원가입 후 데이터 확인 시나리오

```
1. Spring Boot 앱 실행
2. Swagger UI에서 /auth/signup 호출
3. DBeaver → users 테이블 우클릭 → View Data
4. password 컬럼에 $2a$10$... 형태로 BCrypt 해시 보이면 성공! 🎉
```

---

## 💾 샘플 데이터 넣어보기

> 상품 목록을 화려하게(?) 채워서 테스트하고 싶을 때 쓰는 SQL이에요.
> Swagger에서 일일이 등록하는 것보다 빨라요!

### ⚠️ 시작 전 체크
1. **회원이 먼저 있어야 해요.** (products의 `user_id`가 회원 id를 참조하기 때문)
2. Swagger UI에서 회원가입 한 번 하거나, 아래 쿼리로 user_id 확인:
   ```sql
   SELECT id, email, nickname FROM users;
   ```
3. **사용할 user_id를 메모해두세요!** (예: `1`)

---

### 📦 products 5개 한 번에 추가하기

> 아래 쿼리에서 마지막 숫자(`1`)를 본인 user_id로 바꿔주세요!

```sql
INSERT INTO products (title, description, price, status, user_id) VALUES
  ('에어팟 프로 2세대',     '거의 새거예요. 박스 있습니다',           200000,  'SELLING',  1),
  ('맥북 에어 M2',          '2023년식, 키스킨 사용해서 깨끗해요',     1300000, 'SELLING',  1),
  ('자전거 (삼천리)',       '학교 통학용으로 썼어요. 잔고장 없음',    80000,   'RESERVED', 1),
  ('전공책 - 자료구조',     '필기 거의 없고 깔끔합니다',              15000,   'SELLING',  1),
  ('아이폰 14 케이스',      '투명 케이스, 사용감 적어요',             5000,    'SOLD',     1);
```

### ✅ 잘 들어갔나 확인

```sql
SELECT id, title, price, status, user_id FROM products;
```

→ 5개 행이 보이면 성공! 🎉

### 🌐 API로도 확인

브라우저에서 **http://localhost:8080/swagger-ui.html** → `GET /api/products` → `Execute`
→ 방금 넣은 5개 상품이 JSON으로 보여요!

---

### 🧑‍🤝‍🧑 여러 회원 + 여러 상품 시나리오

> "다른 사람의 상품"이 보이는 시나리오를 만들고 싶을 때.

#### Step 1. Swagger UI에서 회원 2명 가입하기

> ⚠️ 회원은 **반드시 Swagger UI(`POST /auth/signup`)로 가입**해야 해요.
> SQL `INSERT`로 직접 만들면 BCrypt 비밀번호를 직접 만들 수 없어서 로그인이 안 됩니다.

```
1) Swagger에서 회원 A 가입: lion@korea.ac.kr / 1234 / 사자123
2) Swagger에서 회원 B 가입: tiger@korea.ac.kr / 1234 / 호랑이456
```

#### Step 2. user_id 확인

```sql
SELECT id, email, nickname FROM users;
```
→ 결과에서 사자, 호랑이의 id 메모! (예: 사자=2, 호랑이=3)

#### Step 3. 각자 상품 SQL로 추가

```sql
-- user_id는 Step 2에서 확인한 값으로 바꿔주세요!
INSERT INTO products (title, description, price, status, user_id) VALUES
  ('닌텐도 스위치 OLED', '풀박스, 상태 좋음',     290000, 'SELLING', 2),  -- 사자 상품
  ('갤럭시 버즈 프로',   '한 번도 안 쓴 미개봉',   150000, 'SELLING', 2),  -- 사자 상품
  ('전기 포트',          '자취방용, 1.5L',          12000,  'SELLING', 3),  -- 호랑이 상품
  ('LG 그램 17인치',     '2022년식, 배터리 좋음',   900000, 'SELLING', 3); -- 호랑이 상품
```

#### Step 4. 결과 확인 🔥

Swagger에서 두 계정으로 번갈아 로그인 → `GET /api/products/mine` 호출
→ **각자 자기 상품만** 나오는지 확인하면 STEP 3 인증 흐름이 제대로 동작한다는 증거!

---

### 🧹 샘플 데이터 다 지우고 싶을 때

```sql
-- 상품만 지우기
DELETE FROM products;

-- 회원까지 다 지우기 (FK 때문에 products 먼저!)
DELETE FROM products;
DELETE FROM users;

-- AUTO_INCREMENT까지 1로 리셋
ALTER TABLE products AUTO_INCREMENT = 1;
ALTER TABLE users AUTO_INCREMENT = 1;
```

> ⚠️ products보다 users 먼저 지우면 **외래키 제약**으로 에러나요. 순서 주의!

---

## 💻 SQL 직접 실행하는 법

### SQL Editor 열기
연결 우클릭 → `SQL Editor` → `New SQL Editor`
(또는 단축키 **Ctrl + ]**)

### 자주 쓰는 SQL

```sql
-- DB 생성
CREATE DATABASE likelion_market CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 어떤 DB를 쓰는지 변경
USE likelion_market;

-- 모든 회원 조회
SELECT * FROM users;

-- 모든 상품 조회
SELECT * FROM products;

-- 특정 회원이 등록한 상품 보기 (JOIN)
SELECT p.title, p.price, u.nickname
FROM products p
JOIN users u ON p.user_id = u.id;

-- 데이터 다 지우기 (조심!)
DELETE FROM products;
DELETE FROM users;
```

> 💡 SQL 실행 단축키: **Ctrl + Enter** (커서 위치의 한 줄만)
> 또는 **Alt + X** (전체 실행)

---

## 🚨 자주 나는 에러

### ❌ "Communications link failure"
> **MySQL 서버가 안 켜져 있어요.**
> - Mac: `brew services start mysql`
> - Windows: 서비스에서 MySQL 시작

### ❌ "Access denied for user 'root'@'localhost'"
> **비밀번호가 틀렸어요.**
> `application.yml`과 DBeaver의 비번이 같은지 확인.

### ❌ "Unknown database 'likelion_market'"
> **DB를 아직 안 만들었어요.**
> ```sql
> CREATE DATABASE likelion_market CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
> ```

### ❌ "Public Key Retrieval is not allowed"
> 4단계에서 `allowPublicKeyRetrieval=true` 빼먹은 거예요. 다시 추가!

### ❌ Tables 폴더가 비어있어요
> 1. Spring Boot 앱이 실행 중인지 확인 (Run 탭에 `Started MarketApplication` 있어야 함)
> 2. DBeaver에서 **F5** 새로고침
> 3. 아직 회원가입을 한 번도 안 했으면 테이블만 있고 데이터는 비어있어요 (정상)

---

## 💡 꿀팁

### Tip 1. 데이터 직접 수정
View Data 화면에서 셀 더블클릭 → 값 변경 → 좌하단 **저장 아이콘** 클릭
(연습 환경이라 가능. 실무에선 절대 금지!)

### Tip 2. ER 다이어그램 보기
`likelion_market` 우클릭 → `View Diagram`
→ User와 Product 관계가 그림으로!

### Tip 3. 즐겨찾는 SQL 저장
SQL Editor에서 `Ctrl + S` 누르면 `.sql` 파일로 저장 가능.
자주 쓰는 쿼리는 저장해두면 편해요.

### Tip 4. 다크 테마
`Window` → `Preferences` → `User Interface` → `Appearance` → `Dark`

---

## 📚 더 알아보기

- [DBeaver 공식 문서](https://dbeaver.com/docs/dbeaver/)
- [MySQL 8.0 공식 문서](https://dev.mysql.com/doc/refman/8.0/en/)

화이팅! 🦁
