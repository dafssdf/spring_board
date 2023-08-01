package com.example.app.mapper;

import com.example.app.dto.BoardDto;
import com.example.app.vo.BoardVo;
import com.example.app.vo.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
//    추가
    void insert(BoardDto boardDto);
//    삭제
    void delete(Long boardNumber);
//    수정
    void update(BoardDto boardDto);
//    조회
    BoardVo select(Long boardNumber);
//    전체조회
    List<BoardVo> selectAll(Criteria criteria);
//    전체 게시글 수 조회
    public int selectTotal();
}
