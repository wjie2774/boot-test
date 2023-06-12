package com.zzzwww.post.controller;

import com.zzzwww.post.dao.FileLevelMapper;
import com.zzzwww.post.dto.entity.FileLevel;
import com.zzzwww.post.dto.response.FileLevelTreeTreeResponse;
import com.zzzwww.tree.dto.TreeConfig;
import com.zzzwww.tree.util.TreeUtils;
import com.zzzwww.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.util.LambdaSafe;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cache")
public class TestCacheController {
    @Autowired
    FileLevelMapper fileLevelMapper;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @GetMapping("/debug")
    @Cacheable(value = "debug", key = "'data:room:file:level:initialize'")
    public List<FileLevelTreeTreeResponse> test1() {
        List<FileLevel> fileLevels = fileLevelMapper.selectByInitialize();
        TreeConfig treeConfig = new TreeConfig();
        treeConfig.setParentIdKey("parentId");
        treeConfig.setIdKey("id");
        treeConfig.setChildren("childrens");
        return TreeUtils.buildTree(this.copyProperties(fileLevels), 0l, treeConfig);
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


}
