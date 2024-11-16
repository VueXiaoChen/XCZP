package com.example.xblog.controller;

import com.example.xblog.req.IndustryReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.IndustryResp;
import com.example.xblog.service.IndustryService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
@RestController
@RequestMapping("/industry")
public class IndustryController {
    @Resource
    private IndustryService industryService;
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated IndustryReq industryReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<IndustryResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<IndustryResp> data = industryService.list(industryReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
}
