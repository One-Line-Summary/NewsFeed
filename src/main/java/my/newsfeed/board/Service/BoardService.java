package my.newsfeed.board.Service;

import lombok.RequiredArgsConstructor;
import my.newsfeed.board.dto.BoardRequestDto;
import my.newsfeed.board.dto.BoardResponseDto;
import my.newsfeed.board.entity.Board;
import my.newsfeed.board.dto.BoardResponsePage;
import my.newsfeed.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public  BoardResponseDto createBoard(BoardRequestDto requestDto) {
        // 일정 생성
        // DTO -> ENTITY
        Board board = Board.from(requestDto);

        // 일정 DB에 저장
        Board savedBoard = boardRepository.save(board);

        return savedBoard.to();
    }

    public List<BoardResponseDto> getBoardList() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(Board::to)
                .collect(Collectors.toList());
    }

    // 페이징 적용 조회
    public BoardResponsePage getBoardListWithPaging(int page, int size, String criteria) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, criteria));
        Page<Board> boards = boardRepository.findAll(pageable);
        return new BoardResponsePage(boards);
    }

    public BoardResponseDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("Board not found with id: " + boardId));
        return board.to();
    }

    @Transactional
    public void updateBoard(Long boardId, BoardRequestDto requestDto) {
        Board board = boardRepository.findBoardById(boardId);
        board.updateData(requestDto);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        boardRepository.findBoardById(boardId);
        boardRepository.deleteById(boardId);
    }
}
