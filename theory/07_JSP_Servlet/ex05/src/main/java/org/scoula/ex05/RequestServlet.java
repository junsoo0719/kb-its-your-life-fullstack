package org.scoula.ex05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/request")
public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //속성 설정
        req.setAttribute("username", "홍길동");
        req.setAttribute("useraddress", "서울");

        // forward
        RequestDispatcher dis = req.getRequestDispatcher("/res.jsp");
        dis.forward(req, resp);
    }
}
