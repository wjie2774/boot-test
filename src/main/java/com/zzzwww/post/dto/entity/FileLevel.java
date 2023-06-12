package com.zzzwww.post.dto.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zzzwww.enums.LeadTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@TableName("components_file_level")
public class FileLevel {
    private Long id;

    private Long parentId;

    private String levelName;

    private LeadTypeEnum leadType;

    private Integer currentLevel;

    private Integer whetherInitialize;

    private Integer whetherLive;

    private Long propertyId;

    private String levelDescription;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    private Integer deleted;

}