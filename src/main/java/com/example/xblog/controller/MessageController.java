package com.example.xblog.controller;


import com.example.xblog.exception.RedisCode;
import com.example.xblog.req.WebMessageReq;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/webmessage")
public class MessageController {

    @Resource
    public RedisTemplate redisTemplate;
    @PostMapping("/sendall")
    //@Valid  开启参数检验
    public void sendall(@RequestBody WebMessageReq webMessageReq) {
        redisTemplate.convertAndSend(RedisCode.TOPIC_SENDALL,webMessageReq);
    }
    @PostMapping("/send")
    public void send(@RequestBody WebMessageReq webMessageReq) {
        redisTemplate.convertAndSend(RedisCode.TOPIC_SEND,webMessageReq);
    }
    @PostMapping("/collect")
    public void collect(@RequestBody WebMessageReq webMessageReq) {
        redisTemplate.convertAndSend(RedisCode.TOPIC_COLLECT,webMessageReq);
    }
    //单个增加
}
