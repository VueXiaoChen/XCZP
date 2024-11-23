package com.example.xblog.service;

import com.example.xblog.domain.Resume;
import com.example.xblog.domain.ResumeExample;
import com.example.xblog.exception.BusinessException;
import com.example.xblog.exception.BusinessExceptionCode;
import com.example.xblog.mapper.ResumeMapper;
import com.example.xblog.req.ResumeReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.ResumeResp;
import com.example.xblog.util.CopyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ResumeService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(ResumeService.class);

    @Resource
    public ResumeMapper resumeMapper;
    //判断名称重复的方法

    //查询数据
    public PageResp<ResumeResp> list(ResumeReq resumeReq) {
        //固定写法
        ResumeExample example = new ResumeExample();
        //固定写法
        ResumeExample.Criteria criteria = example.createCriteria();
        //分页(获取从页面传来的数据)
        PageHelper.startPage(resumeReq.getPage(), resumeReq.getSize());
        //类接收返回的数据
        List<Resume> resumeList = resumeMapper.selectByExampleWithBLOBs(example);
        //将返回的数据进行封装,某些信息是不需要返回的
        List<ResumeResp> data = CopyUtil.copyList(resumeList, ResumeResp.class);
        //定义分页获取总数
        PageInfo<Resume> pageInfo = new PageInfo<>(resumeList);
        //定义分页
        PageResp<ResumeResp> pageResp = new PageResp<>();
        //将分页的数据进行总和
        pageResp.setTotal(pageInfo.getTotal());
        //将分页的数据加入类
        pageResp.setList(data);
        return pageResp;
    }
    //增加数据
    public void save(ResumeReq resumeReq) {
        Resume resume = CopyUtil.copy(resumeReq, Resume.class);
        if (ObjectUtils.isEmpty(resumeReq.getId())) {
            resumeMapper.insertSelective(resume);
        } else {
            //更新数据
            resumeMapper.updateByPrimaryKeySelective(resume);
        }

    }
    //删除数据
    public void delete(Integer id) {
        //删除数据
        resumeMapper.deleteByPrimaryKey(id);
    }

}
