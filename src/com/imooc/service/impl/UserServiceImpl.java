package com.imooc.service.impl;

import com.imooc.dao.UserDao;
import com.imooc.dao.impl.UserDaoImpl;
import com.imooc.domain.User;
import com.imooc.service.UserService;

public class UserServiceImpl implements UserService {

	@Override
	public User login(User user) {
		//����dao�ķ�����ѯ�û��Ƿ����
		UserDao userDao=new UserDaoImpl();
		return userDao.login(user);
	}

}
