package com.example.xblog.mapper;

import com.example.xblog.domain.ArtitleSort;
import com.example.xblog.domain.ArtitleSortExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ArtitleSortMapper {
    long countByExample(ArtitleSortExample example);

    int deleteByExample(ArtitleSortExample example);

    int deleteByPrimaryKey(@Param("articleId") Long articleId, @Param("sortId") Long sortId);

    int insert(ArtitleSort row);

    int insertSelective(ArtitleSort row);

    List<ArtitleSort> selectByExample(ArtitleSortExample example);

    int updateByExampleSelective(@Param("row") ArtitleSort row, @Param("example") ArtitleSortExample example);

    int updateByExample(@Param("row") ArtitleSort row, @Param("example") ArtitleSortExample example);
}