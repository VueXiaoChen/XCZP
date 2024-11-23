package com.example.xblog.controller;

import com.example.xblog.req.ResumeReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.ResumeResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.service.ResumeService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/resume")
public class ResumeController {
    @Resource
    private ResumeService resumeService;
    @Mylog(value="查询简历信息")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated ResumeReq resumeReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<ResumeResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<ResumeResp> data = resumeService.list(resumeReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="增加简历")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody ResumeReq resumeReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        resumeService.save(resumeReq);
        if (ObjectUtils.isEmpty(resumeReq.getId())) {
            resp.setMessage("保存成功");
        } else {
            resp.setMessage("修改成功");
        }
        return resp;
    }
    @Mylog(value="删除简历信息")
    @DeleteMapping ("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable Integer id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        resumeService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }

}
