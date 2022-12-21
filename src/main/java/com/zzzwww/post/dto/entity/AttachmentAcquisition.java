package com.zzzwww.post.dto.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Data
@NoArgsConstructor
@TableName("`components_attachment_acquisition`")
public class AttachmentAcquisition {

    private Long id;

    private Long targetId;

    private Integer targetType;

    private String fileName;

    private String fileKey;

    private Long levelId;

    private Long createBy;

    private Date createTime;

    private Long updateBy;

    private Date updateTime;

    /**
     * Delete status (YES:1, NO:0)
     */
    private Integer deleted;
}