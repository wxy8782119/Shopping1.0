package com.itheima.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itheima.dao.ProductDao;
import com.itheima.domain.Category;
import com.itheima.domain.Order;
import com.itheima.domain.OrderItem;
import com.itheima.domain.Product;
import com.itheima.utils.DataSourceUtils;
import com.itheima.vo.PageBean;

public class ProductService {

	public List<Product> findAllPorduct() throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findAllPorduct();
	}

//	public PageBean findPageBean(int currentPage,int currentCount) throws SQLException {
//		ProductDao dao = new ProductDao();
//		
//		//目的：就是想办法封装一个PageBean并返回
//		PageBean pageBean = new PageBean();
//		
//		// 1.当前页private int currentPage;
//		pageBean.setCurrentPage(currentPage);
//		
//		// 2.当前页显示的条数private int currentCount;
//		pageBean.setCurrentCount(currentCount);
//		
//		// 3.总条数private int totalCount;
//		int totalCount = dao.getTotalCount();
//		pageBean.setTotalCount(totalCount);
//		
//		// 4.总页数private int totalPage;
//		int totalPage = (int) Math.ceil(1.0*totalCount/currentCount);
//		pageBean.setTotalPage(totalPage);
//		// 5.每页显示的数据private List<T> productList =  new ArrayList<T>();
//		//索引index = (当前页数-1)*每页显示的条数
//		int index = (currentPage-1)*currentCount;
//		List<Product> productList = dao.findProductListForPageBean(index,currentCount);
//		pageBean.setProductList(productList);
//		
//		return pageBean;
//	}
	
	//根据关键字查询商品
	public List<Object> findProductByWord(String word) throws SQLException {
		ProductDao dao = new ProductDao();
		return dao.findProductByWord(word);
	}

	//获得热门商品
	public List<Product> findHotProductList() {
		ProductDao dao = new ProductDao();
		List<Product> hotProductList = null;
		try {
			hotProductList = dao.findHotProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hotProductList;
	}

	//获得最新商品
	public List<Product> findNewProductList() {
		ProductDao dao = new ProductDao();
		List<Product> newProductList = null;
		try {
			newProductList = dao.findNewProductList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newProductList;
	}

	//分类数据
	public List<Category> findAllCategory() {
		ProductDao dao = new ProductDao();
		List<Category> categoryList = null;
		try {
			categoryList = dao.findAllCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	//分页显示某种类别的商品列表
	public PageBean findProductListByCid(String cid,int currentPage,int currentCount) {
		
		ProductDao dao = new ProductDao();
		
		//封装一个PageBean，返回web层
		PageBean<Product> pageBean = new PageBean<Product>();
		//1.封装当前页
		pageBean.setCurrentPage(currentPage);
		//2.封装每页显示的条数
		pageBean.setCurrentCount(currentCount);
		//3.封装总条数
		int totalCount = 0;
		try {
			totalCount = dao.getCount(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setTotalCount(totalCount);
		//4.封装总页数
		int totalPage =(int) Math.ceil(1.0*totalCount/currentCount);
		pageBean.setTotalPage(totalPage);
		
		//5.当前页显示的数据
		//select * from produce where cid=？ limit ？，？
		//当前页与起始索引index的关系
		int index = (currentPage-1)*currentCount;
		List<Product> productList = null;
		try {
			productList = dao.findProductByPage(cid,index,currentCount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pageBean.setProductList(productList);
		return pageBean;
	}

	public Product findProductByPid(String pid) {
		ProductDao dao = new ProductDao();
		Product product = null;
		try {
			product = dao.findProductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;
	}

	//提交订单，将订单的数据和订单项的数据存储到数据库中
	public void submitOrder(Order order) {
		ProductDao dao = new ProductDao();
		try {
			//1.开启事务
			DataSourceUtils.startTransaction();
			//2.调用dao存储order表数据的方法
			dao.addOrders(order);
			//3.调用dao存储orderitem表数据的方法
			dao.addOrderItem(order);
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void updateOrderAdrr(Order order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderAdrr(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public void updateOrderState(String r6_Order) {
		ProductDao dao = new ProductDao();
		try {
			dao.updateOrderState(r6_Order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	//获得指定用户的订单集合
	public List<Order> findAllOrders(String uid) {
		ProductDao dao = new ProductDao();
		List<Order> orderList = null;
		try {
			orderList = dao.findAllOrders(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderList;
	}

	public List<Map<String,Object>> findAllOrderItemByOid(String oid) {
		ProductDao dao = new ProductDao();
		List<Map<String,Object>> mapList = null;
		try {
			mapList = dao.findAllOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mapList;
	}
}
