package com.example.xblog.domain;

import java.util.Date;

public class Collect {
    private Integer id;

    private Integer studentId;

    private Integer positionId;

    private Date collecttime;

    private Date collectdistime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public Date getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Date collecttime) {
        this.collecttime = collecttime;
    }

    public Date getCollectdistime() {
        return collectdistime;
    }

    public void setCollectdistime(Date collectdistime) {
        this.collectdistime = collectdistime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", positionId=").append(positionId);
        sb.append(", collecttime=").append(collecttime);
        sb.append(", collectdistime=").append(collectdistime);
        sb.append("]");
        return sb.toString();
    }
}