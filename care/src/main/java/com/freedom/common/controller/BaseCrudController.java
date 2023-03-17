package com.freedom.common.controller;

import com.freedom.common.model.ResultModel;
import com.freedom.common.service.BaseCrudPageService;
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
    @GetMapping("{id}")
    public ResultModel findById(@PathVariable TKey id) {
        log.debug("find record by id [" + id + "]");
        return getService().findById(id);
    }

    //查找所有记录
    @GetMapping("all")
    public ResultModel findAll() {
        log.debug("find all records");
        return getService().findAll();
    }

    //查找分页记录
    @GetMapping("page")
    public ResultModel findPage(
            Pageable pageable,
            // @RequestParam(required = false)
            // @RequestBody(required = false)
            @RequestParam(required = false) Map<String, Object> map
    ) {
        log.debug("find page records");
        return getService().findPage(map, pageable);
    }

    //新建记录
    @PostMapping("")
    public ResultModel insert(
            @Valid @RequestBody TEntity entity,
            BindingResult result,
            Principal principal) {
        if (result.hasErrors()) return getErrorMessage(result);
        log.debug("insert record");
        return getService().insert(entity, getUsername(principal));
    }

    //编辑记录
    @PatchMapping("{id}")
    public ResultModel update(
            @PathVariable("id") TKey id,
            @Valid @RequestBody TEntity entity,
            BindingResult result,
            Principal principal) {
        if (result.hasErrors()) return getErrorMessage(result);
        log.debug("update record");
        return getService().update(id, entity, getUsername(principal));
    }

    //删除单个记录
    @DeleteMapping("{id}")
    public ResultModel delete(@PathVariable TKey id) {
        log.debug("delete record by id[" + id + "]");
        return getService().delete(id);
    }

    //删除多个记录
    @PostMapping("delete")
    public ResultModel delete(@RequestBody List<TKey> idList) {
        log.debug("delete multiply records");
        return getService().delete(idList);
    }

    //保存记录集合
    @PostMapping("input")
    public ResultModel saveAll(
            @Valid @RequestBody Iterable<TEntity> entities,
            BindingResult result,
            Principal principal) {
        if (result.hasErrors()) return getErrorMessage(result);
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
