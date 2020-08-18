package com.qwang.blog.service;

import com.qwang.blog.model.po.Blog;
import com.qwang.blog.model.param.BlogParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author qwang
 * @date 2020/7/29
 */
public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogParam blogParam);

    Page<Blog> listBlog(Pageable pageable);

    Page<Blog> listBlog(String query, Pageable pageable);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    List<Blog> listRecommendBlogTop(Integer topSize);

    Map<String, List<Blog>> archiveBlog();

    Long countBlog();

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void removeBlog(Long id);
}
