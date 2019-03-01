package ink.moshuier.silken.common;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import ink.moshuier.silken.common.gif.AnimatedGifEncoder;
import ink.moshuier.silken.common.gif.GifDecoder;
import net.coobird.thumbnailator.Thumbnails;

public class ImageUtils {
	static public String baseRealPath = ((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).getRealPath("/");
	static public String tempPath = MessageUtils.getMessageFromUrl("img.temp");
	static public String depotPath = MessageUtils.getMessageFromUrl("img.depot");
	static public final String MIDDLE = "middle";
	static public final String THUMBNAIL = "thumb";
	static public final String BIG = "big";
	static public final String PORTRAIT = "portrait";
	static public final String NHORIZONTAL = "nHorizontal";
	static public final String NVERTICAL = "nVertical";
	static public final String ESSAY = "essay";
	static public final String CATEGORY = "category";
	static public final String POSTER = "poster";
	static public final String MUSIC = "music";
	static public String saveImageFromUrl(String imgUrl){
		String localName = null;
	        try {  
	            //ʵ����url  
	            URL url = new URL(imgUrl);  
	            URLConnection connection = url.openConnection();
	            connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
	            //����ͼƬ��������  
	            java.io.BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());  
	            //ʵ�����洢�ֽ�����  
	            byte[] bytes = new byte[100];  
	            //����д��·���Լ�ͼƬ����  
	             localName = UUID.randomUUID().toString();
			     localName = localName + "." + getSimpleType(imgUrl);
			     String realPath = baseRealPath + tempPath;
				 File folder = new File(realPath);
			     if(!folder.exists()){
						folder.mkdir();//д�ļ����������Զ�����Ŀ¼
					}
			     OutputStream bos = new FileOutputStream(new File(realPath+localName));
	             int len;  
	             while ((len = bis.read(bytes)) > 0) {  
	                bos.write(bytes, 0, len);  
	            }  
	            bis.close();  
	            bos.flush();  
	            bos.close();  
	        } catch (Exception e) { 
	        }  

		return localName;
	}
	static public String saveImageFromUrlToDepot(String imgUrl){
		String localName = null;
		try {  
			//ʵ����url  
			URL url = new URL(imgUrl);  
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//����ͼƬ��������  
			java.io.BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());  
			//ʵ�����洢�ֽ�����  
			byte[] bytes = new byte[100];  
			//����д��·���Լ�ͼƬ����  
			localName = UUID.randomUUID().toString();
			localName = localName + "." + getSimpleType(imgUrl);
			String realPath = baseRealPath + depotPath;
			File folder = new File(realPath);
			if(!folder.exists()){
				folder.mkdir();//д�ļ����������Զ�����Ŀ¼
			}
			OutputStream bos = new FileOutputStream(new File(realPath+localName));
			int len;  
			while ((len = bis.read(bytes)) > 0) {  
				bos.write(bytes, 0, len);  
			}  
			bis.close();  
			bos.flush();  
			bos.close();  
		} catch (Exception e) { 
		}  
		
