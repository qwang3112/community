package com.qwang.blog.repository;

/**
 * @author qwang
 * @date 2020/7/29
 */

import com.qwang.blog.model.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Tag findTagByName(String name);

    @Query("select t from Tag t")
    List<Tag> findTop(Pageable pageable);
}
