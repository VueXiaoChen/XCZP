package com.example.xblog.controller;

import com.example.xblog.req.ArticlesReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.ArticlesResp;
import com.example.xblog.service.ArticlesService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/articles")
public class ArticlesController {
    @Resource
    private ArticlesService articlesService;
    @Mylog(value="查询博客内容")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated ArticlesReq articlesReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<ArticlesResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<ArticlesResp> data = articlesService.list(articlesReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="增加或修改博客信息")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody ArticlesReq articlesReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        articlesService.save(articlesReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(articlesReq.getArticleId())) {
            resp.setData(articlesReq);
            resp.setMessage("保存成功");
        } else {
            resp.setData(articlesReq);
            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }

    //单个删除
    @Mylog(value="删除博客信息")
    @GetMapping("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable long id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        articlesService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
}
