package com.qwang.blog.controller;

import com.qwang.blog.model.po.Blog;
import com.qwang.blog.model.po.User;
import com.qwang.blog.service.BlogService;
import com.qwang.blog.service.TagService;
import com.qwang.blog.service.TypeService;
import com.qwang.blog.util.CoverUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author qwang
 * @date 2020/7/27
 */
@Controller

public class BlogShowController {

    private static final String WRITE = "/write";
    private static final String REDIRECT_LIST = "redirect:/index";

    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private TagService tagService;

    /**
     * 跳转到写帖子页面
     *
     * @param model
     * @return
     */
    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return WRITE;
    }

    @PostMapping("/blogs")
    public String post(Blog blog, String tagIds, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        // 获取分类id
        blog.setType(typeService.getType(blog.getType().getId()));
        // 获取标签集
        blog.setTags(tagService.listTag(tagIds));
        // 不要图片
//        blog.setCover(CoverUtils.getRandomCover());
        // 保存到数据库
        Blog b = blogService.saveBlog(blog);
        if (b == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }

    @GetMapping("/blog/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.removeBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/mine";
    }
}
