package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/RootServlet")

public class RootServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");


        HttpSession session = request.getSession(false);

        if (session == null) {
            //セッションがNullの場合はログインページへ
            getServletContext().getRequestDispatcher("/login_form.html").forward(request,response);

        }else {

            //セッション領域のisLogiの値がtrueかを確認、falseならログインページへ
            String isLogin = (String) session.getAttribute("isLogin");
            if (isLogin == null || !(isLogin.equals("true"))) {

                getServletContext().getRequestDispatcher("/login_form.html").forward(request,response);

            }
            //UserPageへ遷移する
            getServletContext().getRequestDispatcher("/user_page.html").forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}
