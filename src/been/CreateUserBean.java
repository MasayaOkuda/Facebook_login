package been;
import dao.CreateUserDAO;
import dao.UserDAO;
import mode.User;


public class CreateUserBean {



    public boolean checkLoginIdExist(User bean) {
        try {
            UserDAO dao = new UserDAO();

            //取得したlogin_idと同じ値がないかどうかをチェックする
            if (dao.findUser(bean.getLogin_id()) == null) {


                //同じ値がなければ、新しくlogin_idとpasswordをDBに追加し、trueを返す
                CreateUserDAO createUserDAO = new CreateUserDAO();
                createUserDAO.createUser(bean.getLogin_id(), bean.getPassword());
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        //同じ値が存在すればfalseを返す
        return false;
    }
}
