package com.example.xblog.mapper;

import com.example.xblog.domain.Employ;
import com.example.xblog.domain.EmployExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface EmployMapper {
    long countByExample(EmployExample example);

    int deleteByExample(EmployExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Employ row);

    int insertSelective(Employ row);

    List<Employ> selectByExample(EmployExample example);

    Employ selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Employ row, @Param("example") EmployExample example);

    int updateByExample(@Param("row") Employ row, @Param("example") EmployExample example);

    int updateByPrimaryKeySelective(Employ row);

    int updateByPrimaryKey(Employ row);
}