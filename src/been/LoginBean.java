package been;

import dao.UserDAO;
import mode.User;

public class LoginBean {

    public boolean getUser(User bean) {

        try {
            UserDAO dao = new UserDAO();

            //取得したlogin_idと同じ値のレコードが存在すれば、レコードをUser型で返す
            User DBUser = dao.findUser(bean.getLogin_id());

            //レコードのpasswordと取得したpasswordが一致すればtrueを、しなければfalseを返す
            if (DBUser.getPassword().equals(bean.getPassword())) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}
