package com.qwang.blog.repository;

import com.qwang.blog.model.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author qwang
 * @date 2020/7/28
 */
@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    Type findTypeByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
