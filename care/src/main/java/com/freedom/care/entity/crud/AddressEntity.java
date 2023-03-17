package com.freedom.care.entity.crud;

import com.freedom.common.entity.UUIDEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Schema(description="老人信息表")
@Entity
@Table(name = "care_address")
public class AddressEntity extends UUIDEntity {
    @Schema(name="编号")
    private String userNo;

    @Schema(name="姓名")
    private String name;

    @Schema(name="电话")
    private String phone;

    @Schema(name="省份名称")
    private String provinceName;

    @Schema(name="省份编号")
    private String provinceCode;

    @Schema(name="城市名称")
    private String cityName;

    @Schema(name="城市编号")
    private String cityCode;

    @Schema(name="地区名称")
    private String districtName;

    @Schema(name="地区编号")
    private String districtCode;

    @Schema(name="详细地址")
    private String detailAddress;

    @Schema(name="是否默认")
    @Column(length = 1)
    private String isDefault;

    @Schema(name="地址标签")
    private String addressTag;

    @Schema(name="纬度")
    private String latitude;

    @Schema(name="经度")
    private String longitude;

    // region getter and setter

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getAddressTag() {
        return addressTag;
    }

    public void setAddressTag(String addressTag) {
        this.addressTag = addressTag;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    // endregion
}
