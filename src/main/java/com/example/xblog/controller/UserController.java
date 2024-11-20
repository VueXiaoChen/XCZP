package com.example.xblog.controller;

import com.example.xblog.req.*;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.EmployResp;
import com.example.xblog.resp.UserLoadingResp;
import com.example.xblog.resp.UserResp;
import com.example.xblog.service.EmployService;
import com.example.xblog.service.UserService;
import com.example.xblog.util.Mylog;
import com.example.xblog.util.SnowFlake;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Resource
    private SnowFlake snowFlake;

    @Mylog(value="查询用户信息")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated UserReq userReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<UserResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<UserResp> data = userService.list(userReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="增加或修改用户信息")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody UserSaveReq userSaveReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        userService.save(userSaveReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(userSaveReq.getId())) {
            resp.setMessage("保存成功");
        } else {
            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }
    //单个删除
    @Mylog(value="删除用户信息")
    @DeleteMapping ("/delete/{id}")
    //@PathVariable与{blogId}是绑定的
    public CommonResp delete(@PathVariable Integer id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        userService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
    @Mylog(value="用户登录")
    @PostMapping("/loading")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp loading(@RequestBody UserLoadingReq userLoadingReq) {
        //返回信息里面定义返回的类型
        CommonResp<UserLoadingResp> resp = new CommonResp<>();
        //保存数据
        UserLoadingResp userLoadingResp = userService.loading(userLoadingReq);
        //雪花算法生成token
        Long token = snowFlake.nextId();
        //LOG.info("生成单点登录的token:{},并放入redis中",token);
        //token设置
        userLoadingResp.setToken(token.toString());
        //redisTemplate.opsForValue().set(token.toString(),userLoadingResp,3600*24*30, TimeUnit.SECONDS);
        //储存信息
        resp.setMessage("登录成功");
        resp.setData(userLoadingResp);
        return resp;
    }

    //单个删除
    @Mylog(value="修改密码")
    @PostMapping ("/password")
    //@PathVariable与{blogId}是绑定的
    public CommonResp password(@RequestBody UserLoadingReq userLoadingReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        userService.updatepassword(userLoadingReq);
        //将信息添加到返回信息里
        resp.setMessage("修改成功");
        resp.setData("");
        return resp;
    }

}
