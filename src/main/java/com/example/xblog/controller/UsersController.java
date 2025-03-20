package com.example.xblog.controller;

import com.example.xblog.req.PageResp;
import com.example.xblog.req.UsersLoadingReq;
import com.example.xblog.req.UsersReq;
import com.example.xblog.req.UsersSaveReq;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.UsersLoadingResp;
import com.example.xblog.resp.UsersResp;
import com.example.xblog.service.UsersService;
import com.example.xblog.util.Mylog;
import com.example.xblog.util.SnowFlake;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Resource
    private UsersService usersService;
    @Resource
    private SnowFlake snowFlake;

    @Mylog(value="查询用户信息")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(@Validated UsersReq usersReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<UsersResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<UsersResp> data = usersService.list(usersReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    @Mylog(value="增加或修改用户信息")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody UsersSaveReq usersSaveReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        usersService.save(usersSaveReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(usersSaveReq.getUserId())) {
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
    public CommonResp delete(@PathVariable long id) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        usersService.delete(id);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        resp.setData("");
        return resp;
    }
    @Mylog(value="用户登录")
    @PostMapping("/loading")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp loading(@RequestBody UsersLoadingReq usersLoadingReq) {
        //返回信息里面定义返回的类型
        CommonResp<UsersLoadingResp> resp = new CommonResp<>();
        //保存数据
        UsersLoadingResp userLoadingResp = usersService.loading(usersLoadingReq);
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
    public CommonResp password(@RequestBody UsersLoadingReq usersLoadingReq) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        usersService.updatepassword(usersLoadingReq);
        //将信息添加到返回信息里
        resp.setMessage("修改成功");
        resp.setData("");
        return resp;
    }

}
