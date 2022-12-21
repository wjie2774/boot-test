package com.zzzwww.post.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zzzwww.aspect.annotation.CheckId;
import com.zzzwww.aspect.annotation.PostLog;
import com.zzzwww.post.dao.FileLevelMapper;
import com.zzzwww.post.dto.entity.FileLevel;
import com.zzzwww.post.dto.response.FileLevelTreeTreeResponse;
import com.zzzwww.post.service.TestService;
import com.zzzwww.tree.dto.TreeConfig;
import com.zzzwww.tree.util.TreeUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    FileLevelMapper fileLevelMapper;
    @Autowired
    RedisTemplate<String, String> redisTemplate;
    @Autowired
    TestService testService;

    @GetMapping("/test1")
    @PostLog
    public List<FileLevelTreeTreeResponse> test1() {
        List<FileLevel> fileLevels = fileLevelMapper.selectByInitialize();
        TreeConfig treeConfig = new TreeConfig();
        treeConfig.setParentIdKey("parentId");
        treeConfig.setIdKey("id");
        treeConfig.setChildren("childrens");
        return TreeUtils.buildTree(this.copyProperties(fileLevels), 0l, treeConfig);
    }

    @PostMapping("/file/level/detail")
    @PostLog
    @CheckId(id = "#fileLevel.id", clazz = FileLevel.class)
    public FileLevel detail(@RequestBody FileLevel fileLevel) {
        return fileLevelMapper.selectById(fileLevel.getId());
    }

    private List<FileLevelTreeTreeResponse> copyProperties(List<FileLevel> fileLevels) {
        List<FileLevelTreeTreeResponse> treeResponses = new ArrayList<>();
        for (FileLevel fileLevel : fileLevels) {
            FileLevelTreeTreeResponse treeResponse = new FileLevelTreeTreeResponse();
            BeanUtils.copyProperties(fileLevel, treeResponse);
            treeResponses.add(treeResponse);
        }
        return treeResponses;
    }

    @GetMapping("/test2")
    public void test2() {
        QueryWrapper<FileLevel> wrapper = new QueryWrapper<>();
        wrapper.eq("deleted", 0);
        List<FileLevel> fileLevels = fileLevelMapper.selectList(wrapper);
        System.out.println(fileLevels);
    }

    @GetMapping("/test3")
    public void test3() {
        List<FileLevel> fileLevels = fileLevelMapper.selectByInitialize();
//        fileLevels.stream().reduce(new FileLevel(), TestController::setFileLevel);

    }

    public static FileLevel setFileLevel(FileLevel fileLevel){
        fileLevel.setCurrentLevel(11);
        return fileLevel;
    }

    @GetMapping("/test4")
    public void test4() throws InterruptedException {
        testService.test4();
    }


}
