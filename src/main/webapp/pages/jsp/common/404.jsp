<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>页面丢失_404</title>
<%request.setAttribute("importParams", "jquery|Akita.js|qtip|form|validate|common.css|end"); %>

<%@ include file="../snippets/static_js_css.jsp"%>
</head>
<body style="background-color:#daecee">
<%@ include file="../snippets/navigator.jsp" %>
<%@ include file="../snippets/hidden_box.jsp"%>
<div style="text-align:center;">
<img src="img/404/404_1.png" alt="404 page not found"/>
</div>
<br>
<br>
<div style="text-align:center;">
<font size="15px">页面被忍者偷走啦</font>
</div>
<%@ include file="../snippets/footer.jsp" %>
</body>
</html>

