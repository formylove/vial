package ink.moshuier.silken.common;

import java.util.Properties;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class MailUtils {
	static private String MyMail = "ansyx@163.com"; 
	static private String PASSWORD="a54417523";
	static private final String SUBJECT="ҹ���ʺż���";
	static private final String Salutation_Reg="(?<=id=\"salutation\">)[^<]+(?=</p>)";
	static private final String Href_Reg = "(?<=href=\")[^\"]*(?=\")";
	static public String baseRealPath = ((HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST)).getRealPath("/");
	static public String templatePath = baseRealPath + MessageUtils.getMessageFromUrl("template.email");
	static public String base = MessageUtils.getMessageFromUrl("base");
    /**
     * �����ʼ���props�ļ�
     */
    private final transient Properties props = System.getProperties();
    /**
     * �ʼ���������¼��֤
     */
    private transient MailAuthenticator authenticator;
 
    /**
     * ����session
     */
    private transient Session session;
    public MailUtils(String Sender,String password){
    	this.MyMail = Sender;
    	this.PASSWORD = password;
    }
    public MailUtils(){
    }
	public String sendEmail(String recipient,String subject,String content){
		init();
		 // ����mime�����ʼ�
	    final MimeMessage message = new MimeMessage(session);
	    // ���÷�����
	    try {
	    message.setFrom(new InternetAddress(authenticator.getUsername()));
	    // �����ռ���
	    message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
	    // ��������
	    message.setSubject(subject);
	    // �����ʼ�����
	    message.setContent(content.toString(), "text/html;charset=utf-8");
	    // ����
			Transport.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String sendActivateEmail(String recipient,String nick_name,String token){
		String content = StrUtils.fileToString(templatePath+"activateMail.html");
		content = content.replaceAll(Salutation_Reg, nick_name+", ��л��ע��ҹ��");
		content = content.replaceAll(Href_Reg, base + "activate/" + token +"/");
		sendEmail(recipient, SUBJECT, content);
		return null;
	}
	 private void init() {
		 final String smtpHostName = "smtp." + MyMail.split("@")[1];
	    // ��ʼ��props
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.host", smtpHostName);
	    // ��֤
	    authenticator = new MailAuthenticator(MyMail, PASSWORD);
	    // ����session
	    session = Session.getInstance(props, authenticator);
	    }
	 
}
