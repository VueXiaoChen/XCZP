package com.example.xblog.mapper;

import com.example.xblog.domain.Sorts;
import com.example.xblog.domain.SortsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface SortsMapper {
    long countByExample(SortsExample example);

    int deleteByExample(SortsExample example);

    int deleteByPrimaryKey(Long sortId);

    int insert(Sorts row);

    int insertSelective(Sorts row);

    List<Sorts> selectByExampleWithBLOBs(SortsExample example);

    List<Sorts> selectByExample(SortsExample example);

    Sorts selectByPrimaryKey(Long sortId);

    int updateByExampleSelective(@Param("row") Sorts row, @Param("example") SortsExample example);

    int updateByExampleWithBLOBs(@Param("row") Sorts row, @Param("example") SortsExample example);

    int updateByExample(@Param("row") Sorts row, @Param("example") SortsExample example);

    int updateByPrimaryKeySelective(Sorts row);

    int updateByPrimaryKeyWithBLOBs(Sorts row);

    int updateByPrimaryKey(Sorts row);
}