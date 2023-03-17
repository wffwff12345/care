package com.freedom.system.entity.crud;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freedom.common.entity.NoNameEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "care_user")
public class UserEntity extends NoNameEntity {

    @JsonIgnore
    private String password;    // 密码
    private String avatarId;    // 头像文件编号
    private String avatarPath;  // 头像文件路径
    @Column(length = 1)
    private String status;      // 状态 1: 启用 0:禁用
    private String role;        // 角色
    private int tryCount;       // 尝试失败次数
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date tryTime;       // 尝试失败时间

    private String openId;
    private String type;

    // region getter and setter

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(String avatarId) {
        this.avatarId = avatarId;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getTryCount() {
        return tryCount;
    }

    public void setTryCount(int tryCount) {
        this.tryCount = tryCount;
    }

    public Date getTryTime() {
        return tryTime;
    }

    public void setTryTime(Date tryTime) {
        this.tryTime = tryTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    // endregion
}
