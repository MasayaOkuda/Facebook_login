package dao;

import java.sql.*;

public class CreateUserDAO {

    Connection con;

    public void createUser(String login_id, String inPassword) throws SQLException {

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
            String sql = "INSERT INTO users (login_id, password) value (?,?)";
            st = con.prepareStatement(sql);

            st.setString(1, login_id);
            st.setString(2, inPassword);
            st.executeUpdate();

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
    }
}
