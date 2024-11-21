package com.example.xblog.service;

import com.example.xblog.domain.Collect;
import com.example.xblog.domain.CollectExample;
import com.example.xblog.domain.Employ;
import com.example.xblog.domain.EmployExample;
import com.example.xblog.exception.BusinessException;
import com.example.xblog.exception.BusinessExceptionCode;
import com.example.xblog.mapper.CollectMapper;
import com.example.xblog.mapper.EmployMapper;
import com.example.xblog.req.CollectReq;
import com.example.xblog.req.EmployReq;
import com.example.xblog.req.EmploySaveReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CollectResp;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class CollectService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(CollectService.class);

    @Resource
    public CollectMapper collectMapper;
    //查询数据
    public PageResp<CollectResp> list(CollectReq collectReq) {
        //固定写法
        CollectExample example = new CollectExample();
        //固定写法
        CollectExample.Criteria criteria = example.createCriteria();
        if (!ObjectUtils.isEmpty(collectReq.getStudentId())) {
            criteria.andStudentIdEqualTo(collectReq.getStudentId());
        }
        if (!ObjectUtils.isEmpty(collectReq.getPositionId())) {
            criteria.andPositionIdEqualTo(collectReq.getPositionId());
        }
        //分页(获取从页面传来的数据)
        PageHelper.startPage(collectReq.getPage(), collectReq.getSize());
        //类接收返回的数据
        List<Collect> collectList = collectMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<CollectResp> data = CopyUtil.copyList(collectList, CollectResp.class);
        //定义分页获取总数
        PageInfo<Collect> pageInfo = new PageInfo<>(collectList);
        //定义分页
        PageResp<CollectResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(CollectReq collectReq) {
        Collect collect = CopyUtil.copy(collectReq, Collect.class);
        PageResp<CollectResp> collectlist = list(collectReq);
        if (collectlist.getTotal() != 0) {
            //已经收藏该职业
            throw new BusinessException(BusinessExceptionCode.USER_COLLECT_EXIST);
        }else{
            //更新时间
            collect.setCollecttime(new Date());
            collect.setCollectdistime(new Date());
            collectMapper.insertSelective(collect);
        }
    }
    //取消收藏
    public void cancelcollect(CollectReq collectReq) {
        Collect collect = CopyUtil.copy(collectReq, Collect.class);
        PageResp<CollectResp> collectlist = list(collectReq);
        if (collectlist.getTotal() != 0) {
            //取消收藏通过id
            collectMapper.deleteByPrimaryKey(collectlist.getList().get(0).getId());
        }
    }

}
