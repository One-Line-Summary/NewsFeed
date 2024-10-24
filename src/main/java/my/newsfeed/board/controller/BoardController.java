package my.newsfeed.board.controller;

import lombok.RequiredArgsConstructor;
import my.newsfeed.board.Service.BoardService;
import my.newsfeed.board.dto.BoardRequestDto;
import my.newsfeed.board.dto.BoardResponseDto;
import my.newsfeed.board.dto.BoardResponsePage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;

    // 게시물 생성
    @PostMapping()
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto requestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(boardService.createBoard(requestDto));
    }

    // 전체 게시물 조회
    @GetMapping()
    public ResponseEntity<BoardResponsePage> getBoardList(@RequestParam(required = false, defaultValue = "0") int page,
                                                          @RequestParam(required = false, defaultValue = "10") int size,
                                                          @RequestParam(required = false, defaultValue = "modifiedDate") String criteria){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardService.getBoardListWithPaging(page, size, criteria));
    }

    // 선택 게시물 조회
    @GetMapping("/{boardid}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long boardId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(boardService.getBoard(boardId));
    }

    // 게시물 수정
    @PutMapping("/{boardid}")
    public ResponseEntity<Void> updateBoard(
            @PathVariable Long boardid,
            @RequestBody BoardRequestDto requestDto
    ){
        boardService.updateBoard(boardid, requestDto);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    // 게시물 삭제
    @DeleteMapping("/{boardid}")
    public ResponseEntity<Void> deleteBoard(
            @PathVariable Long boardid
    ){
        boardService.deleteBoard(boardid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
