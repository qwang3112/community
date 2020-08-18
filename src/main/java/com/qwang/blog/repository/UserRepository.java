package com.qwang.blog.repository;

import com.qwang.blog.model.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author qwang
 * @date 2020/7/27
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findByNickName(String nickName);


}
