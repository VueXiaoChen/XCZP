package com.example.xblog.controller;

import com.example.xblog.req.ArticleLabelsReq;
import com.example.xblog.req.PageResp;
import com.example.xblog.resp.ArticleLabelsResp;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.service.ArticleLabelsService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/articleLabels")
public class ArticleLabelsController {
    @Resource
    private ArticleLabelsService articleLabelsService;
    @Mylog(value="查询文章类型")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated ArticleLabelsReq articleLabelsReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<ArticleLabelsResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<ArticleLabelsResp> data = articleLabelsService.list(articleLabelsReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="增加或修改文章类型")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody ArticleLabelsReq articleLabelsReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        articleLabelsService.save(articleLabelsReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(articleLabelsReq.getArticleId())) {

            resp.setMessage("保存成功");
        } else {

            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }

    //单个删除
    @Mylog(value="删除文章类型")
    @GetMapping("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable long id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        articleLabelsService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
}
