package ink.moshuier.silken.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.service.UserService;

@WebFilter
public class testFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,	FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
		String url=request.getRequestURI().toLowerCase();
		if(!(url.indexOf(".js")>=0 || url.indexOf(".css")>=0 || url.indexOf(".png")>=0)){
			System.out.println("###########testFilter###########"+request.getRequestURI());}
		filterChain.doFilter(req, res);
	
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
