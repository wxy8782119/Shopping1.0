<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/logo2.png" />
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			
			<c:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>
			<c:if test="${!empty user }">
				<li>欢迎您，${user.username }</li>
				<li><a href="${pageContext.request.contextPath }/user?method=logout">退出</a></li>
			</c:if>
			
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath }/product?method=myOrders">我的订单</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUl">
					<!-- <c:forEach items="${categoryList }" var="category">
						<li><a href="#">${category.cname }</a></li>
					
					</c:forEach> -->
				</ul>
				<form class="navbar-form navbar-right" role="search">
					<div class="form-group" style="position:relative">
						<input id="search" type="text" class="form-control" placeholder="Search" onkeyup="searchWord(this)">
						<div id="showDiv" style="display:none; position:absolute; z-index:1000; background:#fff; width:196px; border:1px solid #ccc;">
							
						</div>
					</div>
					<button type="submit" class="btn btn-default">Submit</button>
				</form>
				<!-- 完成异步搜索 -->
				<script type="text/javascript">
					//header.jsp加载完毕后 去服务器端获得所有的category数据
					$(function(){
						var content = "";
						$.post(
							"${pageContext.request.contextPath}/product?method=categoryList",
							function(data){
								//[{"cid":"xxx","cname":"xxxx"},{},{}]
								//动态创建<li><a href="#">${category.cname }</a></li>
								for(var i=0;i<data.length;i++){
									content+="<li><a href='${pageContext.request.contextPath}/product?method=productList&cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
								}
								
								//将拼接好的li放置到ul中
								$("#categoryUl").html(content);
							},
							"json"
						);
					});
				
				
					function overFn(obj){
						$(obj).css("background","#DBEAF9");
					}
					function outFn(obj){
						$(obj).css("background","#fff");
					}
					
					function clickFn(obj){
						$("#search").val($(obj).html());
						$("#showDiv").css("display","none");
					}
					
					function searchWord(obj){
						//1.获得输入框的输入内容
						var word = $(obj).val();
						//2.根据输入框的内容去数据库中模糊查询---List<Product>
						var content = "";
						$.post(
							"${pageContext.request.contextPath}/searchWord",
							{"word":word},
							function(data){
								if(data.length>0){
									//3.返回的商品的名称，现在showDiv中
									for(var i=0;i<data.length;i++){
										content+="<div style='padding:3px; cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"+data[i]+"</div>"
									}
									$("#showDiv").html(content);
									$("#showDiv").css("display","block");
								}
								
							},
							"json"
						);
						
					}
				</script>
			</div>
		</div>
	</nav>
</div>