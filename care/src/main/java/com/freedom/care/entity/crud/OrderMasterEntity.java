package com.freedom.care.entity.crud;

import com.freedom.common.entity.UUIDEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "care_order_master")
public class OrderMasterEntity extends UUIDEntity {

    private String no;
    private String userNo;
    private String serveNo;
    private String serveName;
    private UUID addressId;
    private Date orderTime;
    @Column(length = 1) // 1.待付款 2.待接单 3.待服务 4.服务中 5.已完成
    private String status;
    @Column(length = 1)
    private String starStatus;

    //region getter and setter

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getServeNo() {
        return serveNo;
    }

    public void setServeNo(String serveNo) {
        this.serveNo = serveNo;
    }

    public String getServeName() {
        return serveName;
    }

    public void setServeName(String serveName) {
        this.serveName = serveName;
    }

    public UUID getAddressId() {
        return addressId;
    }

    public void setAddressId(UUID addressId) {
        this.addressId = addressId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStarStatus() {
        return starStatus;
    }

    public void setStarStatus(String starStatus) {
        this.starStatus = starStatus;
    }

// endregion
}
