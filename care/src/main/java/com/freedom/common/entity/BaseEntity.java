package com.freedom.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;

// 继承类会将字段添加到表中
@MappedSuperclass
@Schema(description="基础信息表")
public abstract class BaseEntity {

    @Schema(name="创建者")
    @JsonIgnore
    private String createUser;

    @Schema(name="创建时间")
    @JsonIgnore
    private Date createTime;

    @Schema(name="更新者")
    @JsonIgnore
    private String updateUser;

    @Schema(name="更新时间")
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
