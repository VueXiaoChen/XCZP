package com.example.xblog.mapper;

import com.example.xblog.domain.Comments;
import com.example.xblog.domain.CommentsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface CommentsMapper {
    long countByExample(CommentsExample example);

    int deleteByExample(CommentsExample example);

    int deleteByPrimaryKey(Long commentId);

    int insert(Comments row);

    int insertSelective(Comments row);

    List<Comments> selectByExampleWithBLOBs(CommentsExample example);

    List<Comments> selectByExample(CommentsExample example);

    Comments selectByPrimaryKey(Long commentId);

    int updateByExampleSelective(@Param("row") Comments row, @Param("example") CommentsExample example);

    int updateByExampleWithBLOBs(@Param("row") Comments row, @Param("example") CommentsExample example);

    int updateByExample(@Param("row") Comments row, @Param("example") CommentsExample example);

    int updateByPrimaryKeySelective(Comments row);

    int updateByPrimaryKeyWithBLOBs(Comments row);

    int updateByPrimaryKey(Comments row);
}