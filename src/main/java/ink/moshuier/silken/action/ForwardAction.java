package ink.moshuier.silken.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.common.MsgConstants;
import ink.moshuier.silken.service.EssayService;

public class ForwardAction {
String url;
boolean hasForwarded;
int essayId;
	
	public String forward(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		url = request.getParameter("url");
		Log.print(url);
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域请求的问题，这个header就是让服务器支持CORS的
		essayId = 1;//EssayService.forward(url);
		if(essayId == 0){
			hasForwarded = false;
		}else{
			hasForwarded = true;
		}
		return MsgConstants.DONE;
	}
	
	public String hasForwarded(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		url = request.getParameter("url");
		Log.print(url);
		response.setHeader("Access-Control-Allow-Origin", "*");//解决跨域请求的问题，这个header就是让服务器支持CORS的
		hasForwarded = false;//EssayService.hasForwarded(url);
		return MsgConstants.DONE;
	}


	public int getEssayId() {
		return essayId;
	}

	public void setEssayId(int essayId) {
		this.essayId = essayId;
	}

	public boolean isHasForwarded() {
		return hasForwarded;
	}

	public void setHasForwarded(boolean hasForwarded) {
		this.hasForwarded = hasForwarded;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
