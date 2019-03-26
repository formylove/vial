package ink.moshuier.silken.common;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import ink.moshuier.silken.action.LoginAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class MailUtils {
	static private String MyMail = MessageUtils.getConfig("mail.addr");
	static private String SiteName = MessageUtils.getConfig("site.name");
	static private String MailLogo = MessageUtils.getMessageFromUrl("base") + MessageUtils.getMessageFromUrl("mail.logo");
	static private String PASSWORD = MessageUtils.getConfig("mail.password");
	static private final String SUBJECT = MessageUtils.getConfig("mail.title");
	static private final String Salutation_Reg="(?<=id=\"salutation\">)[^<]+(?=</p>)";
	static private final String Href_Reg = "(?<=href=\")[^\"]*(?=\")";
	static public String baseRealPath = ((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).getRealPath("/");
	static public String template = baseRealPath +  MessageUtils.getMessageFromUrl("template.email") + "activateMail.html";
	static public String base = MessageUtils.getMessageFromUrl("base");
	private static Logger logger = LogManager.getLogger(MailUtils.class.getName());
	/**
	 * 发送邮件的props文件
	 */
	private final transient Properties props = System.getProperties();
	/**
	 * 邮件服务器登录验证
	 */
	private transient MailAuthenticator authenticator;

	/**
	 * 邮箱session
	 */
	private transient Session session;
	public MailUtils(String Sender,String password){
		this.MyMail = Sender;
		logger.error(MyMail);
		this.PASSWORD = password;
	}
	public MailUtils(){
	}
	public String sendActivateEmail(String recipient,String nick_name,String token){
		String content = StrUtils.fileToString(template);
		content = content.replaceAll("\\{nickname\\}", nick_name);
		content = content.replaceAll("\\{mail\\.logo\\}", MailLogo);
		content = content.replaceAll(Href_Reg, base + "activate/" + token +"/");
		content = content.replaceAll("\\{sitename\\}", SiteName);
		content = content.replaceAll("\\{year\\}", TimeManager.getCurrentYear());
//		try {
//			sendEmail(recipient, new String(SUBJECT.getBytes("GBK"),"GBK"), content);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		sendEmail(recipient,SUBJECT, content);
		return null;
	}
	public String sendEmail(String recipient,String subject,String content){
		init();
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		try {
			message.setFrom(new InternetAddress(authenticator.getUsername()));
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
			// 设置主题
			message.setSubject(subject);
			// 设置邮件内容
			message.setContent(content.toString(), "text/html;charset=utf-8");
		logger.error("set 发送前");
			// 发送
			Transport.send(message);
		logger.error("set 发送后 " );
		logger.error("Transport.send" + message);
		} catch (MessagingException e) {
		logger.error(e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void init() {
		final String smtpHostName = "smtp." + MyMail.split("@")[1];
		// 初始化props
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", smtpHostName);
        final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.port", "465");
        props.put("mail.transport.protocol","smtp");
        props.put("mail.smtp.starttls.enable", "true");// 使用 STARTTLS安全连接

        // 验证
		authenticator = new MailAuthenticator(MyMail, PASSWORD);
		// 创建session
		session = Session.getInstance(props, authenticator);
	}
public static void main(String[] s){
	try {
	    System.out.println(baseRealPath);
		System.out.println(new String("卧槽".getBytes("UTF-8"),"UTF-8"));
		System.out.println("卧槽");
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
	}
	System.out.println("sun.jnu.encoding:"+System.getProperty("sun.jnu.encoding"));
	System.out.println(Charset.defaultCharset());

	System.out.print(System.getProperty("file.encoding"));


	(new MailUtils()).sendActivateEmail("satan199@163.com","太上老君","111");



}
}
