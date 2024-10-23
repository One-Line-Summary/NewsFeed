package my.newsfeed.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.newsfeed.model.dto.LoginRequestDto;
import my.newsfeed.model.dto.SignupRequestDto;
import my.newsfeed.model.dto.UserRequestDto;
import my.newsfeed.model.dto.UserResponseDto;
import my.newsfeed.model.entity.User;
import my.newsfeed.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 프로필 조회
    public UserResponseDto getUserProfile(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        // User 엔티티를 UserResponseDto로 변환
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUsername(user.getUserName());
        responseDto.setUserEmail(user.getUserEmail());
        responseDto.setUserImageUrl(user.getUserImageUrl());
        responseDto.setBackgroundImageUrl(user.getBackgroundImageUrl());
        responseDto.setIntroduce(user.getIntroduce());
        responseDto.setPrivate(user.isPrivate());

        return responseDto;
    }

    // 프로필 공개/ 비공개 설정
    public void updateProfile(Long id, UserRequestDto requestDto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        // 프로필 공개 여부 설정
        user.setPrivate(requestDto.isPrivate());
        userRepository.save(user);
    }

    // 프로필 이미지 업데이트
    public void updateProfileImage(Long id, String imageUrl) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        // 프로필 이미지 URL 업데이트
        user.setUserImageUrl(imageUrl);
        userRepository.save(user);
    }

    // 배경 이미지 업데이트
    public void updateBackgroundImage(Long id, String imageUrl) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        // 배경 이미지 업데이트 로직 추가
        user.setBackgroundImageUrl(imageUrl);
        userRepository.save(user);
    }

    // 회원가입
    public void signup(SignupRequestDto signupRequestDto) {
        // 회원가입 로직 추가 필요
    }

    // 로그인
    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        // 로그인 로직 추가 필요
    }

    // 유저 삭제
    public void deleteUser(String username, String password) {
        // 유저 삭제 로직 추가 필요
    }

    public void updateProfileVisibility(Long id, UserRequestDto requestDto) {
    }

    // **User 정보 업데이트 기능 추가**
    @Transactional
    public UserRequestDto updateUser(Long userid, UserRequestDto userRequestDto) {
        // 유저 ID로 조회
        Optional<User> optionalUser = userRepository.findById(userid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // 유저 정보 업데이트
            user.updateUsername(userRequestDto.getUsername());
            user.updateUserEmail(userRequestDto.getUserEmail());
            user.updateIntroduce(userRequestDto.getIntroduce());
            user.updateIsPrivate(userRequestDto.isPrivate());

            // 변경된 정보 저장
            userRepository.save(user);

            return userRequestDto;
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
