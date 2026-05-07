
# 💎 Gemini CLI 사용법

> 터미널에서 Google의 AI 모델 **Gemini**를 바로 쓸 수 있는 도구예요.
> ChatGPT처럼 대화도 되고, 코드 분석·수정도 시켜볼 수 있어요.

---

## 🤔 Gemini CLI가 뭔가요?

> **터미널 기반 AI 코딩 에이전트.**
> Claude Code, Cursor, Copilot CLI 같은 친구예요. 무료 사용량이 넉넉해서 학생들이 써보기 좋아요.

### 비슷한 도구들 비교

| 도구 | 회사 | 특징 |
|---|---|---|
| **Gemini CLI** | Google | 무료 한도 큼, Google 계정 로그인 |
| Claude Code | Anthropic | 깊이 있는 코드 작업 |
| GitHub Copilot CLI | GitHub | GitHub 연동 강함 |
| Cursor | Anysphere | IDE 통합형 |

---

## 📦 설치하기

### 0단계. 사전 요구사항
- **Node.js 18 이상** 설치되어 있어야 해요.
  ```bash
  node --version
  ```
  → `v18.0.0` 이상이면 OK. 없거나 낮으면 https://nodejs.org 에서 LTS 버전 설치.

### 1단계. 설치

#### 방법 A. 글로벌 설치 (권장)
```bash
npm install -g @google/gemini-cli
```

#### 방법 B. npx로 매번 실행 (설치 안 함)
```bash
npx https://github.com/google-gemini/gemini-cli
```

### 2단계. 설치 확인
```bash
gemini --version
```
→ 버전 번호 뜨면 성공!

---

## 🔑 첫 실행 & 로그인

### 1. 프로젝트 폴더로 이동
```bash
cd ~/Desktop/likelion-market
```

### 2. 실행
```bash
gemini
```

### 3. 로그인 (브라우저 자동 열림)
- **Login with Google** 선택 → 본인 구글 계정으로 로그인
- "Authentication successful" 뜨면 끝!

> 💡 무료 한도: Gemini 2.5 Pro 모델 기준 분당·일일 사용량이 꽤 넉넉해요.
> 학생 실습에 무리 없는 양.

---

## 💬 기본 사용법

### 대화형 모드 (Interactive)
`gemini` 실행하면 채팅창이 열려요. 자연어로 물어보세요.

```
> 이 프로젝트 구조를 설명해줘

> AuthService.java 코드 좀 봐줘

> JWT가 뭔지 초보자가 이해할 수 있게 설명해줘

> README.md 파일에 설치 가이드 섹션 추가해줘
```

### 한 번만 묻고 끝내기 (Non-interactive)
```bash
gemini -p "이 프로젝트가 뭐 하는 거야?"
```

### 종료
대화창에서 `/quit` 또는 **Ctrl + C** 두 번

---

## ⌨️ 자주 쓰는 슬래시 명령어

| 명령어 | 설명 |
|---|---|
| `/help` | 도움말 |
| `/clear` | 대화 내용 지우기 |
| `/quit` | 종료 |
| `/chat save 이름` | 대화 저장 |
| `/chat resume 이름` | 저장한 대화 이어가기 |
| `/tools` | 사용 가능한 도구 목록 |
| `/memory show` | 기억하고 있는 컨텍스트 보기 |

> 💡 정확한 명령어 목록은 버전마다 살짝 다를 수 있어요. `/help` 로 확인!

---

## 🎯 학생 실습에서 활용 예시

### 예시 1. 코드 설명 받기
```
> AuthService.java의 signup 메서드를 한 줄씩 주석 달아서 설명해줘
```

### 예시 2. 에러 디버깅
```
> 이 에러가 뭘 의미하는지 알려줘:
> "Public Key Retrieval is not allowed"
```

### 예시 3. TODO 힌트 받기
```
> JwtFilter.java를 보고, TODO 부분을 어떻게 채워야 할지
> 정답을 바로 주지 말고 힌트만 줘
```

### 예시 4. 커밋 메시지 작성
```
> 지금 변경된 파일들 봐주고, 커밋 메시지 추천해줘
```

### 예시 5. 코드 리팩터링
```
> ProductService.java에 중복 코드가 있는 것 같아. 리팩터링 제안해줘
```

