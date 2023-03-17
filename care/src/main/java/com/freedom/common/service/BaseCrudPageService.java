package com.freedom.common.service;

import com.freedom.common.entity.BaseEntity;
import com.freedom.common.model.PageModel;
import com.freedom.common.model.ResultModel;
import com.freedom.common.repository.CrudPageRepository;
import com.freedom.common.repository.condition.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseCrudPageService<TEntity, TKey extends Serializable> {

    protected abstract CrudPageRepository<TEntity, TKey> getRepository();

    // 得到过滤条件
    protected Criteria<TEntity> getCriteria(Object entity) {
        return Criteria.fromMap(
                entity instanceof Map ? (Map) entity : obj2Map(entity)
        );
    }

    //查找单个记录
    public ResultModel findById(TKey id) {
        return ResultModel.data(getRepository().findById(id));
    }

    //查找所有记录
    public ResultModel findAll() {
        return ResultModel.data(getRepository().findAll());
    }

    //查找分页记录
    public ResultModel findPage(
            Map<String, Object> map,
            Pageable pageable
    ) {
        Page<TEntity> items = getRepository().findAll(getCriteria(map), pageable);
        return ResultModel.data(new PageModel<>(items));
    }

    //新建记录
    public ResultModel insert(
            TEntity entity,
            String username
    ) {
        return save(entity, username);
    }

    //编辑记录
    public ResultModel update(
            TKey id,
            TEntity entity,
            String username
    ) {
        if (!getRepository().findById(id).isPresent()) {
            return ResultModel.error("id 不存在");
        }
        return save(entity, username);
    }

    //删除单个记录
    public ResultModel delete(TKey id) {
        getRepository().deleteById(id);
        return ResultModel.data("OK");
    }

    //删除多个记录
    public ResultModel delete(Iterable<TKey> idList) {
        if (idList == null) return ResultModel.error("id 列表不能为空");
        for (TKey id : idList) {
            getRepository().deleteById(id);
        }
        return ResultModel.data("OK");
    }

    //保存记录集合
    public ResultModel saveAll(
            Iterable<TEntity> entities,
            String username
    ) {
        entities.forEach(entity -> save(entity, username));
        return ResultModel.data("OK");
    }

    //保存记录
    public ResultModel save(TEntity entity, String username) {
        // update creating or updating time
        if (entity instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) entity;
            if (baseEntity.getCreateTime() == null) {
                baseEntity.setCreateTime(getNow());
                baseEntity.setCreateUser(username);
            } else {
                baseEntity.setUpdateTime(getNow());
                baseEntity.setUpdateUser(username);
            }
        }
        getRepository().save(entity);
        return ResultModel.data("OK");
    }

    /**
     * 将对象的字段转换为 Map
     *
     * @param obj 对象
     * @return Map
     */
    private Map<String, Object> obj2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        Class clazz = obj.getClass();
        while (clazz != null) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    if (value != null) {
                        map.put(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            clazz = clazz.getSuperclass();
        }
        return map;
    }

    /**
     * 得到当前时间
     *
     * @return 当前时间
     */
    private Date getNow() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MILLISECOND, -now.get(Calendar.MILLISECOND));
        return now.getTime();
    }

}
