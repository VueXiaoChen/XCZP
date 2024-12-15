package com.example.xblog.mapper;

import com.example.xblog.domain.VideoAddress;
import com.example.xblog.domain.VideoAddressExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface VideoAddressMapper {
    long countByExample(VideoAddressExample example);

    int deleteByExample(VideoAddressExample example);

    int deleteByPrimaryKey(Long videoid);

    int insert(VideoAddress row);

    int insertSelective(VideoAddress row);

    List<VideoAddress> selectByExampleWithBLOBs(VideoAddressExample example);

    List<VideoAddress> selectByExample(VideoAddressExample example);

    VideoAddress selectByPrimaryKey(Long videoid);

    int updateByExampleSelective(@Param("row") VideoAddress row, @Param("example") VideoAddressExample example);

    int updateByExampleWithBLOBs(@Param("row") VideoAddress row, @Param("example") VideoAddressExample example);

    int updateByExample(@Param("row") VideoAddress row, @Param("example") VideoAddressExample example);

    int updateByPrimaryKeySelective(VideoAddress row);

    int updateByPrimaryKeyWithBLOBs(VideoAddress row);

    int updateByPrimaryKey(VideoAddress row);
}