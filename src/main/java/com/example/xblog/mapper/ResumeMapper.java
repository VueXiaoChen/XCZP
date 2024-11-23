package com.example.xblog.mapper;

import com.example.xblog.domain.Resume;
import com.example.xblog.domain.ResumeExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ResumeMapper {
    long countByExample(ResumeExample example);

    int deleteByExample(ResumeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Resume row);

    int insertSelective(Resume row);

    List<Resume> selectByExampleWithBLOBs(ResumeExample example);

    List<Resume> selectByExample(ResumeExample example);

    Resume selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") Resume row, @Param("example") ResumeExample example);

    int updateByExampleWithBLOBs(@Param("row") Resume row, @Param("example") ResumeExample example);

    int updateByExample(@Param("row") Resume row, @Param("example") ResumeExample example);

    int updateByPrimaryKeySelective(Resume row);

    int updateByPrimaryKeyWithBLOBs(Resume row);

    int updateByPrimaryKey(Resume row);
}