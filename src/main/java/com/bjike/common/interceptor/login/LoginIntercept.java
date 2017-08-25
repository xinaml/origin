package com.bjike.common.interceptor.login;

import com.bjike.common.http.ResponseContext;
import com.bjike.common.restful.ActResult;
import com.bjike.ser.user.UserSer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 登录安全拦截(仅检测是否有携带token,用token获取用户的时候再进行token有无效判定)
 * RpcContext.getContext().getAttachment(key)获取隐形参数，
 * 假如在调用之前又进行了远程调用（如：userApi.findUser()）,则隐藏参数失效
 *
 * @Author: [liguiqin]
 * @Date: [2017-01-14 14:34]
 * @Description: []
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
@Component
public class LoginIntercept extends HandlerInterceptorAdapter {

    public static UserSer userSer;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();

        if (url.equals("/chatRoom")||url.equals("/")) return true;

        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return validateLogin(request, response);
        }
        Method method = ((HandlerMethod) handler).getMethod();
        Class<?> clazz = method.getDeclaringClass();
        //该类或者方法上是否有登录安全认证注解
        if (clazz.isAnnotationPresent(LoginAuth.class) || method.isAnnotationPresent(LoginAuth.class)) {
            return validateLogin(request, response);
        }
        return true;

    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        //调用完UserFilter才会调用此方法
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String token = request.getHeader("token");
            if (StringUtils.isNotBlank(token)) {
                return userSer.isLogin(token);
            } else {
                handlerNotHasLogin(response, "用户未登录！");
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }


    }


    /**
     * 未登录处理
     *
     * @param response
     * @throws IOException
     */
    private void handlerNotHasLogin(HttpServletResponse response, String msg) throws IOException {
        ActResult actResult = new ActResult();
        response.setContentType("text/html;charset=utf-8");
        actResult.setCode(403);
        actResult.setMsg("用户未登录!");
        response.setStatus(200);
        ResponseContext.writeData(actResult);
    }
}
