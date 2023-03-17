package com.freedom.common.repository.condition;

import jakarta.persistence.criteria.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 单个表达式
 * <p>
 * name = jack
 */
public class SimpleExpression<T> implements Criterion<T> {

    static final String FUZZY_CHAR = "%";

    private final String fieldName;
    private final Object value;
    private final Operator operator;

    /**
     * 构造函数
     *
     * @param fieldName 字段名
     * @param value 值
     * @param operator 操作符
     */
    protected SimpleExpression(String fieldName, Object value, Operator operator) {
        this.fieldName = fieldName;
        this.value = value;
        this.operator = operator;
    }

    /**
     * 将 SimpleExpression 转换为 Criterion
     *
     * @param root 根
     * @param query 查询
     * @param builder 构建器
     * @return Predicate
     */
    @Override
    public Predicate toPredicate(Root<T> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        Path path;
        //此处是表关联数据，注意仅限一层关联，如user.address，
        //查询user的address集合中，address的name为某个值
        if (fieldName.contains(".")) {
            String[] names = fieldName.split("\\.");
            //获取该属性的类型，Set？List？Map？
            path = root.get(names[0]);
            Class clazz = path.getJavaType();
            if (clazz.equals(Set.class)) {
                SetJoin setJoin = root.joinSet(names[0]);
                path = setJoin.get(names[1]);
            } else if (clazz.equals(List.class)) {
                ListJoin listJoin = root.joinList(names[0]);
                path = listJoin.get(names[1]);
            } else if (clazz.equals(Map.class)) {
                MapJoin mapJoin = root.joinMap(names[0]);
                path = mapJoin.get(names[1]);
            } else {
                path = path.get(names[1]);
            }
        } else {
            path = root.get(fieldName);
        }

        switch (operator) {
            case EQ:
                return builder.equal(path, value);
            case NE:
                return builder.notEqual(path, value);
            case LIKE:
                return builder.like((Expression<String>) path, FUZZY_CHAR + value + FUZZY_CHAR);
            case LT:
                return builder.lessThan(path, (Comparable) value);
            case GT:
                return builder.greaterThan(path, (Comparable) value);
            case LTE:
                return builder.lessThanOrEqualTo(path, (Comparable) value);
            case GTE:
                return builder.greaterThanOrEqualTo(path, (Comparable) value);
            case IS_MEMBER:
                return builder.isMember(value, path);
            case IS_NOT_MEMBER:
                return builder.isNotMember(value, path);
            case NULL:
                return builder.isNull(path);
            case NOT_NULL:
                return builder.isNotNull(path);
            default:
                return null;
        }
    }

}
