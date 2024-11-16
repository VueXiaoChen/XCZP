package com.example.xblog.exception;
//自定义异常处理
public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_FOCUS("您已关注过"),
    VOTE_PRAISE("您已点赞过"),
    VOTE_COLLECT("您已收藏过"),
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
