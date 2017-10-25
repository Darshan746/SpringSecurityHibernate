package com.darshan.dao;

import com.darshan.bean.User;

public interface UserDao {

	User findByUserName(String username);

}
