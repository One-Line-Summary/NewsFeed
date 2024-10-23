package my.newsfeed.model.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private String username; //유저 이름 (수정 시 사용)
    private String userEmail; // 유저 이메일 (수정 시 사용)
    private String introduce; // 자기소개 (수정 시 사용)
    private boolean isPrivate; //프로필 공개여부(true = 비공개)
    private String userImageUrl; // 프로필 이미지 URL (추가)
    private String backgroundImageUrl; // 배경 이미지 URL (추가)
}
