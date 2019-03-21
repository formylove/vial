<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<s:set name="importParams" value="'general|common.css|end'" scope="request"/>
<%@ include file="../snippets/static_js_css.jsp"%>
<title></title>
</head>
<body>
<%@ include file="../snippets/navigator.jsp" %>
<%@ include file="../snippets/hidden_box.jsp"%>
<div class="container index-ct" style="min-height: 221px;">	


<jsp:include page="./musicSolo.jsp"/>





</div>
<%@ include file="../snippets/footer.jsp" %>
</body>
</html>
