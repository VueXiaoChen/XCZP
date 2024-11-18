package com.example.xblog.mapper;

import org.apache.ibatis.annotations.Mapper;


import com.example.xblog.domain.Industry;
import com.example.xblog.domain.IndustryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface IndustryMapper {
    long countByExample(IndustryExample example);

    int deleteByExample(IndustryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Industry row);

    int insertSelective(Industry row);

    List<Industry> selectByExampleWithBLOBs(IndustryExample example);

    List<Industry> selectByExample(IndustryExample example);

    Industry selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Industry row, @Param("example") IndustryExample example);

    int updateByExampleWithBLOBs(@Param("row") Industry row, @Param("example") IndustryExample example);

    int updateByExample(@Param("row") Industry row, @Param("example") IndustryExample example);

    int updateByPrimaryKeySelective(Industry row);

    int updateByPrimaryKeyWithBLOBs(Industry row);

    int updateByPrimaryKey(Industry row);
}