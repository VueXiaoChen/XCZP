package com.example.xblog.mapper;

import com.example.xblog.domain.SysJob;
import com.example.xblog.domain.SysJobExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SysJobMapper {
    long countByExample(SysJobExample example);

    int deleteByExample(SysJobExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SysJob row);

    int insertSelective(SysJob row);

    List<SysJob> selectByExample(SysJobExample example);

    SysJob selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") SysJob row, @Param("example") SysJobExample example);

    int updateByExample(@Param("row") SysJob row, @Param("example") SysJobExample example);

    int updateByPrimaryKeySelective(SysJob row);

    int updateByPrimaryKey(SysJob row);
}