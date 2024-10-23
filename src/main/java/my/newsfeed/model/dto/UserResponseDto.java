package my.newsfeed.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDto {
    private String username; // 유저 이름
    private String userPassword; // 유저 비밀번호 (보안상 노출되지 않도록 주의)
    private String userEmail; // 유저 이메일
    private String userImageUrl; // 프로필 이미지 URL
    private String backgroundImageUrl; // 배경 이미지 URL
    private String introduce; // 자기소개
    private boolean isPrivate; // 프로필 공개여부 (true = 비공개)

    // 친구 목록 추가 (서민기)
    private List<Long> friendIds; // 친구 IDs (추가)
}
