package com.example.demo;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.util.AntPathMatcher;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by linla on 2021/7/27.
 */
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private static final String TOKEN = "Authorization";

    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个 option请求，这里我们给 option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (isLoginAttempt(request, response)) {
            return executeLogin(request, response);
        }
        return false;
    }

    /**
     * 判断用户是否想要登入。
     * 检测header里面是否包含Authorization字段即可
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(TOKEN);
        return token != null;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        String token = httpServletRequest.getHeader(TOKEN); //得到token
//        JWTToken jwtToken = new JWTToken(UofferUtil.decryptToken(token)); // 解密token
//        try {
//            // 提交给realm进行登入，如果错误他会抛出异常并被捕获
//            getSubject(request, response).login(jwtToken);
//            // 如果没有抛出异常则代表登入成功，返回true
//            return true;
//        } catch (Exception e) {
//            log.error(e.getMessage());
//            return false;
//        }
        return false;
    }

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
//        log.debug("Authentication required: sending 401 Authentication challenge response.");
//        HttpServletResponse httpResponse = WebUtils.toHttp(response);
////        httpResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
//        httpResponse.setCharacterEncoding("utf-8");
//        httpResponse.setContentType("application/json; charset=utf-8");
//        final String message = "未认证，请在前端系统进行认证";
//        final Integer status = 401;
//        try (PrintWriter out = httpResponse.getWriter()) {
////            String responseJson = "{\"message\":\"" + message + "\"}";
//            JSONObject responseJson = new JSONObject();
//            responseJson.put("msg", message);
//            responseJson.put("status", status);
//            out.print(responseJson);
//        } catch (IOException e) {
//            log.error("sendChallenge error：", e);
//        }
        return false;
    }
}
