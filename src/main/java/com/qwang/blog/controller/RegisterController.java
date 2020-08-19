package com.qwang.blog.controller;

import com.qwang.blog.model.po.User;
import com.qwang.blog.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String registerPage() {
        return "/register";
    }


    @PostMapping("/register")
    public String register(@RequestParam String username, @RequestParam String nickname,
                        @RequestParam String password, @RequestParam String ackpassword,
                        HttpSession session,
                        RedirectAttributes attributes) {
        User user = new User();
        if (userRepository.findByUsername(username) == null){
            user.setUsername(username);
        } else {
            attributes.addFlashAttribute("message", "用户名已存在，请重输");
            return "redirect:/register";
        }

        user.setNickName(nickname);
        if (password.equals(ackpassword)){
            user.setPassword(MD5Utils.string2MD5(password));
        } else {
            attributes.addFlashAttribute("message", "两次密码不一致，请重输");
            return "redirect:/register";
        }
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
