package com.itheima.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.dao.AdminProductDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.service.AdminProductService;
import com.itheima.vo.Condition;

public class AdminProductServiceImpl implements AdminProductService {
	// 查询所有的商品
	public List<Product> findAllProduct() throws SQLException {
		// 因为没有复杂业务，直接传递请求到dao层
		AdminProductDao dao = new AdminProductDao();
		List<Product> productList = dao.findAllProduct();
		return productList;
	}

	// 获得所有的类别
	public List<Category> findAllCategory() throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findAllCategory();
	}

	// 添加数据
	public void addProduct(Product product) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.addProduct(product);

	}

	// 根据pid删除商品
	public void delPorductByPid(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.delPorductByPid(pid);

	}

	// 根据pid查询商品对象
	public Product findProductByPid(String pid) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findProductByPid(pid);
	}

	// 更新商品
	public void updateProduct(Product product) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		dao.updateProduct(product);
	}

	// 根据条件查询商品列表
	public List<Product> findProductListByCondition(Condition condition) throws SQLException {
		AdminProductDao dao = new AdminProductDao();
		return dao.findProductListByCondition(condition);
	}

	// 获得所有的订单
	public List<Order> findAllOrders() {
		AdminProductDao dao = new AdminProductDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	// 根据订单id查询订单项和商品信息
	public List<Map<String, Object>> findOrderInfoByOid(String oid) {
		AdminProductDao dao = new AdminProductDao();
		List<Map<String, Object>> mapList = null;
		try {
			mapList = dao.findOrderInfoByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}
}
