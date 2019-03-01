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

@WebFilter
public class UrlFilter implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,	FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest)req;
//		String url=request.getRequestURI().toLowerCase();
//	HttpServletResponse response=(HttpServletResponse) res;
//	 if(Pattern.compile("/essays/?$").matcher(url).find()){//$Ҫ����
//		response.sendRedirect("essayAction.action?method:listEssay&page=1&category=5000&category=5006");//������ȫ���·���һ�����󣬻���Ҫ���¹���
//	}else if(Pattern.compile("/essays/\\d{1,100}/?$").matcher(url).find()){//$Ҫ����
//		Matcher m = Pattern.compile("\\d{1,100}/?$").matcher(url);
//		m.find();
//		int page = Integer.parseInt(m.group().replace("/", ""));
//		response.sendRedirect("essayAction.action?method:listEssay&page=" + page +"&category=5000&category=5006");//������ȫ���·���һ�����󣬻���Ҫ���¹���
//	}else if(Pattern.compile("/essays/\\d{1,100}/c(/5\\d{4})+/?$").matcher(url).find()){//$Ҫ����
//		Matcher m = Pattern.compile("\\d{1,100}/c").matcher(url);
//		m.find();
//		int page = Integer.parseInt(m.group().replace("/c", ""));		
//		m = Pattern.compile("5\\d{4}").matcher(url);
//		String categories = "";
//		while(m.find()){
//			categories = categories + "&category=" + m.group();
//		}
//		response.sendRedirect("essayAction.action?method:listEssay&page=" + page + categories);//������ȫ���·���һ�����󣬻���Ҫ���¹���
//	} 
//	else if(Pattern.compile("/essay/2\\d{4}/?$").matcher(url).find()){//$Ҫ����
//		Matcher m = Pattern.compile("2\\d{4}/?$").matcher(url);
//		m.find();
//		String id = m.group().replace("/", "");
//		response.sendRedirect("essayAction.action?method:loadEssay&id=" + id);//������ȫ���·���һ�����󣬻���Ҫ���¹���
//	} 
//	else if(Pattern.compile("/edit/2\\d{4}/?$").matcher(url).find()){
//		Matcher m = Pattern.compile("2\\d{4}/?$").matcher(url);
//		m.find();
//		String id = m.group().replace("/", "");
//		response.sendRedirect("essayAction.action?method:editEssay&id=" + id);
//	} else{
filterChain.doFilter(req, res);}
//	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
