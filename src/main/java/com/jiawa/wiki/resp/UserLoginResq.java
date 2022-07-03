package com.jiawa.wiki.resp;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserLoginResq {
    private Long id;
    private Long token;

    @NotNull(message = "用户名 不能为空")
    private String loginName;
    @NotNull(message = "昵称 不能为空")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserLoginResq{" +
                "id=" + id +
                ", token=" + token +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}