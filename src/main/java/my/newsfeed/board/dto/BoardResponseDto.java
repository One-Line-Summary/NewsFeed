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
    private String createdDate;
    private String modifiedDate;

    public BoardResponseDto(Long id, String boardTitle, String userName, String boardContent, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.boardTitle = boardTitle;
        this.userName = userName;
        this.boardContent = boardContent;
        this.createdDate = createdDate.toString();
        this.modifiedDate = modifiedDate.toString();
    }
}
