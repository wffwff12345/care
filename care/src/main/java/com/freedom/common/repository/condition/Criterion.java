package com.freedom.common.repository.condition;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

// Criterion 判断标准
public interface Criterion<T> {

    Predicate toPredicate(Root<T> root,
                          CriteriaQuery<?> query,
                          CriteriaBuilder builder);

    enum Operator {
        EQ, NE,
        LIKE,
        GT, LT, GTE, LTE,
        AND, OR,
        IS_MEMBER, IS_NOT_MEMBER,
        NULL, NOT_NULL
    }

}
