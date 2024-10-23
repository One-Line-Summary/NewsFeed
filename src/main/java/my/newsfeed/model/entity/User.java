package my.newsfeed.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid; // 유저 ID
    private String username; // 유저 이름
    private String userPassword; // 유저 비밀번호
    private String userEmail; // 유저 이메일
    private String userImageUrl; // 프로필 이미지 URL
    private String backgroundImageUrl; // 배경 이미지 URL
    private String introduce; // 자기소개
    private boolean isPrivate; //프로필 공개여부(true = 비공개)

    public void updateUsername(String username) {
        this.username = username;
    }

    public void updateUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public void updateIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public void updateIsPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
}
