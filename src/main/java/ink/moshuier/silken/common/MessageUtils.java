package ink.moshuier.silken.common;

import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

public class MessageUtils {
	public static String getMessageFromDbInfo(String key){
		
		ResourceBundle rb=ResourceBundle.getBundle("dbinfo");
		
		return decode(rb.getString(key));
	}
	public static String getMessageFromPrompt(String key){
		
		ResourceBundle rb=ResourceBundle.getBundle("prompt");
		
		return decode(rb.getString(key));
	}
	public static String getMessageFromError(String key){
		
		ResourceBundle rb=ResourceBundle.getBundle("error");
		
		return decode(rb.getString(key));
	}
	public static String getMessageFromUrl(String key){
		
		ResourceBundle rb=ResourceBundle.getBundle("url");
		
		return decode(rb.getString(key));
	}

	public static String decode(String str){
		try {
			return new String(str.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
		public static void main(String[] strings){
			System.out.println(MessageUtils.getMessageFromDbInfo("user"));


		}
}