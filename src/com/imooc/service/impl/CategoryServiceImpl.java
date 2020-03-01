package com.imooc.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.imooc.dao.CategoryDao;
import com.imooc.dao.ProductDao;
import com.imooc.dao.impl.CategoryDaoImpl;
import com.imooc.dao.impl.ProductDaoImpl;
import com.imooc.domain.Category;
import com.imooc.domain.Product;
import com.imooc.service.CategoryService;
import com.imooc.utils.JDBCUtils;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		//����CategoryDao�ķ���
		CategoryDao categoryDao=new CategoryDaoImpl();
		System.out.println("CategoryService��findAll����ִ���ˡ�����");
		return categoryDao.findAll();
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao=new CategoryDaoImpl();
		categoryDao.save(category);
	}

	@Override
	public Category findOne(Integer cid) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao=new CategoryDaoImpl();
		return categoryDao.findOne(cid);
	}

	@Override
	public void update(Category category) {
		// TODO Auto-generated method stub
		CategoryDao categoryDao=new CategoryDaoImpl();
		categoryDao.update(category);
	}

	@Override
	public void delete(Integer cid) {
		// TODO Auto-generated method stub
		/**
		 * ���������ҵ���ͳһ�������Ӷ��󣬱�֤���DAOʹ��ͬһ������
		 * 1.��������֮�󣬽����Ӷ��󴫵ݸ�dao
		 * 2.����һ�����Ӷ��󣬽����Ӱ󶨵���ǰ�߳��С���ThreadLocal��
		 */
		Connection conn=null;
		try {
			conn=JDBCUtils.getConnection();
			//��������
			conn.setAutoCommit(false);
			//Ҫ��ɾ������֮ǰ���ֽ������÷������Ʒ�ȴ���һ��
			ProductDao productDao=new ProductDaoImpl();
			List<Product> list=productDao.findByCid(cid);
			for(Product product:list) {
				product.getCategory().setCid(null);
				productDao.update(conn,product);
			}
			//ɾ������
			CategoryDao categoryDao=new CategoryDaoImpl();
			categoryDao.delete(conn,cid);
			//�ύ����:
			conn.commit();
		}catch(Exception e) {
			
			//�ع�����
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
