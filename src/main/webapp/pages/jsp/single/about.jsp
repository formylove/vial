<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About Me</title>
<%request.setAttribute("importParams", "general|common.css|end"); %>
<%@ include file="../snippets/static_js_css.jsp"%>
</head>
<body>
<%@ include file="../snippets/navigator.jsp" %>
    <%@ include file="../snippets/hidden_box.jsp"%>
<br>
<br>
<br>
<br>
<br>
<div style="text-align:center;min-height:110px;font-size: 20px;">
    此网站由Spring + Struts + Hibernate框架构建，由Maven进行项目管理。项目托管地址<a href="https://github.com/formylove/vial" target="_blank"> <img width="30px" src="https://github.com/fluidicon.png"/></a>
</div>
<div style="text-align:center;min-height:110px;font-size: 20px;">
    如果有合适的开发岗位也可以联系我，目标工作城市，上海，以及苏南城市。
</div>
<div style="text-align:center;min-height:110px;">
    <a href="http://moshuier.ink/about/"  target="_blank" style="color:cadetblue;font-size:30px">我的简历</a>
</div>
<div style="text-align:center;min-height:110px">
    <img src="${pageScope.base}img/stream.gif"  width="650px" style="border-radius: 20px;"/>
</div>
<style>
    .footer{
        margin-top: 100px;
    }
</style>
<%@ include file="../snippets/footer.jsp" %>
</body>
</html>