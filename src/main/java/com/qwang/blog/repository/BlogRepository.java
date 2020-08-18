package com.qwang.blog.repository;

import com.qwang.blog.model.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author qwang
 * @date 2020/7/29
 */
@Repository
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from Blog b")
    List<Blog> findTop(Pageable pageable);

    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format', b.updatedTime,'%Y') from Blog b group by function('date_format', b.updatedTime,'%Y') order by function('date_format', b.updatedTime,'%Y') desc")
    List<String> findGroupByYear();

    @Query("select b from Blog b where function('date_format', b.updatedTime,'%Y') = ?1")
    List<Blog> findByYear(String year);
}
