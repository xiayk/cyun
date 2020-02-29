package com.cyun.dao;


import com.cyun.model.FileInfo;
import com.cyun.utils.mapper.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FileInfoMapper extends CommonMapper<FileInfo> {
    @Select("select * from user")
    List<FileInfo> listImage();
}