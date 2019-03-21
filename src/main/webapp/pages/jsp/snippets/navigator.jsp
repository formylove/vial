	<%@page pageEncoding="UTF-8"%>
	<%@taglib prefix="s" uri="/struts-tags"%>
	<%@page import="org.springframework.web.context.WebApplicationContext" %>
	<%@page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
	<%@page import="ink.moshuier.silken.service.UserService" %>
	<%@page import="ink.moshuier.silken.entity.User" %>
	<%
	WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	UserService userService = (UserService) ctx.getBean("userService");  
	User loginedUser = userService.getcurLoginUser(request);
	request.setAttribute("loginedUser", loginedUser);
	%>
	<div class="header">
		<div class="header-ct">
			<a href="" class="logo">
				<span class="icon-logo"></span><s:property value="#sitename"/>
			</a>
			<div class="nav" >
				<ul class="clearfix">
					<li><a href="essays/">文字</a></li>
					<li><a href="musics/">音乐</a></li>
<!-- 					<li><a href="tsukkomi/">心情</a></li> -->
<!-- 					<li><a href="somniloquy/">梦呓 </a></li>				 -->
<!-- 					<li><a href="notes/">札记 </a></li>				 -->
<!-- 					<li><a href="illustration/">长廊</a></li>				 -->
<!-- 					<li><a href="essays/">留言</a></li>				 -->
				</ul>

			</div>
			<%--搜索栏--%>
				<%--<div class="head-search">--%>
					<%--<form class="search-form" action="/search/" id="headSearchForm">--%>
						<%--<input class="search-input" name="q" autocomplete="off" type="text">--%>
						<%--<button class="search-btn" type="submit">--%>
							<%--<span class="icon-search"></span>--%>
						<%--</button>--%>
					<%--</form>--%>
				<%--</div>	--%>
		<%if(loginedUser == null){ %>
		<!-- logged-out-wrapper todo -->
			<div style="display: block;" class="logged-out-wrapper" id="loggedOutWrapper" >
				<a href="javascript:;" rel="nofollow" class="ln-top-login"> 注册/登录 </a>
			</div>
			<!-- logged-out-wrapper -->
			<div class="logged-in-wrapper hidden" id="loggedInWrapper">
			<a href="javascript:;" rel="nofollow" class="ln-message" id="lnMessage">
				<%--todo--%>
			<span class="icon-message" style="visibility: hidden;"></span>
			</a>
			<div class="account-more">
			<a href="javascript:;" rel="nofollow" class="ln-account" >
			</a>		
</div>
</div>
		<%}else{ %>
			<div class="logged-out-wrapper hidden"
				id="loggedOutWrapper">
				<a href="javascript:;" rel="nofollow" class="ln-top-login">
					注册/登录 </a>
			</div>
			<div class="logged-in-wrapper" id="loggedInWrapper" data-level ="<%=loginedUser.getAuthority()%>"
				style="display: block;">
				<a href="javascript:;" rel="nofollow" class="ln-message" id="lnMessage" style="visibility: hidden;"> <span class="icon-message"></span>
				</a>
				<div class="account-more">
					<a href="javascript:;" rel="nofollow" class="ln-account" id="lnAccountMore">
						<c:if test="${loginedUser.portrait == pageScope.avatarName}">
							<img src="${pageScope.defaultAvatar}" alt="<%=loginedUser.getNick_name()%>" class="avatar"/>
						</c:if>
						<c:if test="${loginedUser.portrait != pageScope.avatarName}">
							<img src="${imageUrl}<%=loginedUser.getPortrait()%>" alt="<%=loginedUser.getNick_name()%>" class="avatar"/>
						</c:if>
					&nbsp;<span><%=loginedUser.getNick_name()%></span>
					</a>
				</div>
			</div>
		<%}%>
</div>
		</div>
