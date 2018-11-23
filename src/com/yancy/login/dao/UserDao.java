package com.yancy.login.dao;

import com.yancy.login.beans.User;

public interface UserDao {

    public  User getUserByUsernameAndPassword(String username, String password);
}
