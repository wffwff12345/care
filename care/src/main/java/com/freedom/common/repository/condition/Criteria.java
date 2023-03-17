package com.freedom.common.repository.condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Criteria<T> implements Specification<T> {

    private final List<Criterion<T>> criteria = new ArrayList<>();

    /**
     * 将 Map 转化为 Criteria
     *
     * @param map 条件 map
     * @return 判断标准列表
     */
    public static <T> Criteria<T> fromMap(Map<String, Object> map) {
        Criteria<T> criteria = new Criteria<>();
        if (map == null || map.size() == 0) return null;
        for (Map.Entry<String, Object> item : map.entrySet()) {
            Criterion<T> criterion = Restrictions.toCriterion(item.getKey(), item.getValue());
            if (criterion != null) {
                criteria.add(criterion);
            }
        }
        return criteria;
    }

    @Override
    public Predicate toPredicate(
            Root<T> root,
            CriteriaQuery<?> query,
            CriteriaBuilder builder) {

        if (!criteria.isEmpty()) {
            List<Predicate> predicates = new ArrayList<>();
            for (Criterion<T> c : criteria) {
                predicates.add(c.toPredicate(root, query, builder));
            }
            // 将所有条件用 and 联合起来
            return builder.and(predicates.toArray(new Predicate[0]));
        }
        return builder.conjunction();
    }

    /**
     * 增加简单条件表达式
     */
    public void add(Criterion<T> criterion) {
        if (criterion != null) {
            criteria.add(criterion);
        }
    }
}
