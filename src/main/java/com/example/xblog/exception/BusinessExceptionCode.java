package com.example.xblog.exception;
//自定义异常处理
public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    USER_EMPLOY_NAME_EXIST("企业名已存在"),
    USER_POSITION_NAME_EXIST("职位已存在"),
    USER_COLLECT_EXIST("你已经收藏过该职业"),
    USER__NAME_EXIST("账号已存在"),

    LOGIN_USER_ERROR("用户名不存在或密码错误"),
    VOTE_FOCUS("您已关注过"),
    VOTE_PRAISE("您已点赞过"),
    VOTE_COLLECT("您已收藏过"),
    NO_Repeated_Submission("不要重复提交"),

    ADDJOBERROE("添加定时任务失败"),
    UPDATEJOBERROE("修改定时任务失败"),
    PAUSEOBERROE("暂停定时任务失败"),
    STARTJOBERROE("启动定时任务失败"),
    EXECUTEJOBERROE("执行定时任务失败"),
    DELETEJOBERROE("删除定时任务失败"),
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
