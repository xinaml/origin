package com.bjike.common.handler;

import com.bjike.common.exception.ActException;
import com.bjike.common.http.ResponseContext;
import com.bjike.common.restful.ActResult;
import com.bjike.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class ActionExceptionHandler extends AbstractHandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionExceptionHandler.class);
    private static final String JSON_CONTEXT = "text/html;charset=utf-8";
    private static final int SUCCESS_STATUS = 200;
    private static final int EXCEPTION_STATUS = 500;
    private static final int EXCEPTION_CODE = 1;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        ActResult actResult = new ActResult();
        httpServletResponse.setContentType(JSON_CONTEXT);
        if (!StringUtil.isChinese(e.getMessage())) {
            actResult.setMsg("服务器错误");
        }else {
            actResult.setMsg(e.getMessage());
        }
        e.printStackTrace();
        if (e instanceof ActException) {
            actResult.setCode(EXCEPTION_CODE);
            httpServletResponse.setStatus(SUCCESS_STATUS);
        } else {
            String msg = handleJapException(e);
            if (null != msg) {
                actResult.setMsg(msg);
            }
            httpServletResponse.setStatus(EXCEPTION_STATUS);
            actResult.setCode(EXCEPTION_CODE);
        }
        if ("expire".equals(e.getMessage())) {
            actResult.setCode(401);
            actResult.setMsg("登录已过期!");
        } else if ("notLogin".equals(e.getMessage())) {
            actResult.setCode(403);
            actResult.setMsg("用户未登录!");
        }

        ResponseContext.writeData(actResult);
        return new ModelAndView();
    }

    private String handleJapException(Exception throwable) {
        String str = null;
        Throwable tb = throwable.getCause();
        if (null != tb) {
            str = tb.getCause().getMessage();
            if (str.startsWith("Column")) {
                str = str.replaceAll("Column", "列");
                str = str.replaceAll("cannot be null", "不能为空!");
                return str;
            }
            if (str.startsWith("Duplicate entry")) {
                str = StringUtils.substringAfter(str, "Duplicate entry");
                str = StringUtils.substringBefore(str, "for key");
                return str + "已被占用!";
            }
            if (str.startsWith("Data truncation")) {
                str = StringUtils.substringAfter(str, "Data too long for column");
                str = StringUtils.substringBefore(str, "at row");
                return str + "超出数据长度!";
            }
        }
        return str;
    }
}
