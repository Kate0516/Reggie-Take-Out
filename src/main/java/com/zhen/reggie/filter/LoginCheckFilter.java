package com.zhen.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.zhen.reggie.common.BaseContext;
import com.zhen.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * check if the user has logged in
 */
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //used to match pathes, support wildcard character
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;



        //1.get uri
        String requestURI = request.getRequestURI();
        log.info("blocked request : {}", request.getRequestURI());
        //2.if we need to deal with the request
        String[] urls = new  String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**",
                "/user/sendMsg",
                "/user/login"
        };

        boolean check = check(urls, requestURI);

        if(check){
            log.info("this request {} do not need to block", requestURI);
            filterChain.doFilter(request, response);
            return;
        }

        //3-1.if the user have logged in
        if (request.getSession().getAttribute("employee") != null){
            log.info("user logged in, id is {}", request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");

            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request, response);
            return;
        }

        //3-2.if the user in mobile have logged in
        if (request.getSession().getAttribute("user") != null){
            log.info("user logged in, id is {}", request.getSession().getAttribute("user"));

            Long userId = (Long) request.getSession().getAttribute("user");

            BaseContext.setCurrentId(userId);

            filterChain.doFilter(request, response);
            return;
        }

        //4.if not logged in return the message, send data stream to client to response
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

        log.info("user not logged in.");
        return;
    }

    /**
     * check if this request should be blocked
     * @param requestURI
     * @return
     */
    public boolean check(String[] urls, String requestURI){
        for(String url : urls){
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
