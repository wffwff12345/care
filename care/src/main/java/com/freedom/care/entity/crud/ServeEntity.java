package com.freedom.care.entity.crud;

import com.freedom.common.entity.NoNameEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;
@Schema(description="服务信息表")
@Entity
@Table(name = "care_serve")
public class ServeEntity extends NoNameEntity {

    @Schema(name="缩略图 ID")
    private String thumbnailId;

    @Schema(name="获取缩略图路径")
    private String getThumbnailPath;

    @Schema(name="价格")
    private BigDecimal price;

    @Schema(name="服务类别编号")
    @NotEmpty
    private String categoryNo;

    @Schema(name="描述")
    private String description;

    // region getter and setter

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
    }

    public String getGetThumbnailPath() {
        return getThumbnailPath;
    }

    public void setGetThumbnailPath(String getThumbnailPath) {
        this.getThumbnailPath = getThumbnailPath;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

// endregion

}
