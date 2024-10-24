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


##트러블슈팅
다음은 트러블슈팅에 대한 요약입니다.

1. MultipartFile vs File 차이점
MultipartFile은 HTTP 요청을 통해 파일을 업로드할 때 주로 사용되는 객체로, 파일의 원래 이름을 getOriginalFilename() 메서드를 통해 가져올 수 있습니다.
getOriginalFilename()은 파일의 원래 이름을 저장하거나 데이터베이스에 기록해야 할 때 유용하며, 사용자에게 피드백으로 파일 이름을 보여줄 때 사용됩니다.
File 객체는 파일을 생성하는 것이 아니라, 파일이나 디렉토리의 경로를 지정하고 이를 다루기 위한 객체로, 파일의 존재 여부 확인, 파일 생성 및 삭제 등 다양한 파일 작업을 수행할 수 있습니다.

2. Repository 설계
Spring Data JPA에서는 일반적으로 리포지토리를 인터페이스로 선언하고, 해당 인터페이스가 JpaRepository를 상속받도록 설계합니다.
인터페이스는 별도의 클래스로 중첩되지 않으며, 인터페이스 정의와 구현은 분리됩니다.

3. Boolean 타입 필드의 Getter/Setter
isPrivate()은 자바에서 boolean 타입 필드의 표준 getter 메서드 명명 규칙을 따릅니다. 필드가 boolean일 경우 isPrivate()와 같은 형태의 메서드가 자동으로 생성됩니다.
필드명이 Boolean isPrivate이 아닌 한, boolean 타입에는 getIsPrivate()를 사용하지 않고, 대신 isPrivate()를 사용하는 것이 더 자연스럽습니다.
Setter는 setPrivate() 형태로 생성되므로, setIsPrivate() 대신 setPrivate()을 사용해야 합니다.

4. DTO 사용 규칙
Controller와 Service에서 사용하는 DTO는 목적에 맞게 분리하여 사용해야 합니다.
단일 DTO로 모든 작업을 처리하려다 보면 오류가 발생할 수 있습니다. 예를 들어, String 타입의 DTO에서 특정 객체 메서드(예: getUserImageUrl())를 호출하려 하면 오류가 발생할 수 있습니다.

5. Static 메서드 사용의 문제점
BoardController에서 BoardService의 메서드를 호출할 때, 해당 메서드를 static으로 정의하려는 오류가 발생하는 이유는, BoardService가 Spring의 Bean으로 관리되는 서비스 클래스이기 때문입니다.
해결 방법: @Autowired 또는 @RequiredArgsConstructor를 사용해 BoardService의 인스턴스를 주입받아야 합니다. Static 메서드는 Spring의 DI 방식과 맞지 않으므로 지양해야 합니다.
대문자 사용 규칙

6. 대문자로 시작하는 클래스명은 static 메서드여야 하며, 이를 호출할 때 클래스명.메서드()의 형태로 사용됩니다.
일반 메서드는 인스턴스를 생성한 후에 호출해야 하며, 변수명을 통해 접근합니다.
Postman에서 null 반환 문제

7. Postman에서 계속해서 null이 반환되던 이유는, ResponseDto에 값을 할당하지 않았기 때문입니다. ResponseDto에 올바른 값을 넣어주면 이 문제가 해결됩니다.
