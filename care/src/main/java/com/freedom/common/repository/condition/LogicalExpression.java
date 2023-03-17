package com.freedom.common.repository.condition;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

// 逻辑表达式，包含多个 Criterion
public class LogicalExpression<T> implements Criterion<T> {

    private final Criterion<T>[] criterion;

    private final Operator operator;

    public LogicalExpression(Criterion<T>[] criteria, Operator operator) {
        this.criterion = criteria;
        this.operator = operator;
    }

    @Override
    public Predicate toPredicate(Root<T> root,
                                 CriteriaQuery<?> query,
                                 CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (Criterion<T> tCriterion : this.criterion) {
            predicates.add(tCriterion.toPredicate(root, query, builder));
        }
        switch (operator) {
            case AND:
                return builder.and(predicates.toArray(new Predicate[0]));
            case OR:
                return builder.or(predicates.toArray(new Predicate[0]));
            default:
                return null;
        }
    }
}
