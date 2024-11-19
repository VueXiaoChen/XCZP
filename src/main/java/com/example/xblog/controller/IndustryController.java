package com.example.xblog.controller;

import com.example.xblog.req.IndustryReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.IndustryResp;
import com.example.xblog.service.IndustryService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
@RestController
@RequestMapping("/industry")
public class IndustryController {
    @Resource
    private IndustryService industryService;
    @Mylog(value="查询企业类型信息")
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
    @Mylog(value="增加或修改企业类型信息")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody IndustryReq industryReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        industryService.save(industryReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(industryReq.getId())) {
            resp.setData(industryReq);
            resp.setMessage("保存成功");
        } else {
            resp.setData(industryReq);
            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }

    //单个删除
    @Mylog(value="删除企业类型信息")
    @GetMapping("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable Integer id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        industryService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
}
