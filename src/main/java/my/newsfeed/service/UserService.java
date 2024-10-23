package my.newsfeed.service;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.newsfeed.model.dto.LoginRequestDto;
import my.newsfeed.model.dto.SignupRequestDto;
import my.newsfeed.exception.UserNotFoundException;
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
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

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
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        // 프로필 공개 여부 설정
        user.setPrivate(requestDto.isPrivate());
        userRepository.save(user);
    }

    // 프로필 이미지 업데이트
    public void updateProfileImage(Long id, String imageUrl) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));


        // requestDto에서 이미지 URL 가져오기
        user.setUserImageUrl(imageUrl);
        userRepository.save(user);
    }

    // 배경 이미지 업데이트
    public void updateBackgroundImage(Long id, String requestDto) {

    }

    public void updateProfileVisibility(Long id, UserRequestDto requestDto) {
    }

    public void signup(SignupRequestDto signupRequestDto) {

    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
    }

    @Transactional
    public UserRequestDto updateUser(Long userid, UserRequestDto userRequestDto) {
        // Find user by id
        Optional<User> optionalUser = userRepository.findById(userid);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            // Update fields
            user.updateUsername(userRequestDto.getUsername());
            user.updateUserEmail(userRequestDto.getUserEmail());
            user.updateIntroduce(userRequestDto.getIntroduce());
            user.updateIsPrivate(userRequestDto.isPrivate());

            // Save updated user
            userRepository.save(user);

            return userRequestDto;
        } else {
            throw new UserNotFoundException("User not found");
        }
    }
}

    public void deleteUser(String username, String password) {
    }
}