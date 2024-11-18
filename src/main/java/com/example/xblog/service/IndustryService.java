package com.example.xblog.service;

import com.example.xblog.domain.Industry;
import com.example.xblog.domain.IndustryExample;
import com.example.xblog.mapper.IndustryMapper;
import com.example.xblog.req.IndustryReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.IndustryResp;
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
public class IndustryService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(IndustryService.class);

    @Resource
    public IndustryMapper industryMapper;

    public PageResp<IndustryResp> list(IndustryReq industryReq) {
        //固定写法
        IndustryExample example = new IndustryExample();
        //固定写法
        IndustryExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(industryReq.getPage(), industryReq.getSize());
        //类接收返回的数据
        List<Industry> industryList = industryMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<IndustryResp> data = CopyUtil.copyList(industryList, IndustryResp.class);
        //定义分页获取总数
        PageInfo<Industry> pageInfo = new PageInfo<>(industryList);
        //定义分页
        PageResp<IndustryResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(IndustryReq industryReq) {
        Industry industry = CopyUtil.copy(industryReq, Industry.class);
        //增加数据
        if (ObjectUtils.isEmpty(industryReq.getId())) {
            industry.setCreatetime(new Date());
            industry.setUpdatetime(new Date());
            industryMapper.insertSelective(industry);
        } else {
            //更新数据
            industry.setUpdatetime(new Date());
            industryMapper.updateByPrimaryKeySelective(industry);
        }
    }

    //删除数据
    public void delete(Integer id) {
        //删除数据
        industryMapper.deleteByPrimaryKey(id);
    }

}
