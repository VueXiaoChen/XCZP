package com.example.xblog.service;

import com.example.xblog.domain.Submit;
import com.example.xblog.domain.SubmitExample;
import com.example.xblog.mapper.SubmitMapper;
import com.example.xblog.req.PageResp;
import com.example.xblog.req.SubmitReq;
import com.example.xblog.resp.SubmitResp;
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
public class SubmitService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(SubmitService.class);

    @Resource
    public SubmitMapper submitMapper;
    //判断名称重复的方法

    //查询数据
    public PageResp<SubmitResp> list(SubmitReq submitReq) {
        //固定写法
        SubmitExample example = new SubmitExample();
        //固定写法
        SubmitExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(submitReq.getPage(), submitReq.getSize());
        //类接收返回的数据
        List<Submit> submitList = submitMapper.selectByExample(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<SubmitResp> data = CopyUtil.copyList(submitList, SubmitResp.class);
        //定义分页获取总数
        PageInfo<Submit> pageInfo = new PageInfo<>(submitList);
        //定义分页
        PageResp<SubmitResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(SubmitReq submitReq) {
        Submit submit = CopyUtil.copy(submitReq, Submit.class);
        if (ObjectUtils.isEmpty(submitReq.getId())) {
            submit.setCreatetime(new Date());
            submit.setUpdatetime(new Date());
            submitMapper.insertSelective(submit);
        } else {
            //更新数据
            submit.setUpdatetime(new Date());
            submitMapper.updateByPrimaryKeySelective(submit);
        }

    }
    //删除数据
    public void delete(Integer id) {
        //删除数据
        submitMapper.deleteByPrimaryKey(id);
    }

}
