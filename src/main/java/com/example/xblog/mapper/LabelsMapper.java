package com.example.xblog.mapper;

import com.example.xblog.domain.Labels;
import com.example.xblog.domain.LabelsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface LabelsMapper {
    long countByExample(LabelsExample example);

    int deleteByExample(LabelsExample example);

    int deleteByPrimaryKey(Long labelId);

    int insert(Labels row);

    int insertSelective(Labels row);

    List<Labels> selectByExampleWithBLOBs(LabelsExample example);

    List<Labels> selectByExample(LabelsExample example);

    Labels selectByPrimaryKey(Long labelId);

    int updateByExampleSelective(@Param("row") Labels row, @Param("example") LabelsExample example);

    int updateByExampleWithBLOBs(@Param("row") Labels row, @Param("example") LabelsExample example);

    int updateByExample(@Param("row") Labels row, @Param("example") LabelsExample example);

    int updateByPrimaryKeySelective(Labels row);

    int updateByPrimaryKeyWithBLOBs(Labels row);

    int updateByPrimaryKey(Labels row);
}