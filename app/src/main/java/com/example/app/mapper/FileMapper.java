package com.example.app.mapper;

import com.example.app.dto.FileDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {
    void insert(FileDto fileDto);
    void delete(Long boardNumber);
    List<FileDto> selectList(Long boardNumber);
    List<FileDto> selectOldList();
}
