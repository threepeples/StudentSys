package cn.csnu.com;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tt
 * @Date: 2021/12/28 12:27
 */
@Component
public class JsonpPostFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //String origin = (String) servletRequest.getRemoteHost() + ":"+ servletRequest.getRemotePort();
        //构造头部信息
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods","POST, GET, OPTIONS, DELETE, PUT");
        response.setHeader("Access-Control-Max-Age", "3600");

        //请求头允许自定义字段：Token1和Token2
        //response.setHeader("Access-Control-Allow-Headers","x-requested-with,Authorization,Token1,Token2");
        //response.setHeader("Access-Control-Allow-Credentials", "true");

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {


    }

}