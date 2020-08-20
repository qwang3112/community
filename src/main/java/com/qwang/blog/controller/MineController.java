package com.qwang.blog.controller;

import com.qwang.blog.model.po.Blog;
import com.qwang.blog.model.po.Tag;
import com.qwang.blog.model.po.User;
import com.qwang.blog.repository.BlogRepository;
import com.qwang.blog.service.BlogService;
import com.qwang.blog.service.TagService;
import com.qwang.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author qwang
 * @date 2020/7/3
 */
@Controller
public class MineController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagService tagService;


    /**
     * 分类页面
     *
     * @return 分类页面视图
     */
    @GetMapping({"/mine"})
    public String myBlog(@RequestParam(defaultValue = "0", required = false) Integer page,
                         @RequestParam(defaultValue = "8", required = false) Integer size,
                         HttpSession session, Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "updatedTime"));
        User user = ((User) session.getAttribute("user"));
        model.addAttribute("page", blogService.listBlogByUserId(user.getId(), pageable));
        return "mine";
    }
}
