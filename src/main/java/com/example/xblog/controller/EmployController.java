package com.example.xblog.controller;

import com.example.xblog.req.EmployReq;
import com.example.xblog.req.EmploySaveReq;
import com.example.xblog.req.IndustryReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.resp.IndustryResp;
import com.example.xblog.service.EmployService;
import com.example.xblog.service.IndustryService;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/employ")
public class EmployController {
    @Resource
    private EmployService employService;
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated EmployReq employReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<EmployResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<EmployResp> data = employService.list(employReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }

    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody EmploySaveReq employSaveReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        employService.save(employSaveReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(employSaveReq.getId())) {
            resp.setMessage("保存成功");
        } else {
            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }

}
