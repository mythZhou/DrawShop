package com.imooc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.imooc.dao.ProductDao;
import com.imooc.domain.Product;
import com.imooc.utils.JDBCUtils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findAll() {
		// TODO Auto-generated method stub
	//	System.out.println("productdao的findall");
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Product> list=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="select * from product p,category c WHERE p.cid=c.cid order by p.pid asc";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			list=new ArrayList<Product>();
			while(rs.next()) {
				Product product=new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				//封装商品所属的分类
				product.getCategory().setCid(rs.getInt("cid"));
				product.getCategory().setCname(rs.getString("cname"));
				product.getCategory().setCdesc(rs.getString("cdesc"));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
	}

	@Override
	public void save(Product product) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="insert into product values(null,?,?,?,?,?,?,?)";
			//预编译
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getAuthor());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getFilename());
			pstmt.setString(6, product.getPath());
			pstmt.setInt(7, product.getCategory().getCid());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public Product findOne(Integer pid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="SELECT * FROM product p,category c WHERE p.cid=c.cid AND p.pid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			//System.out.println(pid);
			rs=pstmt.executeQuery();
			
			//System.out.println(rs.getInt("pid"));
			if(rs.next()) {
				//封装数据
			//	System.out.println("111");
				Product product=new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				product.getCategory().setCid(rs.getInt("cid"));
				product.getCategory().setCname(rs.getString("cname"));
				product.getCategory().setCdesc(rs.getString("cdesc"));
				return product;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs,pstmt, conn);
		}
		return null;
	}

	@Override
	public void update(Product product) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="update product set pname=?,author=?,price=?,description=?,filename=?,path=?,cid=? where pid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getAuthor());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getFilename());
			pstmt.setString(6, product.getPath());
			pstmt.setObject(7, product.getCategory().getCid());
			pstmt.setInt(8, product.getPid());
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public void delete(Integer pid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="delete from product where pid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, pid);
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(pstmt, conn);
		}
	}

	@Override
	public List<Product> findByCid(Integer cid) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Product> list=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="select * from product p,category c WHERE p.cid=c.cid and p.cid=? order by p.pid asc";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, cid);
			rs=pstmt.executeQuery();
			list=new ArrayList<Product>();
			while(rs.next()) {
				Product product=new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				//封装商品所属的分类
				product.getCategory().setCid(rs.getInt("cid"));
				product.getCategory().setCname(rs.getString("cname"));
				product.getCategory().setCdesc(rs.getString("cdesc"));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
		//return null;
	}
	@Override
	public void update(Connection conn, Product product) {
		// TODO Auto-generated method stub
		//Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			//conn=JDBCUtils.getConnection();
			String sql="update product set pname=?,author=?,price=?,description=?,filename=?,path=?,cid=? where pid=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, product.getPname());
			pstmt.setString(2, product.getAuthor());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getDescription());
			pstmt.setString(5, product.getFilename());
			pstmt.setString(6, product.getPath());
			pstmt.setObject(7, product.getCategory().getCid());
			pstmt.setInt(8, product.getPid());
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

	@Override
	public int findCount() {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		Long count=0l;
		try {
			conn=JDBCUtils.getConnection();
			String sql="select count(*) as count from product";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count=rs.getLong("count");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return count.intValue();
	}

	@Override
	public List<Product> findByPage(int begin, int limit) {
		// TODO Auto-generated method stub
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<Product> list=null;
		try {
			conn=JDBCUtils.getConnection();
			String sql="select * from product limit ?,?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, begin);
			pstmt.setInt(2, limit);
			rs=pstmt.executeQuery();
			list=new ArrayList<Product>();
			while(rs.next()) {
				Product product=new Product();
				product.setPid(rs.getInt("pid"));
				product.setPname(rs.getString("pname"));
				product.setAuthor(rs.getString("author"));
				product.setPrice(rs.getDouble("price"));
				product.setDescription(rs.getString("description"));
				product.setFilename(rs.getString("filename"));
				product.setPath(rs.getString("path"));
				list.add(product);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.release(rs, pstmt, conn);
		}
		return list;
	}
}
