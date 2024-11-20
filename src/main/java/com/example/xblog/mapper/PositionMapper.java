package com.example.xblog.mapper;

import com.example.xblog.domain.Position;
import com.example.xblog.domain.PositionExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface PositionMapper {
    long countByExample(PositionExample example);

    int deleteByExample(PositionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Position row);

    int insertSelective(Position row);

    List<Position> selectByExampleWithBLOBs(PositionExample example);

    List<Position> selectByExample(PositionExample example);

    Position selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Position row, @Param("example") PositionExample example);

    int updateByExampleWithBLOBs(@Param("row") Position row, @Param("example") PositionExample example);

    int updateByExample(@Param("row") Position row, @Param("example") PositionExample example);

    int updateByPrimaryKeySelective(Position row);

    int updateByPrimaryKeyWithBLOBs(Position row);

    int updateByPrimaryKey(Position row);
}