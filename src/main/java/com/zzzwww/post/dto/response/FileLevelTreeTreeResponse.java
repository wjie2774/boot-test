package com.zzzwww.post.dto.response;

import com.zzzwww.enums.LeadTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class FileLevelTreeTreeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

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

    private List<FileLevelTreeTreeResponse> childrens;
}
