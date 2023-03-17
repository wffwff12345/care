package com.freedom.care.entity.crud;

import com.freedom.common.entity.NoNameEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Schema(description="服务类别信息表")
@Entity
@Table(name = "care_serve_category")
public class ServeCategoryEntity extends NoNameEntity {


}
