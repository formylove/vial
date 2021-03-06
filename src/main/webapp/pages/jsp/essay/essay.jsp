<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title><s:property value="essay.title"/></title>
<s:set name="importParams" value="'general|essay.css|content.css|agent.js|musicPlayer.js|comment|end'" scope="request"/>
<%@ include file="../snippets/static_js_css.jsp" %>
</head>
<body>
<%@ include file="../snippets/navigator.jsp" %>
<%@ include file="../snippets/hidden_box.jsp"%>
<input type="hidden" id="append_id" name="document_details" data-type="essay" value="${essay.id}">
<div class="container ct-sm" style="min-height: 221px;">	
		<nav class="article-nav">
			<a href="essays/" class="nav-back">
				<i class="icon-back"></i>
				返回专栏首页
			</a>
		</nav>
		<div class="essay-detail-wrapper">	
			<h1 class="essay-title">${essay.title}</h1>
			<!-- essay meta -->
			<jsp:include page="../snippets/essayMeta.jsp"/>
				<!-- 音乐 -->
		<s:if test="essay.music != null">
		<%@ include file="../snippets/musicColumn.jsp"%>
			</s:if>

				<!-- 文章内容 -->
					<div class="essay-content">
					<s:property value="essay.content" escape="false"/>
				<br>
				<br>
				<br>
					 </div>
			<!-- 点赞区 -->		 
			<jsp:include page="../snippets/shareArea.jsp"/>		 
			<!-- 作者信息 -->		 
					 
			<s:if test="essay.author != null && essay.author != '' ">
			<%@ include file="../snippets/userColumn.jsp"%>
			</s:if>
	</div>	
<%@ include file="../snippets/comments.jsp"%>
		
		</div>
		
<%@ include file="../snippets/footer.jsp" %>
<script>

</script>
</body>
</html>