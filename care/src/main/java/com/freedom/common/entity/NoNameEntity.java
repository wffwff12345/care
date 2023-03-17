package com.freedom.common.entity;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class NoNameEntity extends UUIDEntity{

    private String no;

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
