package com.qbanxiaoli.common.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;

/**
 * @author Q版小李
 * @description
 * @create 2018/7/30 2:06
 */
@NoRepositoryBean
public interface BaseRepository<E, ID extends Serializable> extends JpaRepository<E, ID>, JpaSpecificationExecutor<E>,
        PagingAndSortingRepository<E, ID>, QuerydslPredicateExecutor<E> {
}
