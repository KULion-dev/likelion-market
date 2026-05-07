# 🐬 DBeaver 사용법 가이드

> MySQL 데이터베이스를 시각적으로 다루는 무료 도구예요.
> 우리는 회원·상품 데이터가 잘 저장됐는지 눈으로 확인할 때 사용해요.

---

## 0. 설치 (아직 안 했다면)

👉 https://dbeaver.io/download/ → **Community Edition** 다운로드 → 설치

---

## 🪜 MySQL 연결하기

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

### 4단계. SSL/타임존 옵션 추가 (중요!)

`Driver properties` 탭으로 이동 → 아래 항목 추가/수정:

| Property | Value |
|---|---|
| `allowPublicKeyRetrieval` | `true` |
| `useSSL` | `false` |
| `serverTimezone` | `Asia/Seoul` |

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
