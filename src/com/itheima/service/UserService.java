package com.itheima.service;

import java.sql.SQLException;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;

public class UserService {
	//效验用户名是否存在
	public boolean checkUsername(String username) throws SQLException {
		UserDao dao = new UserDao();
		long isExist = dao.checkUsername(username);
		return isExist>0?true:false;
	}

	public User login(String username, String password) throws SQLException {
		UserDao dao = new UserDao();
		return dao.login(username,password);
	}

	public boolean regist(User user) {
		UserDao dao = new UserDao();
		int row = 0;
		try {
			row = dao.regist(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return row>0?true:false;
	}

	//激活
	public void active(String activeCode) {
		UserDao dao = new UserDao();
		try {
			dao.active(activeCode);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
