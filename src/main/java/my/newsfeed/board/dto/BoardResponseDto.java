package my.newsfeed.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String boardTitle;
    private String userName;
    private String boardContent;
    private String createdDate;
    private String modifiedDate;
}
