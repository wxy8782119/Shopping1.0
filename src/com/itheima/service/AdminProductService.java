package com.itheima.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.Product;
import com.itheima.vo.Condition;

public interface AdminProductService {
	//查询所有的商品
	public List<Product> findAllProduct() throws SQLException;
	
	//获得所有的类别
	public List<Category> findAllCategory() throws SQLException;
	
	//添加数据
	public void addProduct(Product product) throws SQLException;
	
	//根据pid删除商品
	public void delPorductByPid(String pid) throws SQLException;
	
	//根据pid查询商品对象
	public Product findProductByPid(String pid) throws SQLException;
	
	//更新商品
	public void updateProduct(Product product) throws SQLException;
	
	//根据条件查询商品列表
	public List<Product> findProductListByCondition(Condition condition) throws SQLException;
	
	//获得所有的订单
	public List<Order> findAllOrders();
	
	//根据订单id查询订单项和商品信息
	public List<Map<String, Object>> findOrderInfoByOid(String oid);

}
