package ink.moshuier.silken.common;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.*;
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
	static public String depotPath = MessageUtils.getMessageFromUrl("img.path");
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
		imgUrl = imgUrl.toLowerCase();
		String localName = null;
		try {
			//实例化url
			URL url = new URL(imgUrl);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//载入图片到输入流
			java.io.BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			//实例化存储字节数组
			byte[] bytes = new byte[100];
			//设置写入路径以及图片名称
			localName = UUID.randomUUID().toString();
			localName = localName + "." + getSimpleType(imgUrl);
			String realPath = baseRealPath + tempPath;
			File folder = new File(realPath);
			if(!folder.exists()){
				folder.mkdir();//写文件操作不会自动生成目录
			}
			OutputStream bos = new FileOutputStream(new File(realPath + localName));
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
			//实例化url
			URL url = new URL(imgUrl);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
			//载入图片到输入流
			java.io.BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
			//实例化存储字节数组
			byte[] bytes = new byte[100];
			//设置写入路径以及图片名称
			localName = UUID.randomUUID().toString();
			localName = localName + "." + getSimpleType(imgUrl);
			File folder = new File(depotPath);
			if(!folder.exists()){
				folder.mkdir();//写文件操作不会自动生成目录
			}
			OutputStream bos = new FileOutputStream(new File(depotPath + localName));
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
		org_name = org_name.toLowerCase();
		String localName = UUID.randomUUID().toString().toLowerCase();
		localName = localName + FileUtils.getFileSuffix(org_name);
		File folder = new File(realPath);
		if(!folder.exists()){
			folder.mkdir();//写文件操作不会自动生成目录
		}
		return FileUtils.saveFile(image, localName, realPath);
	}
	static public String saveImage4Editor(File image,String org_name) throws IOException{
		return ImageUtils.saveImage(image, org_name,depotPath);
	}
	static public String saveTemp(File image,String org_name) throws IOException{
		String realPath = baseRealPath + tempPath;
		return ImageUtils.saveImage(image, org_name, realPath);
	}
    static public String cut(String cover,float w,float h,float x,float y,String type){
	    int width = (int)w;
	    int height = (int)h;

        if(width == 0 || height == 0){
            width = getImgWidth(baseRealPath + tempPath + cover)[0];
            height = getImgWidth(baseRealPath + tempPath + cover)[1];
        }
        String processedName =  cut( cover, width, height, (int)x, (int)y);
        deleteTemp(cover);
        try {
            if(FileUtils.getFileSuffix(processedName).equalsIgnoreCase(".gif")){
				bufferedReaderWriterCopyFile(depotPath + processedName, depotPath + generateIsoName(processedName,THUMBNAIL));
				bufferedReaderWriterCopyFile(depotPath + processedName, depotPath + generateIsoName(processedName,BIG));
            }else if(type.equals(POSTER)){
                Thumbnails.of(depotPath+processedName)
                        .scale(1000.0/width)
                        .toFile(depotPath+processedName);
            }else if(type.equals(PORTRAIT)){//正方形
                Thumbnails.of(depotPath+processedName)
                        .scale(110.0/width)
                        .toFile(depotPath+processedName);
            }else if(type.equals(NHORIZONTAL)){
                Thumbnails.of(depotPath + processedName)
                        .scale(220.0/width)
                        .toFile(depotPath + processedName);
            }else if(type.equals(NVERTICAL)){
                Thumbnails.of(depotPath+processedName)
                        .scale(120.0/width)
                        .toFile(depotPath+processedName);
            }else if(type.equals(MUSIC)){
                Thumbnails.of(depotPath+processedName)
                        .scale(1f)
                        .toFile(depotPath+generateIsoName(processedName,BIG));
                Thumbnails.of(depotPath+processedName)
                        .scale(60.0/width)
                        .toFile(depotPath+generateIsoName(processedName,THUMBNAIL));
                Thumbnails.of(depotPath+processedName)
                        .scale(220.0/width)
                        .toFile(depotPath+processedName);
            }else if(type.equals(CATEGORY)){
                Thumbnails.of(depotPath+processedName)
                        .scale(50.0/width)
                        .toFile(depotPath+generateIsoName(processedName,THUMBNAIL));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return processedName;
    }
	static public String cut(String cover,int width,int height,int x,int y) {
		String simpleType = getSimpleType(cover);
		String processedName = null;
		try {
			if("gif".equalsIgnoreCase(simpleType)){
				if(cover.indexOf("http")>=0 || cover.indexOf("https")>=0 || cover.indexOf("www.")>=0){
					cover = saveImageFromUrl(cover);
				}
				String sourcePath = baseRealPath + tempPath + cover;
				processedName = cutGif(sourcePath, x, y,width, height);
			}else{
				if(cover.indexOf("http")>=0 || cover.indexOf("https")>=0 || cover.indexOf("www.")>=0){
					URL url = new URL(cover);
					URLConnection connection = url.openConnection();
					connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
					processedName = cut(new BufferedInputStream(connection.getInputStream()),simpleType,width,height,x,y);
				}else{
					FileInputStream fis = new FileInputStream(new File(baseRealPath + tempPath + cover));
					processedName = cut(new BufferedInputStream(fis),simpleType,width,height,x,y);
				}
			}

			return processedName;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//删除缓存中图片
		deleteTemp(cover);
		return null;
	}



	static public String cut(BufferedInputStream image,String picType,int width,int height,int x,int y) throws IOException{
		String sourcePath = null;
		BufferedInputStream is = null;
		ImageInputStream iis = null;
		String name=UUID.randomUUID().toString();
		String path=depotPath;
		try {
			// 读取图片文件
			is = image;
			/*
			 * 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader 声称能够解码指定格式。
			 * 参数：formatName - 包含非正式格式名称 . （例如 "jpeg" 或 "tiff"）等 。
			 */
			Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(picType);
			ImageReader reader = it.next();
			// 获取图片流
			iis = ImageIO.createImageInputStream(is);
			/*
			 * <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索’。
			 * 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			 */
			reader.setInput(iis, true);
			/*
			 * <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			 * 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件 将从其 ImageReader 实现的
			 * getDefaultReadParam 方法中返回 ImageReadParam 的实例。
			 */
			ImageReadParam param = reader.getDefaultReadParam();
			/*
			 * 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			 * 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			 */

//			if(width == 0 || height == 0){
//				width = reader.getWidth(0);
//				height = reader.getHeight(0);
//			}
			Rectangle rect = new Rectangle(x, y, width, height);
			// 提供一个 BufferedImage，将其用作解码像素数据的目标。
			param.setSourceRegion(rect);
			/*
			 * 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将 它作为一个完整的
			 * BufferedImage 返回。
			 */
			BufferedImage bi = reader.read(0, param);
			// 保存新图片
			Log.print(path);
			File folder = new File(path);
			if(!folder.exists()){
				folder.mkdir();
			}
			sourcePath = path + name+"."+picType;
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
		//为gif保存一份静态备份
		String name=UUID.randomUUID().toString();
		String path=depotPath;
		String simpleType = getSimpleType(sourcePath);
		String targetPath = path + name + "." + simpleType;
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
		Log.print("帧数："+decoder.getFrameCount());
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
		originType = originType.toLowerCase();
		String simpleType="jpg";
		if(originType.indexOf(".png")>=0){
			simpleType="png";
		}else if(originType.indexOf(".gif")>=0){
			simpleType="gif";

		}else if(originType.indexOf(".bmp")>=0){
			simpleType="png";

		}else if(originType.indexOf(".ico")>=0){
			simpleType="ico";
		}else if(originType.indexOf(".jpeg")>=0){
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
	private static void bufferedReaderWriterCopyFile(String src, String des)  throws IOException {
		File srcFile = new File(src);
		File desFile = new File(des);
		         // 使用带缓冲区的高效字符流进行文件复制
				 BufferedInputStream br = new BufferedInputStream(new FileInputStream(srcFile));
				 BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(desFile));

		         byte[] b = new byte[1024];
		         Integer len = 0;
		         while((len = br.read(b)) != -1) {
			             bw.write(b, 0, len);
			         }

		         /*String s = null;
14         while((s = br.readLine()) != null) {
15             bw.write(s);
16             bw.newLine();
17         }*/
		         br.close();
		         bw.close();
		     }
	static public int[] getImgWidth(String  imgPath){
        String picType = getSimpleType(imgPath);
        int[] a ={0,0};

        FileInputStream fis = null;
        try {
        File image = new File(imgPath);
        fis = new FileInputStream(image);
        BufferedInputStream bis = new BufferedInputStream(fis);
        Iterator<ImageReader> it = ImageIO.getImageReadersByFormatName(picType);
        ImageReader reader = it.next();
        ImageInputStream iis = ImageIO.createImageInputStream(bis);
        reader.setInput(iis, true);
        ImageReadParam param = reader.getDefaultReadParam();
        a[0] = reader.getWidth(0);
        a[1] = reader.getHeight(0);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }



//        BufferedImage bi = null;
//        boolean imgwrong=false;

//		try {
//			//读取图片
//			bi = javax.imageio.ImageIO.read(img);
//			try{
//				//判断文件图片是否能正常显示,有些图片编码不正确
//				int i = bi.getType();
//				imgwrong=true;
//			}catch(Exception e){
//				imgwrong=false;
//			}
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		if(imgwrong){
//			a[0] = bi.getWidth(); //获得 宽度
//			a[1] = bi.getHeight(); //获得 高度
//		}else{
//			a=null;
//		}
//		//删除文件
//		img.delete();
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
    static public String generateIsoName(String processedName,String suffix){
        return processedName.substring(0, processedName.lastIndexOf("."))+"-"+suffix+FileUtils.getFileSuffix(processedName);
    }
}
