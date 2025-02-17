package com.example.web.interceptor;

import com.example.web.mapper.NewsdetailMapper;
import com.example.web.pojo.UserMessage;
import com.example.web.util.encryption.EncryptionUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 管理员页面拦截
 */
public class NewsInterceptor implements HandlerInterceptor {

    @Autowired
    private NewsdetailMapper newsdetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(NewsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("NewsInterceptor preHandle执行");
        String adminname = null;
        String adminnameAfter;
        Cookie[] a = request.getCookies();

        HttpSession session = request.getSession(false);

        if (a != null) {
            for (Cookie cookie : a) {
                if ((cookie.getName()).equals("username")) {
                    adminname = cookie.getValue();
                    //解密用户名
                    adminnameAfter = EncryptionUtil.decryptUserName(adminname);
                    UserMessage userMessage = this.newsdetailMapper.selectUserMessageByUsername(adminnameAfter);


                    if (session != null) {
                        session.setAttribute("userMessage", adminnameAfter);
                        return true;
                    }

                    HttpSession sessionAfter = request.getSession();
                    sessionAfter.setAttribute("adminname", adminnameAfter);

                    break;
                }
            }
        }

        if (adminname == null || adminname.isEmpty()) {
            // 如果没有找到有效的用户名，重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("NewsInterceptor postHandle执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("NewsInterceptor afterCompletion执行");
    }
}
