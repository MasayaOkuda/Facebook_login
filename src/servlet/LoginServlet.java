package servlet;

import been.LoginBean;
import db.PasswordToHash;
import mode.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.setContentType("text/html;charset=UTF-8");

        //loginIDとパスワードをフォームから取得する
        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");


        //パスワードのハッシュ化
        String hashedPass = PasswordToHash.getHashedPassword(loginId, password);

        //UserModelを作成
        User user = new User(loginId, hashedPass);

        LoginBean lb = new LoginBean();

        //ログインIDとパスワードが一致するレコードがあれば、ユーザーページへ遷移する
        if (lb.getUser(user)) {

            //セッションを作成
            HttpSession session = request.getSession();
            System.out.println("setAttribute");
            session.setAttribute("isLogin", "true");

            //User Pageへ遷移
            getServletContext().getRequestDispatcher("/user_page.html").forward(request, response);


        } else {

            //ログインIDとパスワードが一致するレコードがなければ、ログインエラーページへ遷移する
            getServletContext().getRequestDispatcher("/login_error.html").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login_form.html");
    }

}
