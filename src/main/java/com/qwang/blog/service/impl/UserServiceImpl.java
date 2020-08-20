package com.qwang.blog.service.impl;

import com.qwang.blog.model.po.User;
import com.qwang.blog.repository.UserRepository;
import com.qwang.blog.service.UserService;
import com.qwang.blog.util.AvatarUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author qwang
 * @date 2020/7/27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public User saveUser(User user){
        user.setAvatar(AvatarUtils.getRandomAvatar());
        user.setEmail("example@hotmail.com");
        user.setType(0);
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        return userRepository.save(user);
    }
}
