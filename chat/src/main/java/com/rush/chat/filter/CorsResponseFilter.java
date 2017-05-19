package com.rush.chat.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 跨域访问设置过滤器
 *
 */
public class CorsResponseFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String method = httpServletRequest.getMethod();
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.addHeader("Access-Control-Allow-Credentials", "true");
        httpServletResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        httpServletResponse.addHeader("P3P","CP='IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT'");
        switch (method){
            case "OPTIONS" :

                httpServletResponse.addHeader("Access-Control-Allow-Headers", "Cookie,Authorization,Range,DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
                httpServletResponse.addHeader("Access-Control-Expose-Headers", "Accept-Ranges,Content-Encoding,Content-Length,Content-Range");

                break;
            case "POST":
            case "PUT":
            case "DELETE":
                httpServletResponse.addHeader("Access-Control-Allow-Headers", "Cookie,Authorization,DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
                break;
            case "GET":
                httpServletResponse.addHeader("Access-Control-Allow-Headers", "Cookie,Authorization,Range,DNT,X-Mx-ReqToken,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");
                httpServletResponse.addHeader("Access-Control-Expose-Headers", "Accept-Ranges,Content-Encoding,Content-Length,Content-Range");
                break;
            case "HEAD":
                break;
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
