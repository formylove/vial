package ink.moshuier.silken.common;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
/**
* �����������¼��֤
* 
* @author MZULE
* 
*/
public class MailAuthenticator extends Authenticator {

/**
* �û�������¼���䣩
*/
private String username;
/**
* ����
*/
private String password;

/**
* ��ʼ�����������
* 
* @param username ����
* @param password ����
*/
public MailAuthenticator(String username, String password) {
this.username = username;
this.password = password;
}

String getPassword() {
return password;
}

@Override
protected PasswordAuthentication getPasswordAuthentication() {
return new PasswordAuthentication(username, password);
}

String getUsername() {
return username;
}

public void setPassword(String password) {
this.password = password;
}

public void setUsername(String username) {
this.username = username;
}

}
