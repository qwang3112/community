package com.qwang.blog.controller;

import com.qwang.blog.service.BlogService;
import com.qwang.blog.service.CommentService;
import com.qwang.blog.service.TagService;
import com.qwang.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author qwang
 * @date 2020/7/25
 */
@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    @Autowired
    private CommentService commentService;

    /**
     * 返回首页数据
     *
     * @param page 页码
     * @param size 页大小
     * @param model model
     * @return 首页视图
     */
    @GetMapping({"/", "/index"})
    public String index(@RequestParam(defaultValue = "0", required = false) Integer page,
                        @RequestParam(defaultValue = "8", required = false) Integer size,
                        Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedTime"));
        model.addAttribute("page", blogService.listBlog(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        //model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(10));
        model.addAttribute("recommendBlogs", blogService.listBlogTopTen(10));
        return "index";
    }

    /**
     * 搜索
     *
     * @param page 页码
     * @param size 页大小
     * @param query 查询参数
     * @param model model
     * @return 搜索结果页
     */
    @PostMapping("/search")
    public String search(@RequestParam(defaultValue = "0", required = false) Integer page,
                         @RequestParam(defaultValue = "8", required = false) Integer size,
                         @RequestParam String query,  Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedTime"));
        model.addAttribute("page", blogService.listBlog(query, pageable));
        model.addAttribute("query", query);
        return "search";
    }

    @GetMapping("/blog/{id}")
    public String blogs(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogService.getBlog(id));
        return "blog";
    }

    @GetMapping("/footer/newBlog")
    public String newBlogs(Model model) {
        model.addAttribute("newBlogs", blogService.listRecommendBlogTop(3));
        return "common :: newBlogsList";
    }

}
