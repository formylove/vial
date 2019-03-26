<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<s:set name="importParams" value="'general|index.js|flexSlider.js|common.css|end'" scope="request"/>
<%@ include file="../snippets/static_js_css.jsp"%>
<title>${pageScope.sitename}</title>
</head>
<body>
<%@ include file="../snippets/navigator.jsp" %>
<%@ include file="../snippets/hidden_box.jsp" %>
<div class="container index-ct" style="min-height: 221px;">
<%@ include file="../homepage/poster.jsp" %>
<%@ include file="../homepage/musicColumn.jsp" %>
<%@ include file="../homepage/articleColumn.jsp" %>
<%-- <jsp:include page="../homepage/somniloquismColumn.jsp" /> --%>
<%-- <jsp:include page="../homepage/criticismColumn.jsp" /> --%>
</div>
<%@ include file="../snippets/footer.jsp" %>
</body>
</html>
