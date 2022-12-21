package com.zzzwww.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzzwww.post.dto.entity.AttachmentAcquisition;
import org.apache.ibatis.annotations.Param;


import java.util.Date;
import java.util.List;

public interface AttachmentAcquisitionMapper extends BaseMapper<AttachmentAcquisition> {
    int insert(AttachmentAcquisition record);

    int insertSelective(AttachmentAcquisition record);

    void update(AttachmentAcquisition attachment);

    List<AttachmentAcquisition> query(@Param("targetId") Long targetId, @Param("targetTypes") List<Integer> targetTypes);

    List<AttachmentAcquisition> selectPage(Integer page, Integer size);

    void updateLevelIdBatch(@Param("attachmentList") List<AttachmentAcquisition> attachmentList);

    List<AttachmentAcquisition> selectByLevelIdsAndTargetId(List<Long> levelIds, Long targetId);

    List<AttachmentAcquisition> selectByIds(@Param("ids") List<Long> ids);

    AttachmentAcquisition selectByIdAndTargetId(@Param("id") Long id, @Param("targetId")Long targetId);

    int deleteByLevelIdAndTargetIdAndTargetType(@Param("levelId") Long levelId
            , @Param("targetType") Integer targetType, @Param("targetId") Long targetId, @Param("updateTime") Date updateTime, @Param("updateBy") Long updateBy);
}