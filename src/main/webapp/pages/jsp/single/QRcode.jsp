<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微信号</title>
<%request.setAttribute("importParams", "general|common.css|end"); %>
<%@ include file="../snippets/static_js_css.jsp"%>
</head>
<body>
<%@ include file="../snippets/navigator.jsp" %>
    <%@ include file="../snippets/hidden_box.jsp"%>
<div style="text-align:center;">
<img alt="ansyx1002" src="./img/QRcode/wechat.gif">
</div>
<div style="text-align:center;margin-top: 60px;">
<font size="25px">微信号：ansyx1002</font>
</div>
<style>
    .footer{
        margin-top:100px;
    }
</style>
<%@ include file="../snippets/footer.jsp" %>
</body>
</html>