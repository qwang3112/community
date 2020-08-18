package com.qwang.blog.model.param;

import lombok.Data;

/**
 * @author qwang
 * @date 2020/7/29
 */
@Data
public class BlogParam {

    private String title;
    private Long TypeId;
    private boolean recommend;
}
