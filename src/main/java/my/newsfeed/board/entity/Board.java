package my.newsfeed.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import my.newsfeed.board.dto.BoardRequestDto;
import my.newsfeed.board.dto.BoardResponseDto;
import my.newsfeed.common.BaseTimeStamp;

@Entity
@Getter
@NoArgsConstructor
public class Board extends BaseTimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String boardTitle;

    @Column
    private String boardContent;

    @Column
    private String userName;

    public static Board from(BoardRequestDto requestDto) {
        Board board = new Board();
        board.initData(requestDto);
        return board;
    }

    private void initData(BoardRequestDto requestDto) {
        this.boardTitle = requestDto.getBoardTitle();
        this.boardContent = requestDto.getBoardContent();

    }

    public BoardResponseDto to() {
        return new BoardResponseDto(
                id,
                boardTitle,
                userName,
                boardContent,
                createdDate,
                modifiedDate
        );
    }

    public void updateData(BoardRequestDto requestDto) {
        this.boardTitle = requestDto.getBoardTitle();
        this.boardContent = requestDto.getBoardContent();
    }
}
