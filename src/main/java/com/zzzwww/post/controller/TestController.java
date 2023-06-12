package com.zzzwww.post.controller;

import com.alibaba.fastjson.JSON;
import com.zzzwww.aspect.annotation.CheckId;
import com.zzzwww.aspect.annotation.PostLog;
import com.zzzwww.post.dao.FileLevelMapper;
import com.zzzwww.post.dto.entity.FileLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    FileLevelMapper fileLevelMapper;


    @PostMapping("/file/level/detail")
    @PostLog
    public FileLevel detail(@RequestBody FileLevel fileLevel) {
        return fileLevelMapper.selectById(fileLevel.getId());
    }



}
