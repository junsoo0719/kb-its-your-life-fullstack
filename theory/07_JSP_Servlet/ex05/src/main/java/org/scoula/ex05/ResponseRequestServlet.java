package org.scoula.ex05;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/response_redirect")
public class ResponseRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String username = (String) req.getAttribute("username");
        String useraddress = (String) req.getAttribute("useraddress");

        //속성 설정
        req.setAttribute("username", username);
        req.setAttribute("useraddress", useraddress);

        //forward
        RequestDispatcher dis = req.getRequestDispatcher("/redirect_res.jsp");
        dis.forward(req, res);
    }

}
