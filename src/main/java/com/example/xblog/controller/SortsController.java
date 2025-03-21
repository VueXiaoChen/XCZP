package com.example.xblog.controller;

import com.example.xblog.req.SortsReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.SortsResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.service.SortsService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/sorts")
public class SortsController {
    @Resource
    private SortsService sortsService;
    @Mylog(value="查询博客所有分类")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated SortsReq sortsReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<SortsResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<SortsResp> data = sortsService.list(sortsReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="增加或修改博客分类")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody SortsReq sortsReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        sortsService.save(sortsReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(sortsReq.getSortId())) {

            resp.setMessage("保存成功");
        } else {

            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }

    //单个删除
    @Mylog(value="删除博客分类")
    @GetMapping("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable long id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        sortsService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
}
