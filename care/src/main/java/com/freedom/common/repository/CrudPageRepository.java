package com.freedom.common.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CrudPageRepository<TEntity, TKey>
        extends CrudRepository<TEntity, TKey>,
        JpaSpecificationExecutor<TEntity> {
}
