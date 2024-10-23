package my.newsfeed.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

    public void signup(SignupRequestDto requestDto) {
      String username = requestDto.getUsername();
      String password = passwordEncoder.encode(requestDto.getPassword());

      // 회원 중복 확인
      Optional<User> checkUsername = userRepository.findByUserName(username);
      if (checkUsername.isPresent()) {
        throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
      }

      // email 중복확인
      String email = requestDto.getEmail();
      Optional<User> checkEmail = userRepository.findByUserEmail(email);
      if (checkEmail.isPresent()) {
        throw new IllegalArgumentException("중복된 Email 입니다.");
      }

      // 사용자 ROLE 확인
      UserRoleEnum role = UserRoleEnum.USER;
      if (requestDto.isAdmin()) {
        if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
          throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
        }
        role = UserRoleEnum.ADMIN;
      }

      // 사용자 등록
      User user = new User(username,password,email,role);
      userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
      String username = requestDto.getUsername();
      String password = requestDto.getPassword();

      //사용자 확인
      User user = userRepository.findByUserName(username).orElseThrow(()-> new IllegalArgumentException("등록된 사용자가 없습니다."));

      //비밀번호 확인
      if (!passwordEncoder.matches(password, user.getUserPassword())) {
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
      }

      //Jwt 생성 및 쿠키에 저장 후 response 객체에 추가
      String token = jwtUtil.createToken(user.getUserName(),user.getRole());
      jwtUtil.addJwtToCookie(token,res);
    }

    // **회원 탈퇴 기능**
    public void deleteUser(String username, String password) {
      // 사용자 조회
      User user = userRepository.findByUserName(username)
              .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

      // 비밀번호 확인
      /*if (!passwordEncoder.matches(password, user.getUserPassword())) {
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
      }*/

      // 사용자 삭제 (완전 삭제 또는 비활성화)
      userRepository.delete(user);
    }
}
