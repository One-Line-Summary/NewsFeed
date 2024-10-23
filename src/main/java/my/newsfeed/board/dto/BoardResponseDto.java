package my.newsfeed.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String boardTitle;
    private String userName;
    private String boardContent;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
