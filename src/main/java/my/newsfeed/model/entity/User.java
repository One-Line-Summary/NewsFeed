package my.newsfeed.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    private String username; //유저 이름
    private String userPassword; //유저 비밀번호
    private String userEmail; // 유저 이메일
    private String userImage; // 프로필 이미지
    private String backgroundImage; // 배경 이미지
    private String introduce; // 자기소개
    private boolean isPrivate; //프로필 공개여부(true = 비공개)

}
