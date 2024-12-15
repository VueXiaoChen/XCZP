package com.example.xblog.RedisMessageReceive;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.example.xblog.req.CollectReq;
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



    public void sendall(String message,String channel) {
        LOG.info("消息通道:[{}]", channel);
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

    public void send(String message,String channel) throws IOException {
        LOG.info("消息通道:[{}]", channel);
        //将传过来的字符串转换成数组
        JSONArray arr = JSON.parseArray(message);
        //将数组转换成对象
        WebMessageReq m = JSON.parseObject(JSON.toJSONString(arr.get(1)),WebMessageReq.class);
        //WebMessageReq webMessageReq =new WebMessageReq();
        //webSock发送消息
        webSocsService.sendtoUser(JSON.toJSONString(m),"","userid"+m.getUserid());
        LOG.info("消费关注数据:[{}]", m);
    }
    public void collect(String message,String channel) throws IOException {
        LOG.info("消息通道:[{}]", channel);
        //将传过来的字符串转换成数组
        JSONArray arr = JSON.parseArray(message);
        //将数组转换成对象
        CollectReq collectReq = JSON.parseObject(JSON.toJSONString(arr.get(1)),CollectReq.class);
        WebMessageReq m = new WebMessageReq();
        m.setUserid(String.valueOf(collectReq.getStudentId()));
        m.setType("1");
        m.setComment("123");
        //webSock发送消息
        webSocsService.sendtoUser("收藏成功","","userid"+m.getUserid());
        LOG.info("消费关注数据:[{}]", m);
    }



}
