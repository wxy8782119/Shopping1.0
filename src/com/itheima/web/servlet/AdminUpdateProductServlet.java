package com.itheima.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Category;
import com.itheima.domain.Product;
import com.itheima.service.AdminProductService;
import com.itheima.utils.BeanFactory;

/**
 * Servlet implementation class AdminUpdateProductServlet
 */
@WebServlet("/adminUpdateProduct")
public class AdminUpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminUpdateProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//目的：收集表单的数据，封装一个Product实体，将上传图片存到服务器磁盘上
//
//		Product product = new Product();
//		
//		//收集数据的容器
//		Map<String,Object> map = new HashMap<String, Object>();
//		
//		try {
//			//创建磁盘文件项工厂
//			DiskFileItemFactory factory = new DiskFileItemFactory();
//			//创建文件上传核心对象
//			ServletFileUpload upload = new ServletFileUpload(factory);
//			//解析request获得文件项集合
//			List<FileItem> parseRequest = upload.parseRequest(request);
//			for(FileItem item : parseRequest) {
//				//判断是否是普通表单项
//				boolean formField = item.isFormField();
//				if(formField) {
//					//普通表单项，获得表单的数据，封装到Product实体中
//					String fieldName = item.getFieldName();
//					String fieldValue = item.getString("UTF-8");
//					
//					map.put(fieldName,fieldValue);
//					
//				}else {
//					//文件上传项，获得文件的名称和内容
//					String fileName = item.getName();
//					String path = this.getServletContext().getRealPath("upload");
//					InputStream in = item.getInputStream();
//					OutputStream out = new FileOutputStream(path+"/"+fileName);
//					IOUtils.copy(in, out);
//					in.close();
//					out.close();
//					item.delete();
//					
//					map.put("pimage","upload/"+fileName);
//					
//				}
//				
//			}
//			BeanUtils.populate(product, map);
//			//是否product对象封装完全
//			product.setPid(CommonsUtils.getUUID());
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//上架日期
//			String pdate = format.format(new Date());
//			product.setPdate(pdate);
//			product.setPflag(0);//商品是否下架，0代表未下架
//			Category category = new Category();
//			category.setCid(map.get("cid").toString());
//			product.setCategory(category);
//			
//			//传递数据给service层
//			AdminProductService service = new AdminProductService();
//			try {
//				service.updateProduct(product);
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			
//			//跳转到列表页面
//			response.sendRedirect(request.getContextPath()+"/adminproductList");
//			
//		} catch (Exception e1) {
//			e1.printStackTrace();
//		}
		
		
		
		
		
		
		
		
		
		request.setCharacterEncoding("UTF-8");
		
		//1.获取数据
		Map<String,String[]> properties = request.getParameterMap();
		//2.封装数据
		Product product = new Product();
		try {
			BeanUtils.populate(product, properties);
		}catch(IllegalAccessException | InvocationTargetException e){
			e.printStackTrace();
		}
		
		//此位置Product已经封装完毕-------将表单的数据封装完毕
		//手动设置表单中没有数据
		product.setPimage("products/1/c_0033.jpg");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");//上架日期
		String pdate = format.format(new Date());
		product.setPdate(pdate);
		product.setPflag(0);//商品是否下架，0代表未下架
		
		Category category = new Category();
		String cid = request.getParameter("cid");
		category.setCid(cid);
		product.setCategory(category);
		
		//3.传递数据给service层
//		AdminProductService service = new AdminProductService();
		//使用工厂+反射+配置文件
		AdminProductService service = (AdminProductService) BeanFactory.getBean("AdminProductService");
		try {
			service.updateProduct(product);
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
