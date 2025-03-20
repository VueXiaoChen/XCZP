package com.example.xblog.service;

import com.example.xblog.domain.Labels;
import com.example.xblog.domain.LabelsExample;
import com.example.xblog.mapper.LabelsMapper;
import com.example.xblog.req.LabelsReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.LabelsResp;
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
public class LabelsService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(LabelsService.class);

    @Resource
    public LabelsMapper labelsMapper;

    public PageResp<LabelsResp> list(LabelsReq labelsReq) {
        //固定写法
        LabelsExample example = new LabelsExample();
        //固定写法
        LabelsExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(labelsReq.getPage(), labelsReq.getSize());
        //类接收返回的数据
        List<Labels> labelsList = labelsMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<LabelsResp> data = CopyUtil.copyList(labelsList, LabelsResp.class);
        //定义分页获取总数
        PageInfo<Labels> pageInfo = new PageInfo<>(labelsList);
        //定义分页
        PageResp<LabelsResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(LabelsReq labelsReq) {
        Labels labels = CopyUtil.copy(labelsReq, Labels.class);
        //增加数据
        if (ObjectUtils.isEmpty(labelsReq.getLabelId())) {
            labelsMapper.insertSelective(labels);
        } else {
            //更新数据
            labelsMapper.updateByPrimaryKeySelective(labels);
        }
    }

    //删除数据
    public void delete(long id) {
        //删除数据
        labelsMapper.deleteByPrimaryKey(id);
    }

}
