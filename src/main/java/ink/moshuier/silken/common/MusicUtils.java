package ink.moshuier.silken.common;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;



public class MusicUtils {
	static public String baseRealPath = ((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).getRealPath("/");
	static public String path = MessageUtils.getMessageFromUrl("music.path");
	
	static public String save(File music,String type) throws IOException{
		UUID newName = UUID.randomUUID();
		if(music != null){
			FileUtils.saveFile(music, newName + type, path);
		}
		return newName.toString();
	}
	static public void delete(String musicName){
		if(StringUtils.isNotEmpty(musicName)){
			File file = new File(path + musicName);
			file.deleteOnExit();
		}
	}
	static public String getRealPath(String musicName){
		return path + musicName;
	}
}
