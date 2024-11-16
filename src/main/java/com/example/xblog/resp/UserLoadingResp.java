package com.example.xblog.resp;

import java.io.Serializable;

//序列化，使用redis之后需要序列化
public class UserLoadingResp implements Serializable {
    private Long userid;

    private String username;

    private Integer code = 0;
    private String token;
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Override
    public String toString() {
        return "UserLoadingResp{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", code=" + code +
                ", token='" + token + '\'' +
                '}';
    }
}