package ink.moshuier.silken.common;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StrUtils {
	static final String MAIL_REG ="\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"; 
	static final String Name_REG ="^[0-9a-zA-Z\\u4E00-\\u9FA5_]+$"; 
	static final String SIMPLE_REG ="^[0-9a-zA-Z_]+$"; 
static public String removeTag(String raw){
	if(!isEmpty(raw)){
		raw = raw.replaceAll("&lt;img.+?&gt;","").replaceAll("<img.+?>","").replaceAll("&lt;b&gt;","").replaceAll("<b>","").replaceAll("&lt;/b&gt;","").replaceAll("</b>","").replaceAll("&lt;br&gt;","").replaceAll("<\\s*br\\s*>","").replaceAll("<\\s*br\\s*/>","").replaceAll("<p>","").replaceAll("</p>","").replaceAll("&nbsp;","").replaceAll("<span[^>]*>","").replaceAll("</span>","").trim();
		return raw;
	}else{
		return " ";
	}
}
static public boolean isEmpty(String raw){
	return (raw==null || "".equals(raw));
}
static public boolean contains(String raw,String[] params){
	for(String param : params){
		if(StringUtils.contains(raw, param)){
			return true;
		}
	}
	return false;
}
static public boolean valiPhone(String phone){
	boolean p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$").matcher(phone).matches();  
	return p;
}
static public String fileToString(String filePath){
	String line = "";
	StringBuffer sb = new StringBuffer();
	try {
//		文件读入时是按OS的默认字符集即GBK解码的，我先用默认字符集GBK编码str.getBytes(“GBK”)，此时应该还原为文件中的字节序列了，然后再按UTF-8解码，生成的字符串按理说应该就应该是正确的。
//		为什么结果中还是有部分乱码呢？
//		问题出在FileReader读取文件的过程中，FileReader继承了InputStreamReader，但并没有实现父类中带字符集参数的构造函数，所以FileReader只能按系统默认的字符集来解码，然后在UTF-8 -> GBK -> UTF-8的过程中编码出现损失，造成结果不能还原最初的字符。
//		原因明确了，用InputStreamReader代替FileReader，InputStreamReader isr=new InputStreamReader(new FileInputStream(fileName),"UTF-8");这样读取文件就会直接用UTF-8解码，不用再做编码转换。
	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
	while(null != (line = br.readLine())){
		sb.append(line);
	}
		br.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return sb.toString();
}
static public boolean notEmpty(String raw){
	return (raw!=null && !"".equals(raw));
}
static public boolean valiEmail(String email){
	return Pattern.compile(MAIL_REG).matcher(email).matches();
}
static public boolean valiName(String input){
	return Pattern.compile(Name_REG).matcher(input).matches();
}
static public boolean simpleChar(String input){
	return Pattern.compile(SIMPLE_REG).matcher(input).matches();
}
static public boolean url(String input){
	return Pattern.compile(SIMPLE_REG).matcher(input).matches();
}

static public String truncate(String raw,int cnt){
	if(!isEmpty(raw)){
		if(raw.length()>cnt){
			raw = raw.substring(0,cnt-1) + "...";
		}
	}
	return raw;
}
static public String zeroFill(int raw_num,int length){
	String raw = (String.valueOf(raw_num));
	int org_length=raw.length();
	for(int i = 0;i< length - org_length;i++){
		raw = "0" + raw; //+=Ҫ�������ģ�����,���Ҽ������ұ�
	}
	return raw;
}
static public void main(String[] a){
	
	System.out.println(zeroFill(15,4));
}
public static String filter(String message)
{
	if (message == null)
		return null;
	char content[] = new char[message.length()];
	message.getChars(0, message.length(), content, 0);
	StringBuilder result = new StringBuilder(content.length + 50);
	for (int i = 0; i < content.length; i++)
	{
		// ���ƶԼ����ŵ������ַ�����ת��
		switch (content[i])
		{
			case '<':
				result.append("&lt;");
				break;
			case '>':
				result.append("&gt;");
				break;
			case '&':
				result.append("&amp;");
				break;
			case '"':
				result.append("&quot;");
				break;
			default:
				result.append(content[i]);
		}
	}
	return (result.toString());
}
}