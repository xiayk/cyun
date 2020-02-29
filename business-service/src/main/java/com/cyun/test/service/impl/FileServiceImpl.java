package com.cyun.test.service.impl;


import com.cyun.dao.FileInfoMapper;
import com.cyun.model.FileInfo;
import com.cyun.test.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    FileInfoMapper fileInfoMapper;

    @Override
    public List<FileInfo> listImage() {
        return fileInfoMapper.listImage();
    }

}
