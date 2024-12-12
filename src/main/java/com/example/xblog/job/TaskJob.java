package com.example.xblog.job;

import com.example.xblog.util.JobClient;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TaskJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取参数
        String param = jobExecutionContext.getJobDetail().getJobDataMap().getString(JobClient.PARAM_KEY);
        System.out.println("定时任务执行:" + param);
    }
}
