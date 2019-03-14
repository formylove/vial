<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>About Me</title>
<%request.setAttribute("importParams", "general|common.css|end"); %>
<jsp:include page="../snippets/static_js_css.jsp"/>
    <jsp:include page="../snippets/hidden_box.jsp"/>
</head>
<body>
<%@ include file="../snippets/navigator.jsp" %>
<br>
<br>
<br>
<br>
<br>
<div style="text-align:center;min-height:110px">
<s:property value='@ink.moshuier.silken.common.MessageUtils@getConfig("about.poet")'/>
</div>
<%@ include file="../snippets/footer.jsp" %>
</body>
</html>