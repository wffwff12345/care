package com.freedom.common.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
@Schema(description="编号姓名信息表")
@MappedSuperclass
public class NoNameEntity extends UUIDEntity{

    @Schema(name="编号")
    private String no;

    @Schema(name="名称")
    private String name;

    //<editor-folder desc="getter and setter">

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

    //</editor-folder>
}
