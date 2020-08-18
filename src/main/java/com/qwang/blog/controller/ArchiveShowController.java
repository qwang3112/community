package com.qwang.blog.controller;

import com.qwang.blog.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author qwang
 * @date 2020/7/3
 */
@Controller
public class ArchiveShowController {

    private final BlogService blogService;

    public ArchiveShowController(BlogService blogService) {
        this.blogService = blogService;
    }

    /**
     * 归档页面
     *
     * @return 归档页面视图
     */
    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.archiveBlog());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
