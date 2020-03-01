package com.imooc.service.impl;

import java.util.List;

import com.imooc.dao.ProductDao;
import com.imooc.dao.impl.ProductDaoImpl;
import com.imooc.domain.PageBean;
import com.imooc.domain.Product;
import com.imooc.service.ProductService;

public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
		ProductDao productDao=new ProductDaoImpl();
		System.out.println("productservice的findall");
		return productDao.findAll();
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		ProductDao productDao=new ProductDaoImpl();
		productDao.save(product);
	}

	@Override
	public Product findOne(Integer pid) {
		// TODO Auto-generated method stub
		ProductDao productDao=new ProductDaoImpl();
		return productDao.findOne(pid);
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		ProductDao productDao=new ProductDaoImpl();
		productDao.update(product);
	}

	@Override
	public void delete(Integer pid) {
		// TODO Auto-generated method stub
		ProductDao productDao=new ProductDaoImpl();
		productDao.delete(pid);
	}

	@Override
	public PageBean<Product> findByPage(int page) {
		// TODO Auto-generated method stub
		PageBean<Product> pageBean=new PageBean<>();
		//封装当前的页数
		pageBean.setPage(page);
		//封装每页显示的记录数
		int limit=6;
		pageBean.setLimit(limit);
		//封装总记录数
		ProductDao productDao=new ProductDaoImpl();
		int totalCount=productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		int totalPage=0;
		if(totalCount%limit==0) {
			totalPage=totalCount/limit;
		}else {
			totalPage=totalCount/limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//封装每页显示的集合
		int begin=(page-1)*limit;
		List<Product> list=productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

}
