package com.freedom.care.entity.crud;

import com.freedom.common.entity.NoNameEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

import java.math.BigDecimal;

@Entity
@Table(name = "care_serve")
public class ServeEntity extends NoNameEntity {

    private String thumbnailId;

    private String getThumbnailPath;

    private BigDecimal price;

    @NotEmpty
    private String categoryNo;

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
