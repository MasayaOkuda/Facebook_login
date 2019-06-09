package servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //新しいセッションを作らない
        HttpSession session = request.getSession(false);

        //セッションがNullでなければ削除する
        if (session != null) {
            System.out.println("session.invalidate()");
            session.invalidate();
        }

        //ログインページへ遷移する
        response.sendRedirect("login_form.html");
    }


}
