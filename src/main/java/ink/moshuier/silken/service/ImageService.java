package ink.moshuier.silken.service;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import ink.moshuier.silken.common.FileUtils;
import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.common.MessageUtils;
import ink.moshuier.silken.common.SqlUtils;
import ink.moshuier.silken.common.TimeManager;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ImageService {


	//���ش�·��

	
	static public String cut(File image,String picType,float width,float height,float x,float y) throws IOException{
        
			FileInputStream is = null;  
        ImageInputStream iis = null;  
        String name=UUID.randomUUID().toString();
        String path=MessageUtils.getMessageFromUrl("img.path");
        @SuppressWarnings("deprecation")
		String absPath = new java.io.File(((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).getRealPath("/img/")).getParent();
        System.out.println("+++" + absPath);
        try {  
            // ��ȡͼƬ�ļ�  
            is = new FileInputStream(image);  
            /* 
             * ���ذ������е�ǰ��ע�� ImageReader �� Iterator����Щ ImageReader �����ܹ�����ָ����ʽ�� 
             * ������formatName - ��������ʽ��ʽ���� . ������ "jpeg" �� "tiff"���� �� 
             */  
            Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(picType);  
            ImageReader reader = it.next();  
            // ��ȡͼƬ��  
            iis = ImageIO.createImageInputStream(is);  
            /* 
             * <p>iis:��ȡԴ.true:ֻ��ǰ���� </p>.�������Ϊ ��ֻ��ǰ�������� 
             * ��������ζ�Ű���������Դ�е�ͼ��ֻ��˳���ȡ���������� reader ���⻺���������ǰ�Ѿ���ȡ��ͼ����������ݵ���Щ���벿�֡� 
             */  
            reader.setInput(iis, true);  
            /* 
             * <p>������ζ������н������<p>.����ָ�����������ʱ�� Java Image I/O 
             * ��ܵ��������е���ת��һ��ͼ���һ��ͼ�������ض�ͼ���ʽ�Ĳ�� ������ ImageReader ʵ�ֵ� 
             * getDefaultReadParam �����з��� ImageReadParam ��ʵ���� 
             */  
            ImageReadParam param = reader.getDefaultReadParam();  
            /* 
             * ͼƬ�ü�����Rectangle ָ��������ռ��е�һ������ͨ�� Rectangle ���� 
             * �����϶�������꣨x��y������Ⱥ͸߶ȿ��Զ���������� 
             */  
            Rectangle rect = new Rectangle((int)x, (int)y, (int)width, (int)height);  
            // �ṩһ�� BufferedImage���������������������ݵ�Ŀ�ꡣ  
            param.setSourceRegion(rect);  
            /* 
             * ʹ�����ṩ�� ImageReadParam ��ȡͨ������ imageIndex ָ���Ķ��󣬲��� ����Ϊһ�������� 
             * BufferedImage ���ء� 
             */  
            BufferedImage bi = reader.read(0, param);  
            // ������ͼƬ  
            Log.print(path);
            Log.print(path);
            ImageIO.write(bi, picType, new File(absPath+path+name)); 
        } finally {  
            if (is != null)  
                is.close();  
            if (iis != null)  
                iis.close();  
        }  
  
		
		return name+"."+picType;
	}
	
	public static String getSimpleType(String originType){
		String simpleType="jpg";
		if(originType.indexOf("png")>=0){
			simpleType="png";
		}else if(originType.indexOf("gif")>=0){
			simpleType="gif";
			
		}else if(originType.indexOf("bmp")>=0){
			simpleType="png";
			
		}else if(originType.indexOf("ico")>=0){
			simpleType="ico";
		}
		return simpleType;
	}
	public static int savePhoto(Map<String,Object> data){
		return SqlUtils.executeInsert(data, "photo");
	}
	public static int savePhoto(String name,String title,String desc,int under){
		Map<String,Object> data=new HashMap<String, Object>();
		data.put("name", name);
		data.put("title", title);
		data.put("desc", desc);
		data.put("under", under);
		data.put("create_date", TimeManager.getDate());
		data.put("create_time", TimeManager.getTime());
		return SqlUtils.executeInsert(data, "photo");
	}
		
}
