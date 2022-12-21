package com.zzzwww.post.dto.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("post_log")
public class PostLog {
    @TableId
    private Long id;

    private String url;

    private String ip;

    private String methodName;

    private String param;

    private String result;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Byte deleted;


}