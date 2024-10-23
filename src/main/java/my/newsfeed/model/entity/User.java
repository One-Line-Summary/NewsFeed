package my.newsfeed.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;
    @Column(nullable = true)
    private String userName; //유저 이름
    @Column(nullable = true)
    private String userPassword; //유저 비밀번호
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
    @Column(nullable = true)
    private boolean isDeleted = false;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //enum타입
    private UserRoleEnum role;

    public User(String username, String password, String email, UserRoleEnum role) {
        this.userName = username;
        this.userPassword = password;
        this.userEmail = email;
        this.role = role;
    }

    // 친구 관계를 관리하기 위한 필드 추가
    @ManyToMany
    @JoinTable(
            name = "friendship",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>(); // 친구 목록
    public void addFriend(User friend) {
        this.friends.add(friend);
        friend.getFriends().add(this); // 양방향 관계 설정
    }
    public void removeFriend(User friend) {
        this.friends.remove(friend);
        friend.getFriends().remove(this); // 양방향 관계 설정
    }

}
