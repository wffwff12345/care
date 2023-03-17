package com.freedom.system.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freedom.common.helper.DateHelper;
import com.freedom.common.helper.JwtHelper;
import com.freedom.system.entity.crud.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.Date;

public class UserModel {

    private static final String ROLE_USER = "ROLE_USER";

    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    private String no;          // 用户名
    private String name;        // 姓名
    private String role;        // 权限
    private String token;       // token
    private String avatar;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date loginTime;     // 登录时间

    // 从 Entity 转换为 Model
    public static UserModel fromEntity(UserEntity entity) {
        UserModel model = new UserModel();
        BeanUtils.copyProperties(entity, model);
        model.setAvatar(entity.getAvatarPath());
        model.setToken(JwtHelper.createJWT(entity.getNo(), entity.getRole()));
        model.setLoginTime(DateHelper.getNow());
        return model;
    }

    // region getter and setter

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    // endregion

}
