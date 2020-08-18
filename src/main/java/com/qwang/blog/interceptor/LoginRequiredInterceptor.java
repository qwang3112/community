package com.qwang.blog.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author qwang
 * @date 2020/7/27
 */
@Slf4j
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("UserAgent : {}", request.getHeader("User-Agent"));
        log.info("User request URI : [{}], User IP : [{}]", request.getRequestURI(), request.getRemoteAddr());
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
