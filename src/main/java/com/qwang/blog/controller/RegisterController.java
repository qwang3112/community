package com.qwang.blog.controller;

import com.qwang.blog.model.po.User;
import com.qwang.blog.service.UserService;
import com.qwang.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * @author qwang
 * @date 2020/7/27
 */
@Controller
public class RegisterController {


    @Autowired
    private UserService userService;

//    public RegisterController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/register")
    public String registerPage() {
        return "/register";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = new User();
        user.setUsername(username);
        user.setNickName(username);
        user.setPassword(MD5Utils.string2MD5(password));

        User result = userService.saveUser(user);

        if (result != null) {
            session.setAttribute("user", user);
            return "redirect:/index";
        } else {
            attributes.addFlashAttribute("message", "注册失败");
            return "redirect:/register";
        }
    }

}
