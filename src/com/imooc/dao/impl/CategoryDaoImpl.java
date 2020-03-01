package com.imooc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.CategoryDao;
import com.imooc.domain.Category;
import com.imooc.utils.JDBCUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		//System.out.println("CategoryDao��findAll����ִ���ˡ�����");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Category> list=null;
		try {
		//1.�������
			conn=JDBCUtils.getConnection();
		//2.��дSQL���
			String sql="select * from category";
		//3.Ԥ����SQL
			pstmt=conn.prepareStatement(sql);
		//4.���ò���
		//5.ִ��SQL
			rs=pstmt.executeQuery();
		//6.�������
			list=new ArrayList<Category>();
			while(rs.next()) {
				Category category=new Category();
				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				category.setCdesc(rs.getString("cdesc"));
				
				list.add(category);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		//7.�ͷ���Դ
			JDBCUtils.release(rs, pstmt, conn);
		}
		

		return list;
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="insert into category values(null,?,?)";
			pstmt=conn.prepareStatement(sql);
			//���ò���
			pstmt.setString(1, category.getCname());
			pstmt.setString(2, category.getCdesc());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public Category findOne(Integer cid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//�������
			conn=JDBCUtils.getConnection();
			String sql="select * from category where cid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs=pstmt.executeQuery();
			//��������
			if(rs.next()) {
				Category category=new Category();
				category.setCid(rs.getInt("cid"));
				category.setCname(rs.getString("cname"));
				category.setCdesc(rs.getString("cdesc"));
				return category;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return null;
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			//�������
			conn=JDBCUtils.getConnection();
			String sql="update category set cname=? ,cdesc=? where cid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category.getCname());
			pstmt.setString(2,category.getCdesc());
			pstmt.setInt(3, category.getCid());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public void delete(Integer cid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="delete from category where cid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}
	public void delete(Connection conn, Integer cid) {
		// TODO Auto-generated method stub
		//Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			//conn=JDBCUtils.getConnection();
			String sql="delete from category where cid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			//JDBCUtils.release(pstmt, conn);
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
