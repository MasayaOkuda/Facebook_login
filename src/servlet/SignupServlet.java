package servlet;

import been.CreateUserBean;
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

@WebServlet("/SignupServlet")

public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");


        //Get login_id & password
        String loginId = request.getParameter("login_id");
        String password = request.getParameter("password");

        // ログインIDとパスワードに文字列があれば
        if (loginId.length() != 0 && password.length() != 0) {

            //パスワードのハッシュ化
            String hashedPass = PasswordToHash.getHashedPassword(loginId, password);

            //UserModelを作成
            User user = new User(loginId, hashedPass);

            CreateUserBean createUserBean = new CreateUserBean();


            //取得したログインIDと同じ値が、DBに存在しなければDBにレコードを追加する
            if (createUserBean.checkLoginIdExist(user)) {

                //セッションを作成
                HttpSession session = request.getSession();
                System.out.println("setAttribute");
                session.setAttribute("isLogin", "true");

                //User Pageへ遷移
                getServletContext().getRequestDispatcher("/user_page.html").forward(request, response);

            } else {

                //取得したログインIDと同じ値がDBに存在すれば、SignUp Pageへ戻る
                RequestDispatcher rd=request.getRequestDispatcher("/signup_error.html");
                rd.forward(request,response);
            }

        }else {

            //入力値が空白ならSignUp Pageへ戻る
            RequestDispatcher rd = request.getRequestDispatcher("/login_form.html");
            rd.forward(request,response);
        }


    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
