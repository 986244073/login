package com.yancy.login.dao;

import com.yancy.login.Util.ConnectionUtils;
import com.yancy.login.beans.User;

import java.sql.*;

public class UserDaoImpl implements UserDao {
    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        User u=null;
        try {
            /*Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/User";
            String user = "root";
            String pawd = "123456";
            Connection connection = DriverManager.getConnection(url, user, pawd);*/
            Connection connection = ConnectionUtils.getConn();
            String sql ="select id,username,password from tb1_user where username=? and password=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                u=new User();
                u.setId(rs.getInt("id"));
                u.setName(rs.getString("username"));
                u.setPassword(rs.getString("password"));
            }
            return u;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ConnectionUtils.closeConn();
            } catch (SQLException e) {
            }
        }
        return null;
    }
}
