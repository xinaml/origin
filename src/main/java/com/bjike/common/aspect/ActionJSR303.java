package com.bjike.common.aspect;

import com.alibaba.fastjson.JSON;
import com.bjike.common.http.ResponseContext;
import com.bjike.common.interceptor.error.JsrTip;
import com.bjike.common.restful.ActResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;

@Order(Integer.MIN_VALUE + 1)
@Aspect
@Component
public class ActionJSR303 {


    @Autowired(required = false)

    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PostMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PutMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.DeleteMapping)" +
            " || @annotation(org.springframework.web.bind.annotation.PatchMapping)"
    )
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {

        BindingResult result = null;
        MethodSignature methodSignature = (MethodSignature) joinPoint
                .getSignature();
        Method method = methodSignature.getMethod();
        Annotation[][] argAnnotations = method.getParameterAnnotations();
        Object[] args = joinPoint.getArgs();//获取参数值

        if (method.getName().equals("errorHtml")) {//springmvc 错误请求
            return joinPoint.proceed(args);
        }
        for (int i = 0, len = args.length; i < len; i++) {
            Object object = args[i];
            if (null != object) {
                if (null == result && object instanceof BindingResult) {
                    result = (BindingResult) object;
                }
                if (null != argAnnotations[i] && argAnnotations[i].length > 0) {//检测是否带注解
                    if (null != result) {
                        break;
                    }
                }
            }
        }

        if (null != result && writeResult(result)) {
            return null;
        }

        return joinPoint.proceed(args);
    }

    protected boolean writeResult(BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            if (null != fieldErrors && fieldErrors.size() > 0) {
                ActResult actResult = new ActResult();
                actResult.setCode(2);
                actResult.setMsg("参数校验不通过");
                actResult.setData(new JsrTip(fieldErrors.get(0).getField(), fieldErrors.get(0).getDefaultMessage()));
                ResponseContext.writeData(JSON.toJSONString(actResult));
                return true;
            }
        }
        return false;
    }

}
