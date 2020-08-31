package com.flouis.counter.filter;

import com.flouis.counter.service.LoginService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class SessionCheckFilter implements Filter {

    @Resource
    private LoginService loginService;

    private Set<String> whiteRootPaths = Sets.newHashSet("login", "msgsocket", "test");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //解决ajax跨域问题
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setHeader("Access-Control-Allow-Origin","*");

        // 身份校验：粗粒度权限控制
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // http://localhost:8081/login/pwdsetting ==> /login/pwdsetting
        String path = request.getRequestURI();
        String[] pathArr = path.split("/");
        if (whiteRootPaths.contains(pathArr[1])){
            // 在白名单中放行请求
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            // 不在白名单中校验token是否合法，合法token放行，不合法进请求转发到
            if (this.loginService.checkToken(request.getParameter("token"))){
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                request.getRequestDispatcher("/login/needLogin").forward(servletRequest, servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }


}
