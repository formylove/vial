<%@ page language="java" import="ink.moshuier.silken.common.WebUtils" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>邮箱激活</title>
<%request.setAttribute("importParams", "general|common.css|end"); %>
<%@ include file="../snippets/static_js_css.jsp"%>
</head>
<body>
<%@ include file="../snippets/hidden_box.jsp"%>
<%@ include file="../snippets/navigator.jsp" %>
<div style="text-align:center;margin-top: 100px">
<div>亲爱的<a style="color:red">${param.nick_name}</a>，还差一步，请激活你的帐号</div>
<br>
<div>系统已发送了一封激活邮件到您的邮箱：<a style="color:red">${param.email}</a></div>
<br>
<div><a href="<%=WebUtils.getMailLoginUrl(request.getParameter("email"))%>" target="_blank"><u>点击链接登录邮箱</a></u></div>
<br>
<div>请在24小时内激完成帐号激活</a></div>
</div>

<%@ include file="../snippets/footer.jsp" %>
</body>
</html>