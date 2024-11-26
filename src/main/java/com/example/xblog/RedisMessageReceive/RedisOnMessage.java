package com.example.xblog.RedisMessageReceive;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.xblog.req.WebMessageReq;
import com.example.xblog.service.WebSocsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import java.io.IOException;


@Component
public class RedisOnMessage implements MessageListener{

    @Override
    public void onMessage(Message message, byte[] pattern) {

        System.out.print("11111111111111111111111");
    }
}
