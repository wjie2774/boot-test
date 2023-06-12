package com.zzzwww.post.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzzwww.aspect.annotation.CheckId;
import com.zzzwww.aspect.annotation.PostLog;
import com.zzzwww.post.dao.FileLevelMapper;
import com.zzzwww.post.dto.entity.FileLevel;
import com.zzzwww.post.dto.response.FileLevelTreeTreeResponse;
import com.zzzwww.post.service.TestService;
import com.zzzwww.tree.dto.TreeConfig;
import com.zzzwww.tree.util.TreeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    FileLevelMapper fileLevelMapper;
    @Autowired
    TestService testService;


    @GetMapping("/test1")
    public List<FileLevelTreeTreeResponse> test1() {
        return new ArrayList<>();
    }





}
