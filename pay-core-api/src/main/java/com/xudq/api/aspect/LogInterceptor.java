package com.xudq.api.aspect;

import com.xudq.api.util.MDCUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author xudq
 * @version v1.0
 * @Title
 * @Description
 * @date
 */
public class LogInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String requestId = UUID.randomUUID().toString();
            MDC.clear();
            MDCUtil.putTransId(requestId);
            // 业务日志加上tranceId
            // 以后需要在access日志中加上requestId，解注下面文件，并修改tomcat中的配置
            // access日志加上tranceId
            // request.setAttribute(ARI_REQUEST_ID, requestId);
        } catch (Exception e) {
            logger.error("ApplicationResourceInterceptor preHandle error", e);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


}
