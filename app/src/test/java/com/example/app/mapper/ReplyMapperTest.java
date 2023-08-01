package com.example.app.mapper;

import com.example.app.dto.ReplyDto;
import com.example.app.vo.ReplyVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.BooleanString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class ReplyMapperTest {

    @Autowired
    private ReplyMapper replyMapper;
    private ReplyDto replyDto;

    @BeforeEach
    void setUp(){
        replyDto = new ReplyDto();
        replyDto.setReplyContent("test Content");
        replyDto.setUserNumber(8L);
        replyDto.setBoardNumber(91L);


    }

    @Test
    void insert() {
        replyMapper.insert(replyDto);
        replyMapper.selectList(91L);

        assertThat(replyMapper.selectList(91L).get(0).getReplyContent()).isEqualTo(replyDto.getReplyContent());
    }

    @Test
    void selectList() {
    }

    @Test
    void select() {
        replyMapper.insert(replyDto);
        replyMapper.selectList(91L);
        assertThat(replyMapper.selectList(91L).get(0).getUserNumber()).isEqualTo(replyDto.getUserNumber());
    }

    @Test
    void update() {
        replyMapper.insert(replyDto);

        replyDto.setReplyNumber(20L);
        replyDto.setReplyContent("update");
        replyMapper.update(replyDto);

        assertThat(replyMapper.selectList(replyDto.getBoardNumber()).get(0).getReplyContent()).isEqualTo("update");
    }

    @Test
    void delete() {
        replyMapper.insert(replyDto);
        List<ReplyVo> reply = replyMapper.selectList(replyDto.getBoardNumber());
        replyMapper.delete(reply.get(0).getReplyNumber());
        assertThat(replyMapper.selectList(replyDto.getBoardNumber()).size()).isEqualTo(0);
    }
}