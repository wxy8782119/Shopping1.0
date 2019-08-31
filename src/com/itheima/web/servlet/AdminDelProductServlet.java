package com.itheima.web.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.AdminProductService;
import com.itheima.utils.BeanFactory;

/**
 * Servlet implementation class AdminDelProductServlet
 */
@WebServlet("/adminDelProduct")
public class AdminDelProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDelProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取要删除的pid
		String pid = request.getParameter("pid");
		// 传递pid到service层
//		AdminProductService service = new AdminProductService();
		//使用工厂+反射+配置文件
		AdminProductService service = (AdminProductService) BeanFactory.getBean("AdminProductService");
		try {
			service.delPorductByPid(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//跳转到列表页面
		response.sendRedirect(request.getContextPath()+"/adminproductList");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
