package com.example.web.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 统计时长
 */
public class ControllerStatisticsInterceptor implements HandlerInterceptor {
    ThreadLocal<Long> sTime = new ThreadLocal<>();
    private static final Logger logger = LoggerFactory.getLogger(ControllerStatisticsInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        sTime.set(startTime);
        logger.info("请求路径：{}，开始计时", request.getRequestURI());

        return true;
    }

    //计算控制器耗费时长
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long time = System.currentTimeMillis() - sTime.get();
        //重新计时开始统计页面渲染时长
        sTime.set(time);
        double totalTime = (double) time / 1000.0;

        logger.info("{}耗费了{}秒",handler.getClass().getSimpleName(), totalTime);
    }

    //计算页面加载耗费时长
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long viewCompleteTime = System.currentTimeMillis() - sTime.get();
        double totalTime = (double) viewCompleteTime / 1000.0;

        logger.info("{}页面花费了{}秒进行渲染", request.getRequestURI(), totalTime);
        sTime.remove();

        if (ex != null) {
            logger.error("{}抛出了错误：{}", request.getRequestURI(), ex.getMessage());
        }
    }
}
