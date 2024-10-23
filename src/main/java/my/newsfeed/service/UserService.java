package my.newsfeed.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import my.newsfeed.jwt.JwtUtil;
import my.newsfeed.model.dto.LoginRequestDto;
import my.newsfeed.model.dto.SignupRequestDto;
import my.newsfeed.model.dto.UserRequestDto;
import my.newsfeed.model.dto.UserResponseDto;
import my.newsfeed.model.entity.User;
import my.newsfeed.model.entity.UserRoleEnum;
import my.newsfeed.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
      String username = requestDto.getUsername();
      String password = passwordEncoder.encode(requestDto.getPassword());

      Optional<User> checkUsername = userRepository.findByUserName(username);
      if (checkUsername.isPresent()) {
        throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
      }

      String email = requestDto.getEmail();
      Optional<User> checkEmail = userRepository.findByUserEmail(email);
      if (checkEmail.isPresent()) {
        throw new IllegalArgumentException("중복된 Email 입니다.");
      }

      UserRoleEnum role = UserRoleEnum.USER;
      if (requestDto.isAdmin()) {
        if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
          throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
        }
        role = UserRoleEnum.ADMIN;
      }

      User user = new User(username,password,email,role);
      userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
      String username = requestDto.getUsername();
      String password = requestDto.getPassword();

      User user = userRepository.findByUserName(username).orElseThrow(()-> new IllegalArgumentException("등록된 사용자가 없습니다."));

      if (!passwordEncoder.matches(password, user.getUserPassword())) {
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
      }

      String token = jwtUtil.createToken(user.getUserName(),user.getRole());
      jwtUtil.addJwtToCookie(token,res);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        if (user.isDeleted()) {
            throw new IllegalArgumentException("이미 탈퇴한 사용자입니다.");
        }

        user.setDeleted(true);
        userRepository.save(user);
    }

    // 배경 이미지 업데이트
    public void updateBackgroundImage(Long id, String backgroundImageUrl) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        user.setBackgroundImageUrl(backgroundImageUrl);
        userRepository.save(user);
    }

    public void updateProfileVisibility(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        user.setPrivate(requestDto.isPrivate());
        userRepository.save(user);

    }

    public void updateProfileImage(Long id, String filePath) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        user.setUserImageUrl(filePath);
        userRepository.save(user);
    }

    public UserResponseDto getUserProfile(Long id) {
        return null;
    }

    public UserRequestDto updateUser(Long userid, @Valid UserRequestDto userRequestDto) {
        return null;
    }

    public void deleteUser(String username, String password) {
    }

    // 친구 추가 메서드
    public User addFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
        User friend = userRepository.findById(friendId).orElseThrow(() -> new IllegalArgumentException("친구를 찾지 못했습니다."));
        user.addFriend(friend); // 친구 추가
        return userRepository.save(user); // 친구 추가 후 저장
    }
    // 친구 삭제 메서드
    public User removeFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
        User friend = userRepository.findById(friendId).orElseThrow(() -> new IllegalArgumentException("친구를 찾지 못했습니다."));
        user.removeFriend(friend); // 친구 삭제
        return userRepository.save(user); // 친구 삭제 후 저장
    }
    // 친구 목록 조회 메서드
    public Set<User> getFriends(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
        return user.getFriends(); // 친구 목록 반환
    }

}
