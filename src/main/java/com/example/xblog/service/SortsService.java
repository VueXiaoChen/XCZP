package com.example.xblog.service;

import com.example.xblog.domain.Sorts;
import com.example.xblog.domain.SortsExample;
import com.example.xblog.mapper.SortsMapper;
import com.example.xblog.req.SortsReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.SortsResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SortsService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(SortsService.class);

    @Resource
    public SortsMapper sortsMapper;

    public PageResp<SortsResp> list(SortsReq sortsReq) {
        //固定写法
        SortsExample example = new SortsExample();
        //固定写法
        SortsExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(sortsReq.getPage(), sortsReq.getSize());
        //类接收返回的数据
        List<Sorts> sortsList = sortsMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<SortsResp> data = CopyUtil.copyList(sortsList, SortsResp.class);
        //定义分页获取总数
        PageInfo<Sorts> pageInfo = new PageInfo<>(sortsList);
        //定义分页
        PageResp<SortsResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }

    //增加修改数据
    public void save(SortsReq sortsReq) {
        Sorts sorts = CopyUtil.copy(sortsReq, Sorts.class);
        //增加数据
        if (ObjectUtils.isEmpty(sortsReq.getSortId())) {
            sortsMapper.insertSelective(sorts);
        } else {
            //更新数据
            sortsMapper.updateByPrimaryKeySelective(sorts);
        }
    }

    //删除数据
    public void delete(long id) {
        //删除数据
        sortsMapper.deleteByPrimaryKey(id);
    }

}
