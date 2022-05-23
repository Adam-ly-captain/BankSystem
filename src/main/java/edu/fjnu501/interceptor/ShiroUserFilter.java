package edu.fjnu501.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.fjnu501.domain.Result;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 重写shiro的UserFilter，实现通过OPTIONS请求
 * UserFilter会过滤掉所有没有登录cookie的请求
 * PathMatchingFilter则不会
 * @author MDY
 */
public class ShiroUserFilter extends UserFilter {

    /**
     * 在访问过来的时候检测是否为OPTIONS请求，如果是就直接返回true
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println(httpRequest.getMethod());
        httpResponse.setHeader("Access-control-Allow-Origin", "http://127.0.0.1:5500/");
        httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        httpResponse.setHeader("Content-Type","application/json;charset=UTF-8");
        httpResponse.setStatus(HttpStatus.OK.value());
        if (httpRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            System.out.println(2);
            setHeader(httpRequest,httpResponse);
            return true;
        }
        return super.preHandle(httpRequest,httpResponse);
//        HttpServletResponse response1 = (HttpServletResponse) response;
//        HttpServletRequest request1 = (HttpServletRequest) request;
//        // 允许哪些Origin发起跨域请求
//        // response.setHeader( "Access-Control-Allow-Origin", config.getInitParameter( "AccessControlAllowOrigin" ) );
//        response1.setHeader( "Access-Control-Allow-Origin", "http://127.0.0.1:5500/" );
//        // 允许请求的方法
//        response1.setHeader( "Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT" );
//        //多少秒内,不需要再发送预检验请求，可以缓存该结果
//        response1.setHeader( "Access-Control-Max-Age", "3600" );
//        // 表明它允许跨域请求包含xxx头
//        response1.setHeader( "Access-Control-Allow-Headers", "x-auth-token,Origin,Access-Token,X-Requested-With,Content-Type, Accept" );
//        //是否允许浏览器携带用户身份信息（cookie）
//        response1.setHeader( "Access-Control-Allow-Credentials", "true" );
//        //prefight请求
//        if (request1.getMethod().equals( "OPTIONS" )) {
//            response1.setStatus( 200 );
//            return true;
//        }
//        return super.preHandle(request, response);
    }

    /**
     * 该方法会在验证失败后调用，这里由于是前后端分离，后台不控制页面跳转
     * 因此重写改成传输JSON数据
     */
    @Override
    protected void saveRequestAndRedirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        saveRequest(request);
        setHeader((HttpServletRequest) request,(HttpServletResponse) response);
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.println(objectMapper.writeValueAsString(new Result(400, "未登录", null)));
        out.flush();
        out.close();
    }

    /**
     * 为response设置header，实现跨域
     */
    private void setHeader(HttpServletRequest request, HttpServletResponse response){
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", request.getMethod());
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }

}