<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page isErrorPage="true"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>嚯!</title>
<s:set name="importParams" value="'general|ZeroClipboard.js|common.css|end'" scope="request"/>
<jsp:include page="../snippets/static_js_css.jsp"/>
<jsp:include page="../snippets/hidden_box.jsp"/>
</head>
<body onload="clipboard('clip_button')">
	<%@ include file="../snippets/navigator.jsp"%>
	<div style="text-align: left;">
		<h1 style="font-size: 20px;">报错信息：</h1>
		<s:property value="exception.message" />
<%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" class="btn" id="clip_button" value="将异常信息加入剪切板">--%>

		<br>
		<br>
		 <s:property value="exceptionStack"/>
	</div>
	<%@ include file="../snippets/footer.jsp"%>
</body>
</html>
