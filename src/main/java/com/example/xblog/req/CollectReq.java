package com.example.xblog.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class CollectReq extends PageReq{
    private Integer id;
    private Integer studentId;
    private Integer positionId;

    private String positionName;

    private String positionSalary;

    private List<String> tagList;

    private String employAvatar;

    private String employName;

    private String industryName;

    private String employStage;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date collecttime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getPositionSalary() {
        return positionSalary;
    }

    public void setPositionSalary(String positionSalary) {
        this.positionSalary = positionSalary;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getEmployAvatar() {
        return employAvatar;
    }

    public void setEmployAvatar(String employAvatar) {
        this.employAvatar = employAvatar;
    }

    public String getEmployName() {
        return employName;
    }

    public void setEmployName(String employName) {
        this.employName = employName;
    }

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getEmployStage() {
        return employStage;
    }

    public void setEmployStage(String employStage) {
        this.employStage = employStage;
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
        sb.append(", positionName=").append(positionId);
        sb.append(", positionSalary=").append(positionId);
        sb.append(", tagList=").append(positionId);
        sb.append(", employAvatar=").append(positionId);
        sb.append(", employName=").append(positionId);
        sb.append(", industryName=").append(positionId);
        sb.append(", employStage=").append(positionId);
        sb.append(", collecttime=").append(collecttime);
        sb.append(", collectdistime=").append(collectdistime);
        sb.append("]");
        return sb.toString();
    }
}