package com.example.xblog.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class SubmitReq extends PageReq{
    private Integer id;

    private Integer employId;

    private Integer positionId;

    private Integer userId;

    private Integer resumeId;

    private String time;

    private String status;
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date createtime;
    @JsonFormat(pattern = "yyyy-MM-dd ", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd ")
    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEmployId() {
        return employId;
    }

    public void setEmployId(Integer employId) {
        this.employId = employId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getResumeId() {
        return resumeId;
    }

    public void setResumeId(Integer resumeId) {
        this.resumeId = resumeId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", employId=").append(employId);
        sb.append(", positionId=").append(positionId);
        sb.append(", userId=").append(userId);
        sb.append(", resumeId=").append(resumeId);
        sb.append(", time=").append(time);
        sb.append(", status=").append(status);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append("]");
        return sb.toString();
    }
}