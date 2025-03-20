package com.example.xblog.mapper;

import com.example.xblog.domain.Articles;
import com.example.xblog.domain.ArticlesExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ArticlesMapper {
    long countByExample(ArticlesExample example);

    int deleteByExample(ArticlesExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(Articles row);

    int insertSelective(Articles row);

    List<Articles> selectByExampleWithBLOBs(ArticlesExample example);

    List<Articles> selectByExample(ArticlesExample example);

    Articles selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByExampleWithBLOBs(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByExample(@Param("row") Articles row, @Param("example") ArticlesExample example);

    int updateByPrimaryKeySelective(Articles row);

    int updateByPrimaryKeyWithBLOBs(Articles row);

    int updateByPrimaryKey(Articles row);
}