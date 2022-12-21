package com.zzzwww.post.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zzzwww.enums.LeadTypeEnum;
import com.zzzwww.post.dto.entity.FileLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileLevelMapper extends BaseMapper<FileLevel> {
    int deleteById(Long id);

    int insert(FileLevel record);

    int insertSelective(FileLevel record);

    FileLevel selectById(Long id);

    List<FileLevel> selectByParentIdAndPropertyId(@Param("parentId") Long parentId, @Param("leadType") LeadTypeEnum leadType, @Param("propertyId") Long propertyId);

    int updateByPrimaryKeySelective(FileLevel record);

    int updateByPrimaryKey(FileLevel record);


    List<FileLevel> tree(@Param("parentId") Long parentId, @Param("propertyId") Long propertyId);

    List<FileLevel> selectByIds(@Param("ids") List<Long> levelIds);

    int selectDuplicateName(@Param("propertyId") Long propertyId, @Param("name") String name);

    Long selectByPropertyIdAndName(@Param("propertyId") Long propertyId, @Param("name") String name);

    List<FileLevel> selectByInitialize();

}