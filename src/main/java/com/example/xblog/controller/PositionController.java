package com.example.xblog.controller;

import com.example.xblog.req.*;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.resp.PositionResp;
import com.example.xblog.service.EmployService;
import com.example.xblog.service.PositionService;
import com.example.xblog.util.Mylog;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Resource
    private PositionService positionService;
    @Mylog(value="查询企业信息")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated PositionReq positionReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<PositionResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<PositionResp> data = positionService.list(positionReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }

    @Mylog(value="增加或修改企业信息")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody PositionSaveReq positionSaveReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        positionService.save(positionSaveReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(positionSaveReq.getId())) {
            resp.setMessage("保存成功");
        } else {
            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }
    //单个删除
    @Mylog(value="删除企业职业信息")
    @DeleteMapping ("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable Integer id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        positionService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
}