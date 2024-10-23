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
1. 프로필 이미지 업로드
URL: /api/users/{id}/uploadProfileImage
Method: POST
설명: 사용자의 프로필 이미지를 업로드합니다.
Path Parameter:
id: 사용자 ID (Long)
Request Parameters:
file: 업로드할 이미지 파일 (MultipartFile)
Response:
성공 시: 이미지 업로드 경로 반환 (String)
실패 시: 에러 메시지 반환
에러 처리:
이미지 파일이 없거나 잘못된 경우 IOException 발생

2. 로그인 페이지
URL: /api/user/login-page
Method: GET
설명: 로그인 페이지를 불러옵니다.
Response:
성공 시: 로그인 페이지 텍스트 반환

3. 회원 가입
URL: /api/user/signup
Method: POST
설명: 사용자를 회원으로 가입시킵니다.
Request Body:
SignupRequestDto: 회원 가입에 필요한 정보를 담은 DTO
username (String): 사용자 이름
password (String): 사용자 비밀번호
email (String): 이메일 주소
admin (boolean): 관리자인지 여부 (기본값: false)
adminToken (String): 관리자를 위한 토큰 (선택적)
Response:
성공 시: 회원 가입 성공 메시지 반환
실패 시: 예외 처리로 오류 메시지 반환
서비스 로직: UserService.signup(SignupRequestDto requestDto)
비밀번호 암호화(PasswordEncoder)
ADMIN_TOKEN을 통한 관리 권한 설정

4. 프로필 이미지 삭제
URL: /api/users/{id}/deleteProfileImage
Method: DELETE
설명: 사용자의 프로필 이미지를 삭제합니다.
Path Parameter:
id: 사용자 ID (Long)
Response:
성공 시: 삭제 완료 메시지 반환
실패 시: 에러 메시지 반환

5. 게시물 생성
URL: /api/boards
Method: POST
설명: 새로운 게시물을 생성합니다.
Request Body:
BoardRequestDto: 게시물 생성에 필요한 데이터를 담은 DTO
Response:
성공 시: 생성된 게시물의 정보 (BoardResponseDto) 반환
실패 시: 예외 처리로 오류 메시지 반환
HTTP Status: 201 Created

6. 게시물 목록 조회
URL: /api/boards
Method: GET
설명: 전체 게시물 목록을 조회합니다.
Request Parameters:
page: 페이지 번호 (기본값 0)
size: 페이지 크기 (선택적)
Response:
성공 시: 페이지별 게시물 목록 (BoardResponsePage) 반환
실패 시: 오류 메시지 반환

7. 게시물 단일 조회
URL: /api/boards/{id}
Method: GET
설명: 특정 ID에 해당하는 게시물을 조회합니다.
Path Parameter:
id: 게시물 ID (Long)
Response:
성공 시: 게시물 정보 (BoardResponseDto) 반환
실패 시: 해당 게시물이 없는 경우 예외 처리

8. 게시물 삭제
URL: /api/boards/{id}
Method: DELETE
설명: 특정 게시물을 삭제합니다.
Path Parameter:
id: 게시물 ID (Long)
Response:
성공 시: 삭제 성공 메시지 반환
실패 시: 예외 처리
## ERD
---
<img width="339" alt="스크린샷 2024-10-23 오후 9 02 28" src="https://github.com/user-attachments/assets/63700307-ca9c-444d-abf4-7677945b452f">
