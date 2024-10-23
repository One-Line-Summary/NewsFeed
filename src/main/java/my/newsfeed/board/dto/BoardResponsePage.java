package my.newsfeed.board.dto;

import lombok.Getter;
import my.newsfeed.board.entity.Board;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResponsePage {
    private List<BoardResponseDto> boards;
    private int totalPages;
    private long totalElements;

    public BoardResponsePage(Page<Board> page) {
        this.boards = page.getContent().stream()
                .map(Board::to)
                .collect(Collectors.toList());
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
    }
}
