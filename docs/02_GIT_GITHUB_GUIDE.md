# 🐙 Git & GitHub 명령어 정리

> 코드를 시간여행 시키는 마법 도구예요. 처음엔 어색해도 익숙해지면 절대 못 떠나요.

---

## 🧭 Git vs GitHub 차이

| Git | GitHub |
|---|---|
| 내 컴퓨터에 깔린 **버전 관리 도구** | 코드를 올려두는 **클라우드 서비스** |
| 오프라인에서도 작동 | 인터넷 필요 |
| 무료, 오픈소스 | 회사 (현재 Microsoft 소유) |

> 💡 비유: **Git = 워드의 "변경 내역 추적"**, **GitHub = 구글 드라이브**

---

## 📚 꼭 알아야 할 핵심 용어 7개

### ① Repository (저장소, 줄여서 "레포")
> **프로젝트 폴더 + 모든 변경 기록**을 합친 것.
> 우리 `likelion-market` 폴더가 곧 하나의 레포예요.

### ② Commit (커밋)
> **"여기까지 작업한 거 저장!" 하는 스냅샷.**
> 게임의 "세이브 포인트" 같은 거예요. 언제든 이 시점으로 돌아올 수 있어요.

### ③ Branch (브랜치)
> **평행우주처럼 갈라져 나온 작업 줄기.**
> `main` 브랜치는 본진, 기능 개발할 땐 `feature/login` 같은 새 가지를 만들어 거기서 작업.

### ④ Remote (원격 저장소)
> **GitHub에 있는 내 레포의 복사본.**
> 보통 `origin`이라는 이름으로 부르고, "오리진에 푸시한다"고 말해요.

### ⑤ Push (푸시)
> **내 컴퓨터의 커밋 → GitHub에 올리기.**
> "내 작업 GitHub에 올려둘게"

### ⑥ Pull (풀)
> **GitHub의 최신 내용 → 내 컴퓨터로 받기.**
> "팀원이 올린 거 내려받을게"

### ⑦ Merge (머지)
> **두 브랜치를 합치기.**
> 기능 개발 끝나면 `feature/login`을 `main`에 merge.

---

## 🔄 일반적인 작업 흐름

```
   ┌──────────────────────────────────────────────────┐
   │  1. 작업 시작 전: git pull (최신 받기)            │
   │  2. 코드 수정                                     │
   │  3. git status (뭐 바뀌었나 확인)                 │
   │  4. git add . (변경사항을 커밋 대기열에 올림)     │
   │  5. git commit -m "메시지" (스냅샷 저장)         │
   │  6. git push (GitHub에 업로드)                   │
   └──────────────────────────────────────────────────┘
                        ↻ 반복
```

---

## 🛠️ 자주 쓰는 명령어

### 시작할 때

```bash
# Git 초기 설정 (한 번만!)
git config --global user.name "본인이름"
git config --global user.email "본인이메일@example.com"

# 새 프로젝트 시작 (이미 있는 폴더를 Git 레포로)
git init

# 남의 프로젝트 가져오기
git clone https://github.com/사용자명/레포명.git
```

### 매일 쓰는 4총사 ⭐

```bash
# 1. 상태 확인 — "지금 뭐가 바뀐 상태야?"
git status

# 2. 추가 — "이것들을 커밋할 거야"
git add .                  # 변경된 파일 전부
git add README.md          # 특정 파일만

# 3. 커밋 — "스냅샷 찍어!"
git commit -m "회원가입 기능 추가"

# 4. 푸시 — "GitHub에 올려!"
git push
```

### 받아오기

```bash
# GitHub의 최신 변경사항 받아오기
git pull

# 받아오기만 하고 합치진 않기 (확인용)
git fetch
```

### 기록 보기

```bash
# 커밋 기록 보기
git log

# 한 줄로 깔끔하게
git log --oneline

# 그래프로 (브랜치 합쳐진 모양 보임)
git log --oneline --graph --all
```

### 브랜치 다루기

```bash
# 현재 어떤 브랜치인가?
git branch

# 새 브랜치 만들기
git branch feature/login

# 브랜치 이동
git switch feature/login   # 최신 명령어 (Git 2.23+)
git checkout feature/login # 옛날 명령어 (둘 다 작동)

# 브랜치 만들고 바로 이동
git switch -c feature/login

# 브랜치 합치기 (현재 브랜치로 합쳐옴)
git merge feature/login

# 브랜치 삭제
git branch -d feature/login   # 안전 삭제
```

### 되돌리기 (조심!)

```bash
# 아직 add 안 한 변경 취소
git restore 파일명

# add는 했는데 commit 안 한 상태 풀기
git restore --staged 파일명

# 마지막 커밋 메시지 수정
git commit --amend -m "새 메시지"
```

---

## 🌐 GitHub 처음 연결하기

### 시나리오: 이미 있는 로컬 프로젝트를 GitHub에 올리기

```bash
# 1. GitHub 사이트에서 새 레포 만들기 (웹에서)
#    → README, .gitignore 체크박스는 비워두기!

# 2. 로컬 프로젝트 폴더에서:
git init
git add .
git commit -m "최초 커밋"

# 3. 원격 저장소 연결 (GitHub에서 알려준 URL)
git remote add origin https://github.com/사용자명/레포명.git

# 4. 메인 브랜치 이름 통일 (옛날엔 master, 요즘엔 main)
git branch -M main

# 5. 첫 푸시
git push -u origin main
```

