package com.example.xblog.controller;


import com.example.xblog.req.PageResp;
import com.example.xblog.req.VideoAddressReq;
import com.example.xblog.resp.CommonResp;
import com.example.xblog.resp.VideoAddressResp;
import com.example.xblog.service.VideoAddressService;
import com.example.xblog.util.Mylog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/videoAddress")
public class VideoAddressController {
    private static final Logger LOG = (Logger) LoggerFactory.getLogger(VideoAddressController.class);
    @Resource
    private VideoAddressService videoAddressService;
    @Mylog(value="查询视频地址")
    @GetMapping("/list")
    //@Valid  开启参数检验
    public CommonResp list(VideoAddressReq videoAddressReq) {
        //返回信息里面定义返回的类型
        CommonResp<PageResp<VideoAddressResp>> resp = new CommonResp<>();
        //接收数据库返回的数据
        PageResp<VideoAddressResp> data = videoAddressService.list(videoAddressReq);
        //将信息添加到返回信息里
        resp.setMessage("获取成功");
        //将信息添加到返回信息里
        resp.setData(data);
        return resp;
    }
    //单个增加
    @Mylog(value="增加视频地址")
    @PostMapping("/save")
    //@RequestBody  定义传过来的参数是实体类
    public CommonResp save(@Validated @RequestBody VideoAddressReq videoAddressReq){
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //保存数据
        videoAddressService.save(videoAddressReq);
        //将信息添加到返回信息里
        if (ObjectUtils.isEmpty(videoAddressReq.getVideoid())) {
            resp.setMessage("保存成功");
        } else {
            resp.setMessage("修改成功");
        }
        //将信息添加到返回信息里
        return resp;
    }
    //单个删除
    @Mylog(value="删除视频地址")
    @GetMapping("/delete/{videoid}")
    //@PathVariable与{videoid}是绑定的
    public CommonResp delete(@PathVariable long videoid) {
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        //删除数据
        videoAddressService.delete(videoid);
        //将信息添加到返回信息里
        resp.setMessage("删除成功");
        return resp;
    }
    @PostMapping("/upload")
    //文件上传
    public CommonResp uploadfile(@RequestParam("file") MultipartFile multipartFile,@RequestParam("ruleForm") String ruleForm){
        //返回信息里面定义返回的类型
        CommonResp resp = new CommonResp<>();
        return resp;
    }
}
