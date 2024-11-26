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
import javax.websocket.Session;
import java.io.IOException;


@Component
public class RedisReceiver{

    private static final Logger LOG = (Logger) LoggerFactory.getLogger(RedisReceiver.class);

    @Resource
    public WebSocsService webSocsService;
    private Session session;
    public Session getSession() {   return session;  }
    public void setSession(Session session) {
        this.session = session;
    }



    public void sendall(String message) {
        //将传过来的字符串转换成数组
        JSONArray arr = JSON.parseArray(message);
        //将数组转换成对象
        WebMessageReq m = JSON.parseObject(JSON.toJSONString(arr.get(1)),WebMessageReq.class);
        //WebMessageReq webMessageReq =new WebMessageReq();
        m.setType("2");
        m.setComment(m.getComment());
        //webSock发送消息
        webSocsService.sendInfo(JSON.toJSONString(m),"");
        LOG.info("消费关注数据:[{}]", m);
    }

    public void send(String message) throws IOException {
        //将传过来的字符串转换成数组
        JSONArray arr = JSON.parseArray(message);
        //将数组转换成对象
        WebMessageReq m = JSON.parseObject(JSON.toJSONString(arr.get(1)),WebMessageReq.class);
        //WebMessageReq webMessageReq =new WebMessageReq();
        m.setType("1");
        m.setComment(m.getComment());
        //webSock发送消息
        webSocsService.sendtoUser(JSON.toJSONString(m),"",m.getUserid());
        LOG.info("消费关注数据:[{}]", m);
    }

}
