package com.example.xblog.mapper;

import com.example.xblog.domain.Collect;
import com.example.xblog.domain.CollectExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CollectMapper {
    long countByExample(CollectExample example);

    int deleteByExample(CollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Collect row);

    int insertSelective(Collect row);

    List<Collect> selectByExample(CollectExample example);

    Collect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Collect row, @Param("example") CollectExample example);

    int updateByExample(@Param("row") Collect row, @Param("example") CollectExample example);

    int updateByPrimaryKeySelective(Collect row);

    int updateByPrimaryKey(Collect row);
}