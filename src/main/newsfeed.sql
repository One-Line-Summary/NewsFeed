/*# User 테이블
CREATE TABLE User
(
    user_id        BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_name      VARCHAR(255) NOT NULL,
    user_password  VARCHAR(255) NOT NULL,
    user_email     VARCHAR(255) NOT NULL,
    user_image_url VARCHAR(255),
    introduce      TEXT
);

# Follow 테이블
CREATE TABLE Follow
(
    follow_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    follower_id  BIGINT,
    following_id BIGINT,
    FOREIGN KEY (follower_id) REFERENCES User (user_id),
    FOREIGN KEY (following_id) REFERENCES User (user_id)
);

# Board 테이블
CREATE TABLE Board
(
    board_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    board_title   VARCHAR(255) NOT NULL,
    board_content TEXT         NOT NULL,
    created_date  DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_date DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id       BIGINT,
    FOREIGN KEY (user_id) REFERENCES User (user_id)
);

# Comment 테이블
CREATE TABLE Comment
(
    comment_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    comment_content TEXT NOT NULL,
    create_date     DATETIME DEFAULT CURRENT_TIMESTAMP,
    modified_date   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    user_id         BIGINT,
    board_id        BIGINT,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (board_id) REFERENCES Board (board_id)
);

# User_Board_Like 테이블
CREATE TABLE User_Board_Like
(
    user_board_like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id            BIGINT,
    board_id           BIGINT,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (board_id) REFERENCES Board (board_id)
);

# User_Comment_Like 테이블
CREATE TABLE User_Comment_Like
(
    user_comment_like_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id              BIGINT,
    comment_id           BIGINT,
    FOREIGN KEY (user_id) REFERENCES User (user_id),
    FOREIGN KEY (comment_id) REFERENCES Comment (comment_id)
);*/