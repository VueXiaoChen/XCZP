package com.example.xblog.controller;

import com.example.xblog.req.CollectReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.req.SysJobReq;
import com.example.xblog.resp.CollectResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.service.CollectService;
import com.example.xblog.service.SysJobService;
import com.example.xblog.util.Mylog;
import com.example.xblog.util.NoRepeatLock;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sysjob")
public class SysJobController {
    @Resource
    private SysJobService sysJobService;

    @PostMapping ("/add")
    public CommonResp add(@RequestBody SysJobReq sysJobReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        sysJobService.add(sysJobReq);
        resp.setMessage("定时任务启动成功");
        return resp;
    }
    @PostMapping ("/update")
    public CommonResp update(@RequestBody SysJobReq sysJobReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        sysJobService.update(sysJobReq);
        resp.setMessage("定时任务修改成功");
        return resp;
    }
    @GetMapping ("/delete/{id}")
    public CommonResp update(@PathVariable long id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        sysJobService.delete(id);
        resp.setMessage("定时任务删除成功");
        return resp;
    }

}
