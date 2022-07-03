package com.jiawa.wiki.req;

import javax.validation.constraints.NotNull;

public class UserLoginReq {


    @NotNull(message = "用户名 不能为空")
    private String loginName;


    @NotNull(message = "密码 不能为空")
    private String password;


    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserLoginReq{" +
                "loginName='" + loginName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}