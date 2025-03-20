package com.example.xblog.mapper;

import com.example.xblog.domain.ArticleLabels;
import com.example.xblog.domain.ArticleLabelsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ArticleLabelsMapper {
    long countByExample(ArticleLabelsExample example);

    int deleteByExample(ArticleLabelsExample example);

    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleLabels row);

    int insertSelective(ArticleLabels row);

    List<ArticleLabels> selectByExample(ArticleLabelsExample example);

    ArticleLabels selectByPrimaryKey(Long articleId);

    int updateByExampleSelective(@Param("row") ArticleLabels row, @Param("example") ArticleLabelsExample example);

    int updateByExample(@Param("row") ArticleLabels row, @Param("example") ArticleLabelsExample example);

    int updateByPrimaryKeySelective(ArticleLabels row);

    int updateByPrimaryKey(ArticleLabels row);
}