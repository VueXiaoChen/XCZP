package com.example.xblog.service;


import com.example.xblog.domain.VideoAddress;
import com.example.xblog.domain.VideoAddressExample;
import com.example.xblog.mapper.VideoAddressMapper;
import com.example.xblog.req.PageResp;
import com.example.xblog.req.VideoAddressReq;
import com.example.xblog.resp.VideoAddressResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class VideoAddressService {
    //打印日志
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(VideoAddressService.class);
    @Resource
    public VideoAddressMapper videoAddressMapper;

    public PageResp<VideoAddressResp> list(VideoAddressReq videoAddressReq){
        //固定写法
        VideoAddressExample example = new VideoAddressExample();
        //固定写法
        VideoAddressExample.Criteria criteria = example.createCriteria();
        //条件查询
        //大于或等于创建时间
        if (!ObjectUtils.isEmpty(videoAddressReq.getCreateTime())) {
            criteria.andCreateTimeGreaterThanOrEqualTo(videoAddressReq.getCreateTime());
        }
        //大于或等于修改时间
        if (!ObjectUtils.isEmpty(videoAddressReq.getUpdateTime())) {
            criteria.andCreateTimeGreaterThanOrEqualTo(videoAddressReq.getUpdateTime());
        }
        //根据视频标签
        if (!ObjectUtils.isEmpty(videoAddressReq.getVideotag())) {
            criteria.andVideotagLike("%" + videoAddressReq.getVideotag() + "%");
        }
        //根据视频来源出处
        if (!ObjectUtils.isEmpty(videoAddressReq.getVideosource())) {
            criteria.andVideosourceLike("%" + videoAddressReq.getVideosource() + "%");
        }
        //根据视频原地址
        if (!ObjectUtils.isEmpty(videoAddressReq.getVideoaddress())) {
            criteria.andVideoaddressLike("%" + videoAddressReq.getVideoaddress() + "%");
        }
        //分页(获取从页面传来的数据)
        PageHelper.startPage(videoAddressReq.getPage(), videoAddressReq.getSize());
        //类接收返回的数据
        List<VideoAddress> videoAddressList = videoAddressMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<VideoAddressResp> data = CopyUtil.copyList(videoAddressList, VideoAddressResp.class);
        //定义分页获取总数
        PageInfo<VideoAddress> pageInfo = new PageInfo<>(videoAddressList);
        //定义分页
        PageResp<VideoAddressResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }


    //保存数据
    public void save(VideoAddressReq videoAddressReq){
        VideoAddress videoAddress = CopyUtil.copy(videoAddressReq, VideoAddress.class);
        //增加数据
        if (ObjectUtils.isEmpty(videoAddressReq.getVideoid())) {
            videoAddress.setCreateTime(new Date());
            videoAddress.setUpdateTime(new Date());
            videoAddress.setVideoimg("暂无图片");
            videoAddressMapper.insertSelective(videoAddress);
        } else {
            //更新数据
            videoAddress.setUpdateTime(new Date());
            videoAddressMapper.updateByPrimaryKeySelective(videoAddress);
        }
    }

    //删除数据
    public void delete(long videoid) {
        //删除数据
        videoAddressMapper.deleteByPrimaryKey(videoid);
    }
}