> 💡 `-u origin main`은 첫 푸시에만 써요. 다음부턴 그냥 `git push`로 끝.

---

## 🔐 GitHub 인증 (HTTPS의 경우)

2021년부터 비밀번호 인증이 막혔어요. **Personal Access Token (PAT)** 또는 **GitHub CLI** 필요.

### 방법 A. Personal Access Token (간단)

1. GitHub → 우상단 프로필 → `Settings`
2. 좌측 맨 아래 → `Developer settings`
3. `Personal access tokens` → `Tokens (classic)` → `Generate new token`
4. 권한에서 `repo` 체크 → 토큰 발급
5. **이 토큰을 비밀번호 대신 입력!**

> ⚠️ 토큰은 발급 시 한 번만 보여요. 안전한 곳에 복사해두세요.

### 방법 B. GitHub CLI (편함)

```bash
# 설치 (Mac)
brew install gh

# 로그인 (브라우저 자동 열림)
gh auth login
```

→ 이후엔 push/pull 시 자동 인증!

---

## 📝 좋은 커밋 메시지 쓰는 법

### ❌ 나쁜 예
```
git commit -m "수정"
git commit -m "ㅎㅇ"
git commit -m "asdf"
```

### ✅ 좋은 예
```
git commit -m "feat: 회원가입 API 추가"
git commit -m "fix: 로그인 시 401 에러 수정"
git commit -m "docs: README에 Swagger 사용법 추가"
```

### 자주 쓰는 prefix
| Prefix | 의미 |
|---|---|
| `feat:` | 새 기능 |
| `fix:` | 버그 수정 |
| `docs:` | 문서 변경 |
| `refactor:` | 리팩토링 (기능 변화 X) |
| `style:` | 코드 스타일 (들여쓰기 등) |
| `test:` | 테스트 코드 |
| `chore:` | 잡일 (빌드 설정 등) |

---

## 🚨 자주 나는 에러

### ❌ "fatal: not a git repository"
> 현재 폴더가 Git 레포가 아니에요.
> → `git init` 실행하거나, 올바른 폴더로 `cd` 하세요.

### ❌ "Updates were rejected because the remote contains work that you do not have locally"
> 다른 사람이 먼저 푸시했어요.
> → `git pull` 먼저 → 충돌 해결 → 다시 `git push`

### ❌ "Please tell me who you are"
> Git에 이름·이메일 설정 안 했어요.
> ```bash
> git config --global user.name "이름"
> git config --global user.email "이메일"
> ```

### ❌ "Authentication failed"
> 비밀번호 입력했죠? 위의 **Personal Access Token** 또는 **GitHub CLI** 써야 해요.

### ❌ Merge Conflict (충돌)
> 같은 줄을 여러 명이 다르게 수정해서 Git이 어느 쪽 쓸지 모르는 상태.
> 충돌난 파일 열면 이런 모양:
> ```
> <<<<<<< HEAD
> 내가 쓴 코드
> =======
> 다른 사람이 쓴 코드
> >>>>>>> branch-name
> ```
> 둘 중 하나 골라서 (또는 합쳐서) `<<<<<<<`, `=======`, `>>>>>>>` 줄 다 지우고 → `git add` → `git commit`

---

## 📦 .gitignore 파일

> **올리면 안 되는 파일 목록**을 적어두는 파일.
> 비밀번호, 빌드 결과물, IDE 설정 등.

`.gitignore` 예시 (Spring Boot 프로젝트):
```
# 빌드 결과
build/
out/
*.class

# IDE
.idea/
*.iml
.vscode/

# 설정 (비밀번호 등)
application-secret.yml
.env

# OS
.DS_Store
Thumbs.db
```

> 💡 **이미 커밋해버린 파일은 .gitignore에 추가해도 안 빠져요.**
> `git rm --cached 파일명` 으로 한 번 빼주고 다시 커밋해야 함.

---

## 🎯 학생들이 많이 헷갈리는 5가지

### Q1. add랑 commit 차이가 뭐예요?
> **add**는 "이 파일들을 다음 스냅샷에 포함시킬게" (대기열)
> **commit**은 "지금 대기열에 있는 거 진짜 스냅샷 찍어!" (실행)
>
> 비유: 장바구니 담기(add) → 결제(commit)

### Q2. push랑 commit 차이가 뭐예요?
> **commit**은 내 컴퓨터에만 저장 (오프라인 OK)
> **push**는 GitHub로 업로드 (인터넷 필요)

### Q3. main이랑 master는 뭐예요?
> 둘 다 기본 브랜치 이름이에요.
> 옛날엔 `master`였는데 2020년부터 `main`으로 바뀌는 추세예요. 의미는 동일.

### Q4. origin은 누가 정해주는 거예요?
> 본인이 정한 거예요! `git remote add origin <URL>` 명령에서 `origin`이 별명.
> 관습적으로 `origin`이라 부르는 거고, 이론적으론 다른 이름도 가능.

### Q5. 한 번 커밋한 거 지울 수 있어요?
> 가능은 한데 **위험**해요. 특히 푸시한 후엔 더 위험.
> 잘 모르겠으면 그냥 새 커밋으로 덮어쓰는 게 안전.

---

## 📚 더 알아보기

- [GitHub 공식 학습 가이드 (한국어)](https://docs.github.com/ko)
- [Pro Git 책 (무료, 한국어)](https://git-scm.com/book/ko/v2)
- 인터랙티브 학습: https://learngitbranching.js.org/?locale=ko

화이팅! 🦁
