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
API 명세서1. 회원 가입
URL: /api/user/signup
Method: POST
JSON 코드:
  "username": "newuser",
  "password": "NewPass123!",
  "email": "newuser@example.com",
  "admin": false,
  "adminToken": ""

2. 게시물 생성
URL: /api/boards
Method: POST
JSON 코드:
  "title": "게시물 제목",
  "content": "게시물 내용입니다.",
  "author": "작성자 이름"

3. 게시물 목록 조회
URL: /api/boards?page=0&size=10
Method: GET

4. 게시물 단일 조회
URL: /api/boards/{id}
Method: GET

5. 게시물 삭제
URL: /api/boards/{id}
Method: DELETE

6. 프로필 이미지 삭제
URL: /api/users/{id}/deleteProfileImage
Method: DELETE

7. 프로필 이미지 업로드
URL: /api/users/{id}/uploadProfileImage
Method: POST
Postman 설정:
form-data 형식에서 파일 업로드.
Key: file (파일 첨부), id는 URL에 포함.

8. 프로필 이미지 삭제
URL: /api/users/{id}/deleteProfileImage
Method: DELETE
)

## ERD
---
<img width="339" alt="스크린샷 2024-10-23 오후 9 02 28" src="https://github.com/user-attachments/assets/63700307-ca9c-444d-abf4-7677945b452f">
