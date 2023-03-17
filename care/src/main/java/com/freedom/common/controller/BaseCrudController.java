package com.freedom.common.controller;

import com.freedom.common.model.ResultModel;
import com.freedom.common.service.BaseCrudPageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
import java.util.Map;


/**
 * 增删改查抽象控制器类
 *
 * @param <TEntity> 实体类型
 * @param <TKey>    主键类型
 */

public abstract class BaseCrudController<TEntity, TKey extends Serializable> {

    private static final Log log = LogFactory.getLog(BaseCrudController.class);

    protected abstract BaseCrudPageService<TEntity, TKey> getService();

    //查找单个记录
    @Operation(summary = "查找单个记录", description = "author：wcp")
    @GetMapping("{id}")
    public ResultModel findById( @Parameter(name = "id", description = "记录编号ID", required = true)@PathVariable TKey id) {
        log.debug("find record by id [" + id + "]");
        return getService().findById(id);
    }

    //查找所有记录
    @Operation(summary = "查找所有记录", description = "author：wcp")
    @GetMapping("all")
    public ResultModel findAll() {
        log.debug("find all records");
        return getService().findAll();
    }

    //查找分页记录
    @Operation(summary = "查找分页记录", description = "author：wcp")
    @GetMapping("page")
    public ResultModel findPage(
            @Parameter(name = "pageable", description = "分页条件", required = false)
                    Pageable pageable,
            // @RequestParam(required = false)
            // @RequestBody(required = false)
            @Parameter(name = "map", description = "过滤条件集合", required = false)
            @RequestParam(required = false) Map<String, Object> map
    ) {
        log.debug("find page records");
        return getService().findPage(map, pageable);
    }

    //新建记录
    @Operation(summary = "新建记录", description = "前台只需提供具体实体类，BindingResult和principal由过滤器处理后提供")
    @PostMapping("")
    public ResultModel insert(
            @Parameter(name = "entity", description = "实体类", required = true)
            @Valid @RequestBody TEntity entity,
            @Parameter(name = "BindingResult", description = "result", required = true) BindingResult result,
            @Parameter(name = "principal", description = "principal", required = true) Principal principal) {
        if (result.hasErrors()) {
            return getErrorMessage(result);
        }
        log.debug("insert record");
        return getService().insert(entity, getUsername(principal));
    }

    //编辑记录
    @PatchMapping("{id}")
    @Operation(summary = "编辑记录", description = "前台只需提供具体实体类和记录ID，BindingResult和principal由过滤器处理后提供")
    public ResultModel update(
            @Parameter(name = "id", description = "记录编号ID", required = true)
            @PathVariable("id") TKey id,
            @Parameter(name = "entity", description = "实体类", required = true)
            @Valid @RequestBody TEntity entity,
            @Parameter(name = "BindingResult", description = "result", required = true) BindingResult result,
            @Parameter(name = "principal", description = "principal", required = true) Principal principal) {
        if (result.hasErrors()) {
            return getErrorMessage(result);
        }
        log.debug("update record");
        return getService().update(id, entity, getUsername(principal));
    }

    //删除单个记录
    @Operation(summary = "删除单个记录", description = "")
    @DeleteMapping("{id}")
    public ResultModel delete(
            @Parameter(name = "id", description = "记录编号ID", required = true) @PathVariable TKey id) {
        log.debug("delete record by id[" + id + "]");
        return getService().delete(id);
    }

    //删除多个记录
    @Operation(summary = "删除多个记录", description = "")
    @PostMapping("delete")
    public ResultModel delete(
            @Parameter(name = "idList", description = "编号ID数组集合", required = true)
            @RequestBody List<TKey> idList) {
        log.debug("delete multiply records");
        return getService().delete(idList);
    }

    //保存记录集合
    @Operation(summary = "保存记录集合",  description = "前台只需提供具体实体类数组集合，BindingResult和principal由过滤器处理后提供")
    @PostMapping("input")
    public ResultModel saveAll(
            @Parameter(name = "entities", description = "实体类数组集合", required = true)
            @Valid @RequestBody Iterable<TEntity> entities,
            @Parameter(name = "BindingResult", description = "result", required = true) BindingResult result,
            @Parameter(name = "principal", description = "principal", required = true) Principal principal) {
        if (result.hasErrors()) {
            return getErrorMessage(result);
        }
        log.debug("input record list");
        entities.forEach(entity -> getService().save(entity, getUsername(principal)));
        return ResultModel.data("OK");
    }

    // 得到错误信息
    private ResultModel getErrorMessage(BindingResult result) {
        return ResultModel.error(result.getAllErrors().get(0).getDefaultMessage());
    }

    // 得到用用户名
    private String getUsername(Principal principal) {
        return principal == null ? "developer" : principal.getName();
    }
}
