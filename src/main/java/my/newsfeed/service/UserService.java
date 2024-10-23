package my.newsfeed.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import my.newsfeed.model.dto.UserRequestDto;
import my.newsfeed.model.dto.UserResponseDto;
import my.newsfeed.jwt.JwtUtil;
import my.newsfeed.model.dto.LoginRequestDto;
import my.newsfeed.model.dto.SignupRequestDto;
import my.newsfeed.model.entity.User;
import my.newsfeed.model.entity.UserRoleEnum;
import my.newsfeed.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 프로필 조회
    public UserResponseDto getUserProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
    public void signup(SignupRequestDto requestDto) {
      String username = requestDto.getUsername();
      String password = passwordEncoder.encode(requestDto.getPassword());

        // User 엔티티를 UserResponseDto로 변환
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUsername(user.getUsername());
        responseDto.setUserEmail(user.getUserEmail());
        responseDto.setUserImageUrl(user.getUserImageUrl());
        responseDto.setBackgroundImageUrl(user.getBackgroundImageUrl());
        responseDto.setIntroduce(user.getIntroduce());
        responseDto.setPrivate(user.isPrivate());
      // 회원 중복 확인
      Optional<User> checkUsername = userRepository.findByUserName(username);
      if (checkUsername.isPresent()) {
        throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
      }

        return responseDto;
    }
      // email 중복확인
      String email = requestDto.getEmail();
      Optional<User> checkEmail = userRepository.findByUserEmail(email);
      if (checkEmail.isPresent()) {
        throw new IllegalArgumentException("중복된 Email 입니다.");
      }

    // 프로필 공개/ 비공개 설정
    public void updateProfile(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
      // 사용자 ROLE 확인
      UserRoleEnum role = UserRoleEnum.USER;
      if (requestDto.isAdmin()) {
        if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
          throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
        }
        role = UserRoleEnum.ADMIN;
      }

        // 프로필 공개 여부 설정
        user.setPrivate(requestDto.isPrivate());
        userRepository.save(user);
      // 사용자 등록
      User user = new User(username,password,email,role);
      userRepository.save(user);
    }

    // 프로필 이미지 업데이트
    public void updateProfileImage(Long id, String imageUrl) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
      String username = requestDto.getUsername();
      String password = requestDto.getPassword();

      //사용자 확인
      User user = userRepository.findByUserName(username).orElseThrow(()-> new IllegalArgumentException("등록된 사용자가 없습니다."));

      //비밀번호 확인
      if (!passwordEncoder.matches(password, user.getUserPassword())) {
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
      }

        // requestDto에서 이미지 URL 가져오기
        user.setUserImageUrl(imageUrl);
        userRepository.save(user);
      //Jwt 생성 및 쿠키에 저장 후 response 객체에 추가
      String token = jwtUtil.createToken(user.getUserName(),user.getRole());
      jwtUtil.addJwtToCookie(token,res);
    }

    // 배경 이미지 업데이트
    public void updateBackgroundImage(Long id, String requestDto) {
    // **회원 탈퇴 기능**
    public void deleteUser(String username, String password) {
      // 사용자 조회
      User user = userRepository.findByUserName(username)
              .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

    }
      // 비밀번호 확인
      /*if (!passwordEncoder.matches(password, user.getUserPassword())) {
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
      }*/

    public void updateProfileVisibility(Long id, UserRequestDto requestDto) {
      // 사용자 삭제 (완전 삭제 또는 비활성화)
      userRepository.delete(user);
    }
}
