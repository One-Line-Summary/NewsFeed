# NewsFeed
### 팀노션
---
[한 줄 정리 해주시조](https://www.notion.so/teamsparta/1232dc3ef51481808460cf50e30e40d9)

---
## 팀원 파트
---
* 팀장 이시우: 뉴스피드CRUD
* 팀원 김혜진: 유저 프로필
* 팀원 김도현: 프로필 수정 및 조회
* 팀원 박서희: 사용자인증
* 팀원 서민기: 친구관리
---
## API
API 명세서
회원가입
URL: /api/user/signup

로그인
URL: /api/user/login

회원탈퇴
URL: /api/user/delete

프로필이미지 업로드
URL: /api/user/{id}/uploadProfileImage

배경이미지 업로드
URL: /api/user/{id}/uploadBackgroundImage

프로필 공개/비공개 설정
URL: /api/user/{id}/setProfileVisibility

프로필 조회
URL: /api/user/{id}/isProfilePrivate

프로필 수정
URL: /api/user/{id}

친구추가
URL: /api/user/{id}/friends

친구삭제
URL: /api/user/{id}/friends/{friendId}

친구목록 조회
URL: /api/user/{id}/friends

게시물 생성
URL: /api/boards
Method: POST

게시물 목록 조회
URL: /api/boards?page=0&size=10

게시물 단일 조회
URL: /api/boards/{id}

게시물 삭제
URL: /api/boards/{id}

## ERD
---

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FbblU3h%2FbtsKhfZQ6WS%2FDS0wHcYl2hsMHsUgXNfwtK%2Fimg.png)
