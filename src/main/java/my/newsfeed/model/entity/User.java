package my.newsfeed.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId; // 유저 ID
    @Column(nullable = true)
    private String userName; // 유저 이름
    @Column(nullable = true)
    private String userPassword; // 유저 비밀번호
    @Column(nullable = true)
    private String userEmail; // 유저 이메일
    @Column(nullable = true)
    private String userImageUrl; // 프로필 이미지 URL
    @Column(nullable = true)
    private String backgroundImageUrl; // 배경 이미지 URL
    @Column(nullable = true)
    private String introduce; // 자기소개
    @Column(nullable = true)
    private boolean isPrivate; //프로필 공개여부(true = 비공개)
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //enum타입
    private UserRoleEnum role;

    public User(String username, String password, String email, UserRoleEnum role) {
        this.userName = username;
        this.userPassword = password;
        this.userEmail = email;
        this.role = role;
    }

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
