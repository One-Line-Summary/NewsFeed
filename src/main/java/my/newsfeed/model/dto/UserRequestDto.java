package my.newsfeed.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequestDto {
    private String username; // 유저 이름 (수정 시 사용)
    private String userEmail; // 유저 이메일 (수정 시 사용)
    private String introduce; // 자기소개 (수정 시 사용)
    private boolean isPrivate; // 프로필 공개여부 (true = 비공개)
    private String userImageUrl; // 프로필 이미지 URL (추가)
    private String backgroundImageUrl; // 배경 이미지 URL (추가)

    // 친구 추가 및 삭제 시 사용할 친구 ID 리스트 (서민기)
    private List<Long> friendIds; // 친구 IDs (추가)
}
