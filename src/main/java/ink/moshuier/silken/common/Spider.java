package ink.moshuier.silken.common;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Spider {
 static public String SendGet(String url) {
  // ����һ���ַ��������洢��ҳ����
  String result = "";
  // ����һ�������ַ�������
  BufferedReader in = null;
  try {
   // ��stringת��url����
   URL realUrl = new URL(url);
   // ��ʼ��һ�����ӵ��Ǹ�url������
   URLConnection connection = realUrl.openConnection();
   connection.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
   // ��ʼʵ�ʵ�����
   connection.connect();
   // ��ʼ�� BufferedReader����������ȡURL����Ӧ
   in = new BufferedReader(new InputStreamReader(
     connection.getInputStream(), "utf-8"));
   // ������ʱ�洢ץȡ����ÿһ�е�����
   String line;
   while ((line = in.readLine()) != null) {
    // ����ץȡ����ÿһ�в�����洢��result����
    result += line;
   }
  } catch (Exception e) {
   System.out.println("����GET��������쳣��" + e);
   e.printStackTrace();
  }
  // ʹ��finally���ر�������
  finally {
   try {
    if (in != null) {
     in.close();
    }
   } catch (Exception e2) {
    e2.printStackTrace();
   }
  }
  return result;
 }

}
