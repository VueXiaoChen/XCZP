package com.example.xblog.mapper;

import com.example.xblog.domain.OperateLog;
import com.example.xblog.domain.OperateLogExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface OperateLogMapper {
    long countByExample(OperateLogExample example);

    int deleteByExample(OperateLogExample example);

    int insert(OperateLog row);

    int insertSelective(OperateLog row);

    List<OperateLog> selectByExampleWithBLOBs(OperateLogExample example);

    List<OperateLog> selectByExample(OperateLogExample example);

    int updateByExampleSelective(@Param("row") OperateLog row, @Param("example") OperateLogExample example);

    int updateByExampleWithBLOBs(@Param("row") OperateLog row, @Param("example") OperateLogExample example);

    int updateByExample(@Param("row") OperateLog row, @Param("example") OperateLogExample example);
}