---

## 🛡️ 컨텍스트 / 메모리 파일

### `GEMINI.md`
프로젝트 루트에 `GEMINI.md` 파일을 만들어두면, Gemini가 매번 이 내용을 읽고 시작해요.

**예시 `GEMINI.md`:**
```markdown
# 멋사 중고마켓 프로젝트

## 기술 스택
- Spring Boot 3.2.5 + Java 21
- MySQL + JPA
- JWT 인증

## 코딩 규칙
- 한국어 주석으로
- TODO 표시는 ① ② ③ 형태로

## 절대 금지
- application.yml의 비밀번호 노출
- 정답을 직접 알려주지 말 것 (힌트만)
```

> 💡 학생들이 실습하는 환경에서, 강사가 미리 `GEMINI.md`를 작성해두면
> AI가 정답을 너무 쉽게 알려주지 않게 통제 가능!

---

## 📁 파일 다루기

### 파일 참조하기
대화 중에 `@파일명` 으로 특정 파일을 가리킬 수 있어요.

```
> @src/main/java/com/likelion/market/service/AuthService.java 이 파일 분석해줘
```

### 디렉토리 통째로
```
> @src/main/java/ 이 폴더 구조 설명해줘
```

> 💡 `@`만 누르면 자동완성으로 파일 목록 떠요!

---

## 🚨 자주 나는 문제

### ❌ "command not found: gemini"
> 설치가 안 됐거나 PATH에 없어요.
> ```bash
> npm install -g @google/gemini-cli
> ```
> 그래도 안 되면 npm 전역 경로 확인:
> ```bash
> npm config get prefix
> ```

### ❌ Node 버전이 낮아요
> Node.js 18 미만에선 안 돌아가요. 최신 LTS로 업데이트:
> - Mac: `brew upgrade node`
> - Windows: https://nodejs.org 에서 LTS 다운로드

### ❌ 로그인 페이지가 안 열려요
> 방화벽이나 사내망 문제일 수 있어요.
> API key 방식으로도 인증 가능 (Google AI Studio에서 키 발급).
> ```bash
> export GEMINI_API_KEY="발급받은_키"
> ```

### ❌ 응답이 느려요
> Gemini 서버 부하 문제일 수 있어요.
> 네트워크 확인 + 잠시 후 재시도.

### ❌ 사용 한도 초과
> 무료 한도를 다 썼어요. 다음날까지 기다리거나, 유료 API key 사용.

---

## 💡 효과적으로 쓰는 팁

### Tip 1. 구체적으로 물어보기
- ❌ "고쳐줘"
- ✅ "AuthController의 signup 메서드가 500을 반환하는데, 적절한 에러 핸들링 추가해줘"

### Tip 2. 단계적으로
한 번에 큰 변경 시키지 말고, 작은 단위로 쪼개서 검증하면서 진행.

### Tip 3. 직접 검증 필수
AI가 생성한 코드는 **반드시 직접 실행하고 테스트**하세요.
배우는 단계에선 더더욱!

### Tip 4. 학습 모드로 쓰기
"정답을 주지 말고, 어디서 찾아봐야 할지만 알려줘" 라고 물어보세요.
실습의 의미가 살아요.

### Tip 5. Git과 함께
중요한 변경 전엔 항상 `git commit`으로 세이브!
AI가 잘못 고치면 `git restore`로 복구.

---

## 🆚 Claude Code와 비교

| 항목 | Gemini CLI | Claude Code |
|---|---|---|
| 무료 한도 | 큼 (2.5 Pro 분당 한도 큼) | 제한적 |
| 코드 깊이 | 좋음 | 매우 좋음 |
| 에이전트 능력 | 좋음 | 매우 좋음 |
| 슬래시 명령어 | 풍부 | 풍부 |
| 한국어 | 좋음 | 매우 좋음 |

> 💡 둘 다 써보고 본인 손에 맞는 거 쓰는 게 정답!

---

## 📚 더 알아보기

- [공식 GitHub](https://github.com/google-gemini/gemini-cli)
- [Google AI Studio](https://aistudio.google.com/) — API key 발급
- [Gemini 모델 정보](https://ai.google.dev/)

화이팅! 🦁
