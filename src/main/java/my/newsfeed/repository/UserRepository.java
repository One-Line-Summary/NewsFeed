package my.newsfeed.repository;

import my.newsfeed.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 특정 유저의 친구 목록을 가져오는 메서드
    List<User> findFriendsByUserId(Long userId);

    // 친구 추가를 위한 메서드 (예: 유저 ID로 친구를 찾는 방법)
    User findByUsername(String username);

    // 특정 유저가 친구를 삭제하는 메서드 (예: 두 유저 ID로 친구 삭제)
    void deleteByIdAndFriendId(Long userId, Long friendId);
}
