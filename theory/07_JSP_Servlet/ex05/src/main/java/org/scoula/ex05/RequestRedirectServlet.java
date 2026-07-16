package org.scoula.ex05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/request_redirect")
public class RequestRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //속성 설정
        req.setAttribute("username", "홍길동");
        req.setAttribute("useraddress", "서울");

        //redirect
        res.sendRedirect("response_redirect");
    }

}
