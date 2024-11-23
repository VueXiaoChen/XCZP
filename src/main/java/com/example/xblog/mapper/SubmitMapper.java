package com.example.xblog.mapper;

import com.example.xblog.domain.Submit;
import com.example.xblog.domain.SubmitExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SubmitMapper {
    long countByExample(SubmitExample example);

    int deleteByExample(SubmitExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Submit row);

    int insertSelective(Submit row);

    List<Submit> selectByExample(SubmitExample example);

    Submit selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Submit row, @Param("example") SubmitExample example);

    int updateByExample(@Param("row") Submit row, @Param("example") SubmitExample example);

    int updateByPrimaryKeySelective(Submit row);

    int updateByPrimaryKey(Submit row);
}