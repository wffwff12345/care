package com.freedom.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

// 继承类会将字段添加到表中
@MappedSuperclass
public abstract class BaseEntity {

    @JsonIgnore
    private String createUser;

    @JsonIgnore
    private Date createTime;

    @JsonIgnore
    private String updateUser;

    @JsonIgnore
    private Date updateTime;

    //region getter and setter

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    //endregion
}
