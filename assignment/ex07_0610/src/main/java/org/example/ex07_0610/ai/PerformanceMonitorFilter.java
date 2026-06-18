package org.example.ex07_0610.ai;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

@WebFilter
public class PerformanceMonitorFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        LocalDateTime requestTime = LocalDateTime.now();
        long startTime = System.currentTimeMillis();

        try {
            chain.doFilter(request, response);
        } finally {
            long endTime = System.currentTimeMillis();
            long executionTime = endTime - startTime;

            System.out.println(
                    "[" + requestTime + "] "
                            + httpRequest.getRequestURI()
                            + " - "
                            + executionTime
                            + "ms 소요."
            );
        }
    }
}