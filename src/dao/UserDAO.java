package dao;

import mode.User;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
    Connection con;

    public User findUser(String loginId) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/facebook3";
            String user = "root";
            String pass = "himitu";

            con = DriverManager.getConnection(url,user,pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        PreparedStatement st=null;
        ResultSet rs=null;

        try {
            String sql = "SELECT * from users where login_id = ?";
            st = con.prepareStatement(sql);
            st.setString(1, loginId);
            rs = st.executeQuery();


            ArrayList<User> list = new ArrayList<>();
            while (rs.next()) {
                String login_id = rs.getString("login_id");
                String password = rs.getString("password");
                User bean = new User(login_id, password);
                list.add(bean);
            }

            return list.get(0);

        } catch (Exception e) {

            e.printStackTrace();

        }finally {
            try{
                if(rs!=null)rs.close();
                if(st!=null)st.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        return null;
    }

}
