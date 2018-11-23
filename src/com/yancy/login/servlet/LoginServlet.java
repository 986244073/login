package com.yancy.login.servlet;

import com.yancy.login.beans.User;
import com.yancy.login.dao.UserDao;
import com.yancy.login.dao.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserDao userDao= new UserDaoImpl() ;
        User user = userDao.getUserByUsernameAndPassword(username, password);
        if (user == null) {
            resp.getWriter().write("error");
        }else {
            resp.getWriter().write("ok");

        }


    }
}
