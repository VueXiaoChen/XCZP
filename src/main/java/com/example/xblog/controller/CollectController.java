package com.example.xblog.controller;

import com.example.xblog.req.CollectReq;
import com.example.xblog.req.EmployReq;
import com.example.xblog.req.EmploySaveReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CollectResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.service.CollectService;
import com.example.xblog.service.EmployService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {
    @Resource
    private CollectService collectService;
    @Mylog(value="查询收藏信息")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated CollectReq collectReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<CollectResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<CollectResp> data = collectService.list(collectReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="收藏岗位职业")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody CollectReq collectReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        collectService.save(collectReq);
        resp.setMessage("收藏成功");
        return resp;
    }
    @Mylog(value="取消收藏岗位职业")
    @PostMapping ("/delete")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@Validated @RequestBody CollectReq collectReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        collectService.cancelcollect(collectReq);
        //将信息添加到返回信息里
        resp.setMessage("取消收藏成功");
        resp.setData("");
        return resp;
    }

    @PostMapping ("/selfcollect")
    //@PathVariable与{blogId}是绑定的
    public CommonResp findmyselfcollect(@Validated @RequestBody CollectReq collectReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        PageResp<CollectResp> data = collectService.findmyselfcollect(collectReq);
        //将信息添加到返回信息里
        resp.setMessage("查询成功");
        resp.setData(data);
        return resp;
    }
}
