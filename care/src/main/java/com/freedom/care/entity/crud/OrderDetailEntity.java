package com.freedom.care.entity.crud;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freedom.common.entity.UUIDEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Date;
@Schema(description="订单详情信息表")
@Entity
@Table(name = "care_order_detail")
public class OrderDetailEntity extends UUIDEntity {

    @Schema(name="订单编号")
    private String orderNo;

    @Schema(name="用户编号")
    private String userNo;

    @Schema(name="用户姓名")
    private String userName;

    @Schema(name="状态")
    @Column(length = 1)
    private String status;

    @Schema(name="状态时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date statusTime;

    //region getter and setter

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(Date statusTime) {
        this.statusTime = statusTime;
    }


    // endregion
}