		return localName;
	}
	
	static public String saveImage(File image,String org_name,String realPath) throws IOException{
		 String localName = UUID.randomUUID().toString();
		 localName = localName + FileUtils.getFileSuffix(org_name);
		 File folder = new File(realPath);
		if(!folder.exists()){
			folder.mkdir();//д�ļ����������Զ�����Ŀ¼
		}
		 return FileUtils.saveFile(image, localName,realPath);
	 }
	static public String saveImage4Editor(File image,String org_name) throws IOException{
		 String realPath = baseRealPath + depotPath;
		 return ImageUtils.saveImage(image, org_name,realPath);
	}
	static public String saveTemp(File image,String org_name) throws IOException{
		String realPath = baseRealPath + tempPath;
		return ImageUtils.saveImage(image, org_name,realPath);
	}
	
		static public String cut(String cover,float width,float height,float x,float y) {
			String simpleType = getSimpleType(cover);
			String processedName = null;
			try {
			if("gif".equalsIgnoreCase(simpleType)){
				if(cover.indexOf("http")>=0 || cover.indexOf("https")>=0 || cover.indexOf("www.")>=0){
					cover = saveImageFromUrl(cover);
				}	
				String sourcePath = baseRealPath + tempPath + cover;
				processedName = cutGif(sourcePath, (int)x, (int)y,(int)width, (int)height);
			}else{
				if(cover.indexOf("http")>=0 || cover.indexOf("https")>=0 || cover.indexOf("www.")>=0){
					URL url = new URL(cover);
					URLConnection connection = url.openConnection();
					connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
					processedName = cut(new BufferedInputStream(connection.getInputStream()),simpleType,width,height,x,y);
				}else{
		    	    FileInputStream fis = new FileInputStream(new File(baseRealPath + tempPath+cover));
		    	    processedName = cut(new BufferedInputStream(fis),simpleType,width,height,x,y);
				}	
			}
			
			return processedName;
		    } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//ɾ��������ͼƬ
			deleteTemp(cover);
		return null;
		}

		static public String cut(String cover,float width,float height,float x,float y,String type){
			String processedName =  cut( cover, width, height, x, y);
			try {
			if(FileUtils.getFileSuffix(processedName).equalsIgnoreCase(".gif")){
				
			}else if(type.equals(POSTER)){
				Thumbnails.of(baseRealPath+depotPath+processedName)  
				.scale(1000/width)
				.toFile(baseRealPath+depotPath+processedName);
			}else if(type.equals(PORTRAIT)){//������
			Thumbnails.of(baseRealPath+depotPath+processedName)  
			.scale(110/width)
			.toFile(baseRealPath+depotPath+processedName);
			}else if(type.equals(NHORIZONTAL)){
				Thumbnails.of(baseRealPath+depotPath+processedName)   
				.scale(220/width)   
				.toFile(baseRealPath+depotPath+processedName);  
			}else if(type.equals(NVERTICAL)){
				Thumbnails.of(baseRealPath+depotPath+processedName)   
				.scale(120/width)   
				.toFile(baseRealPath+depotPath+processedName);  
			}else if(type.equals(MUSIC)){
				Thumbnails.of(baseRealPath+depotPath+processedName)   
				.scale(1f)   
				.toFile(baseRealPath+depotPath+generateIsoName(processedName,BIG));  
				Thumbnails.of(baseRealPath+depotPath+processedName)   
				.scale(60/width)   
				.toFile(baseRealPath+depotPath+generateIsoName(processedName,THUMBNAIL));  
				Thumbnails.of(baseRealPath+depotPath+processedName)   
				.scale(220/width)   
				.toFile(baseRealPath+depotPath+processedName);  
			}else if(type.equals(CATEGORY)){
				Thumbnails.of(baseRealPath+depotPath+processedName)   
				.scale(50/width)   
				.toFile(baseRealPath+depotPath+generateIsoName(processedName,THUMBNAIL));  
			}
			
			} catch (IOException e) {
				e.printStackTrace();
			}  
			return processedName;
		}
		static public String generateIsoName(String processedName,String suffix){
			return processedName.substring(0, processedName.lastIndexOf("."))+"-"+suffix+FileUtils.getFileSuffix(processedName);
		}
	static public String cut(BufferedInputStream image,String picType,float width,float height,float x,float y) throws IOException{
	   String sourcePath = null;
	   BufferedInputStream is = null;  
       ImageInputStream iis = null;  
       String name=UUID.randomUUID().toString();
       String path=depotPath;
       try {  
           // ��ȡͼƬ�ļ�  
           is = image;  
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
           File folder = new File(baseRealPath + path);
           if(!folder.exists()){
        	   folder.mkdir();
           }
           sourcePath = baseRealPath + path + name+"."+picType;
           ImageIO.write(bi, picType, new File(sourcePath)); 
       } finally {  
           if (is != null)  
               is.close();  
           if (iis != null)  
               iis.close();  
       } 
		return name+"."+picType;
	}
	
	public static String cutGif(String sourcePath,int x,int y,int width,int height){
	     //Ϊgif����һ�ݾ�̬����
		String name=UUID.randomUUID().toString();
		String path=depotPath;
		String simpleType = getSimpleType(sourcePath);
		String targetPath = baseRealPath + path + name + "." + simpleType;
		            GifDecoder decoder = new GifDecoder();  
		            int status = decoder.read(sourcePath);  
		            if (status != GifDecoder.STATUS_OK) {  
		                try {
							throw new IOException("read image " + sourcePath + " error!");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}  
		            }
		 
		            AnimatedGifEncoder encoder = new AnimatedGifEncoder();
		            encoder.start(targetPath);
		            encoder.setRepeat(decoder.getLoopCount());  
		            Log.print("֡����"+decoder.getFrameCount());
		            for (int i = 0; i < decoder.getFrameCount(); i ++) {  
		                encoder.setDelay(decoder.getDelay(i));  
		                BufferedImage childImage = decoder.getFrame(i);
		                BufferedImage subImage = childImage.getSubimage(x, y, width, height);
		                encoder.addFrame(subImage);  
		            }  
		            encoder.finish();
		return  name + "." + simpleType;
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
		}else if(originType.indexOf("jpeg")>=0){
			simpleType="jpeg";
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
	static public int[] getImgWidth(File img){
		int[] a ={0,0};
        BufferedImage bi = null;  
        boolean imgwrong=false;  
        try {  
            //��ȡͼƬ  
            bi = javax.imageio.ImageIO.read(img);  
            try{  
                //�ж��ļ�ͼƬ�Ƿ���������ʾ,��ЩͼƬ���벻��ȷ  
                int i = bi.getType();  
                imgwrong=true;  
            }catch(Exception e){  
                imgwrong=false;  
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
        if(imgwrong){  
            a[0] = bi.getWidth(); //��� ���  
            a[1] = bi.getHeight(); //��� �߶�  
        }else{  
            a=null;  
        }  
        //ɾ���ļ�  
        img.delete();  
		return a;
	}
	static public void deleteImg(String name){
		FileUtils.deleteFile(depotPath+name);
		FileUtils.deleteFile(depotPath+ImageUtils.generateIsoName(name,ImageUtils.THUMBNAIL));
		FileUtils.deleteFile(depotPath+ImageUtils.generateIsoName(name,ImageUtils.MIDDLE));
		FileUtils.deleteFile(depotPath+ImageUtils.generateIsoName(name,ImageUtils.BIG));
	}
	static public void deleteTemp(String name){
		FileUtils.deleteFile(tempPath+name);
	}
}
