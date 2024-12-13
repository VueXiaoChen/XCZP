package com.example.xblog.job;

import com.alibaba.fastjson.JSON;
import com.example.xblog.exception.RedisCode;
import com.example.xblog.util.JobClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.Resource;

public class TaskJob implements Job {

    @Resource
    public RedisTemplate redisTemplate;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取参数
        String param = jobExecutionContext.getJobDetail().getJobDataMap().getString(JobClient.PARAM_KEY);
        System.out.println("定时任务执行:" + param);
        //redisTemplate.convertAndSend(RedisCode.TOPIC_SEND, JSON.parseObject(param));
    }
}
