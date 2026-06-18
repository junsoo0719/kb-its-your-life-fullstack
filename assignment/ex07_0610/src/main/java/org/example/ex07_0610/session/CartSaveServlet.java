package org.example.ex07_0610.session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/cart_save")
public class CartSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        String product = req.getParameter("product");

        HttpSession session = req.getSession();
        ArrayList<String> list = (ArrayList<String>) session.getAttribute("product");

        if (list == null) {
            list = new ArrayList<>();
            session.setAttribute("product", list);
        }

        list.add(product);

        out.println("<html><body>");
        out.println("Product 추가!! <br>");
        out.println("<a href='session_product.jsp'>상품 선택 페이지</a><br>");
        out.println("<a href='cart_view'>장바구니 보기</a>");
        out.println("</body></html>");
    }
}
