package com.example.xblog.service;

import com.example.xblog.domain.Collect;
import com.example.xblog.domain.SysJob;
import com.example.xblog.mapper.SysJobMapper;
import com.example.xblog.req.SysJobReq;
import com.example.xblog.util.CopyUtil;
import com.example.xblog.util.JobClient;
import com.example.xblog.util.JobParamVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class SysJobService {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(SysJobService.class);

    @Resource
    public SysJobMapper sysJobMapper;

    @Resource
    public JobClient jobClient;


    //查询数据
    public void add(SysJobReq sysJobReq) {
        JobParamVO jobParamVO = CopyUtil.copy(sysJobReq, JobParamVO.class);
        SysJob sysJob = CopyUtil.copy(sysJobReq, SysJob.class);
        jobClient.add(jobParamVO);
        sysJob.setCreateTime(new Date());
        //sysJobMapper.insertSelective(sysJob);
    }
    //修改定时任务
    public void update(SysJobReq sysJobReq) {
        JobParamVO jobParamVO = CopyUtil.copy(sysJobReq, JobParamVO.class);
        SysJob sysJob = CopyUtil.copy(sysJobReq, SysJob.class);
        if(!ObjectUtils.isEmpty(sysJobReq.getId())){
            jobClient.update(jobParamVO);
            //sysJobMapper.updateByPrimaryKeySelective(sysJob);
        }
    }
    //删除定时任务
    public void delete(long id) {
        jobClient.delete(id);
        //sysJobMapper.deleteByPrimaryKey(id);
    }
    //启动暂停定时任务
    public Integer startorpause(long id,SysJobReq sysJobReq){
        JobParamVO jobParamVO = CopyUtil.copy(sysJobReq, JobParamVO.class);
        SysJob sysJob = CopyUtil.copy(sysJobReq, SysJob.class);
        if(sysJobReq.getStatus()==false){
            jobClient.start(String.valueOf(sysJobReq.getId()),jobParamVO);
            //sysJobMapper.updateByPrimaryKeySelective(sysJob);
            return 0;
        }else{
            jobClient.stop(String.valueOf(sysJobReq.getId()));
            //sysJobMapper.updateByPrimaryKeySelective(sysJob);
            return 1;
        }
    }


}
