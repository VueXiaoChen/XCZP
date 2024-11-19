package com.example.xblog.req;

import javax.validation.constraints.NotNull;

public class EmploySaveReq extends PageReq{
    private Integer id;
    @NotNull(message = "【账号】不能为空")
    private String username;
    @NotNull(message = "【密码】不能为空")
    private String password;
    @NotNull(message = "【名称】不能为空")
    private String name;
    @NotNull(message = "【avatar】不能为空")
    private String avatar;
    @NotNull(message = "【角色】不能为空")
    private String role;
    @NotNull(message = "【企业所在城市】不能为空")
    private String city;
    @NotNull(message = "【企业地址】不能为空")
    private String address;

    private Integer industryId;
    @NotNull(message = "【企业规模】不能为空")
    private String scale;
    @NotNull(message = "【企业融资】不能为空")
    private String stage;
    @NotNull(message = "【企业状态】不能为空")
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", name=").append(name);
        sb.append(", avatar=").append(avatar);
        sb.append(", role=").append(role);
        sb.append(", city=").append(city);
        sb.append(", address=").append(address);
        sb.append(", industryId=").append(industryId);
        sb.append(", scale=").append(scale);
        sb.append(", stage=").append(stage);
        sb.append(", status=").append(status);
        sb.append("]");
        return sb.toString();
    }
}