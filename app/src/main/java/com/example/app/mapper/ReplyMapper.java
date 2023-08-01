package com.example.app.mapper;

import com.example.app.dto.ReplyDto;
import com.example.app.vo.Criteria;
import com.example.app.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReplyMapper {

    void insert(ReplyDto replyDto);

    List<ReplyVo> selectList(Long boardNumber);

    ReplyVo select(Long replyNumber);

    void update(ReplyDto replyDto);

    void delete(Long replyNumber);

    List<ReplyVo> selectListPage(@Param("criteria") Criteria criteria,@Param("boardNumber") Long boardNumber);

    int selectTotal(Long boardNumber);
}
