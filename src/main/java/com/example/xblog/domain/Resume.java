package com.example.xblog.domain;

import java.util.Date;

public class Resume {
    private Integer id;

    private String name;

    private String username;

    private String sex;

    private String salary;

    private String education;

    private String experience;

    private String phone;

    private String email;

    private Integer userId;

    private Date createtime;

    private Date updatetime;

    private String eduExps;

    private String workExps;

    private String proExps;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEduExps() {
        return eduExps;
    }

    public void setEduExps(String eduExps) {
        this.eduExps = eduExps;
    }

    public String getWorkExps() {
        return workExps;
    }

    public void setWorkExps(String workExps) {
        this.workExps = workExps;
    }

    public String getProExps() {
        return proExps;
    }

    public void setProExps(String proExps) {
        this.proExps = proExps;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", username=").append(username);
        sb.append(", sex=").append(sex);
        sb.append(", salary=").append(salary);
        sb.append(", education=").append(education);
        sb.append(", experience=").append(experience);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", userId=").append(userId);
        sb.append(", createtime=").append(createtime);
        sb.append(", updatetime=").append(updatetime);
        sb.append(", eduExps=").append(eduExps);
        sb.append(", workExps=").append(workExps);
        sb.append(", proExps=").append(proExps);
        sb.append("]");
        return sb.toString();
    }
}