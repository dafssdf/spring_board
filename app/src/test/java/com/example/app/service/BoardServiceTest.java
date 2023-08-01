package com.example.app.service;

import com.example.app.dto.BoardDto;
import com.example.app.dto.UserDto;
import com.example.app.mapper.BoardMapper;
import com.example.app.mapper.UserMapper;
import com.example.app.vo.BoardVo;
import lombok.extern.slf4j.Slf4j;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
class BoardServiceTest {

    @Mock
    private BoardMapper boardMapper;

    @InjectMocks
    private BoardService boardService;

    private BoardDto boardDto;
    private BoardVo boardVo;

    @BeforeEach
    void setUp(){
        boardDto = new BoardDto();
        boardDto.setBoardTitle("test Title");
        boardDto.setBoardContent("test Content");
        boardDto.setUserNumber(1L);

        boardVo = new BoardVo();
        boardVo.setBoardTitle("test Title");
        boardVo.setBoardContent("test Content");
        boardVo.setUserNumber(1L);
        boardVo.setUserId("aaaa");
    }

    @Test
    @DisplayName("게시물 등록 테스트")
    void register() {
        doNothing().when(boardMapper).insert(any(BoardDto.class));

        boardService.register(boardDto);

        verify(boardMapper,times(1)).insert(boardDto);
    }

    @Test
    @DisplayName("게시물 삭제 테스트")
    void remove() {
        doNothing().when(boardMapper).delete(any(Long.class));

        boardService.remove(1L);

        verify(boardMapper,times(1)).delete(1L);
    }

    @Test
    @DisplayName("게시물 수정 테스트")
    void modify() {
        doNothing().when(boardMapper).update(any(BoardDto.class));

        boardService.modify(boardDto);

        verify(boardMapper,times(1)).update(boardDto);

    }

    @Test
    @DisplayName("게시물 번호로 찾기")
    void findBoard() {
        doReturn(boardVo).when(boardMapper).select(any(Long.class));

       BoardVo board =  boardService.findBoard(1L);

        assertThat(board).isEqualTo(boardVo);

    }

    @Test
    @DisplayName("게시물 전체 조회")
    void findAll() {
        /*doReturn(List.of(boardVo)).when(boardMapper).selectAll();

        List<BoardVo> board = boardService.findAll();

        assertThat(board).contains(boardVo);*/
    }
}