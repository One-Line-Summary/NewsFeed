package my.newsfeed.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import my.newsfeed.exception.UserNotFoundException;
import my.newsfeed.model.dto.UserRequestDto;
import my.newsfeed.model.dto.UserResponseDto;
import my.newsfeed.model.entity.User;
import my.newsfeed.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 프로필 조회
    public UserResponseDto getUserProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));

        // User 엔티티를 UserResponseDto로 변환
        UserResponseDto responseDto = new UserResponseDto();
        responseDto.setUsername(user.getUsername());
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
    public void updateBackgroundImage(Long id, String imageUrl) {
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("유저를 찾지 못했습니다."));
        user.setBackgroundImageUrl(imageUrl);
        userRepository.save(user);
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
