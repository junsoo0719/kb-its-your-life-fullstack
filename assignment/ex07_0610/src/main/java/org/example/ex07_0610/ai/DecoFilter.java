package org.example.ex07_0610.ai;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class DecoFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        System.out.println(req.getRequestURI() + " =======> 출력");

        chain.doFilter(request, response);

        System.out.println("<======= 출력");
    }
}