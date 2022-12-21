package com.zzzwww.post.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzzwww.post.dto.entity.PostLog;

public interface PostLogMapper extends BaseMapper<PostLog> {
    int deleteByPrimaryKey(Long id);
}