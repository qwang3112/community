package com.qwang.blog.service;

import com.qwang.blog.model.po.Comment;

import java.util.List;

/**
 * @author qwang
 * @date 2020/7/3
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long id);

    Comment saveComment(Comment comment);
}
