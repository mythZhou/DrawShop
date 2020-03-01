package com.imooc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.imooc.dao.UserDao;
import com.imooc.domain.User;
import com.imooc.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {

	@Override
	public User login(User user) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		//�������
		conn=JDBCUtils.getConnection();
		//��дSQL���
		String sql="select * from user where username=? and password=?";
		//Ԥ����SQL
		pstmt=conn.prepareStatement(sql);
		//���ò���
		pstmt.setString(1, user.getUsername());
		pstmt.setString(2, user.getPassword());
		//ִ��
		rs=pstmt.executeQuery();
		if(rs.next()) {
			User existUser=new User();
			existUser.setUid(rs.getInt("uid"));
			existUser.setUsername(rs.getString("username"));
			existUser.setPassword(rs.getString("password"));
			return existUser;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		
		return null;
	}

}
