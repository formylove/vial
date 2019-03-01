package ink.moshuier.silken.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

import com.opensymphony.xwork2.ActionContext;

import ink.moshuier.silken.common.SqlUtils;
import ink.moshuier.silken.common.StrUtils;
import ink.moshuier.silken.common.WebUtils;
import ink.moshuier.silken.entity.User;

public class zUserService2 {
	
	static public String getUserName(int id){
		return getUser(id).getNick_name();
	}
	
	static public User getUser(int id){
		String sql="select * from user where id="+id;
		@SuppressWarnings("unchecked")
		User user=((List<User>)SqlUtils.executeQuery(sql, null, User.class)).get(0);
		return user;
	}
	@SuppressWarnings("unchecked")
	static public List<User> getUsers(String condition){
		String sql="select * from user where "+condition;
		return (List<User>) SqlUtils.executeQuery(sql, null, User.class);
	}
	static public boolean hasRegistered(String email){
		List<User> users = getUsers("email='"+email+"'");
		if(users.size()>=1)
		{
			return true;
		}else{
			return false;
		}
	}
	static public boolean hasUsed(String nick_name){
		List<User> users = getUsers("nick_name='"+nick_name+"'");
		if(users.size()>=1)
		{
			return true;
		}else{
			return false;
		}
	}
	static public String loginDetect(String email,String password){
		email = email.trim();
		String message = null;
		if(!StrUtils.valiEmail(email)){
			return "�����ʽ����";
		}
		if(!StrUtils.simpleChar(password)){
			return "�������";
		}
		if(getUserByMail(email) == null){
			return "����δע��";
		}
		User user = getUserByMail(email);
		if(!user.isEmail_val_flag()){
			return "����δ����";
		}
		if(password == null || !password.equals(user.getPassword())){
			return "�������";
		}
		return null;
	}
	static public String registerDetect(String nick_name,String email,String password,String psw_conf,String rule){
		email = email.trim();
		String message = null;
		if(!StrUtils.valiName(nick_name)){
			return "�ǳƱ���ֻ����Ӣ�����������Լ��»���";
		}
		if(!StrUtils.valiEmail(email)){
			return "�����ʽ����";
		}
		if(getUserByMail(email) != null){
			return "������ע��";
		}
		if(!StrUtils.simpleChar(password)){
			return "����ֻ�ܰ������ּ���ĸ";
		}
		if(StrUtils.isEmpty(password) || password.length()<8 || password.length()>16){
			return "���볤�ȱ�����8��16λ֮��";
		}
		if(!password.equals(psw_conf)){
			return "�����������벻ͬ";
		}
		if(StrUtils.isEmpty(rule)){
			return "�����Ķ���ͬ��ҹ��ʹ��Э��";
		}
		return null;
	}
	static public User valiAndLogin(String email,String password,String remember){
		List<User> users = getUsers("email='" + email + "'");
		if(users.size()>=1)
		{
			User user = users.get(0);
			if(password.equals(user.getPassword())){
				login(user,remember);
				return user;
			}
		}
			return null;
	}
	static public boolean login(User user,String remember){
		if(remember == null){
			ActionContext.getContext().getSession().put("logined_user", user);
		}else{
			HttpServletResponse res = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			Cookie c = new Cookie("night_user_id",String.valueOf(user.getId()));
			c.setMaxAge(7*24*60*60);
			res.addCookie(c);
		}
		return true;
	}
	static public User login(String email,String remember){
		User user = getUserByMail(email);
		login(user, remember);
		return user;
	}
	static public User getUserByMail(String email){
		email = email.trim();
		return getUser("email='"+email+"'");
	}
	static public User getUserByToken(String token){
		token = token.trim();
		return getUser("token='"+token+"'");
	}
	static public boolean isExpired(String token){
		token = token.trim();
		if(getDurationForRegister(token)>=24){//����24�Ѿ�����
			return false;
		}else{
			
			return false;
		}
	}
	static public int getDurationForRegister(String token){
		token = token.trim();
		String sql = "SELECT TIMESTAMPDIFF(HOUR,register_date,NOW()) FROM `user` WHERE token = ?";
		return SqlUtils.executeQueryForCount(sql, new Object[]{token});
	}
	static public User getUser(String condition){
		List<User> users = getUsers(condition);
		if(users.size()>0){
			return users.get(0);
		}else{
			return null;
		}
	}
	static public boolean logout(){
			ActionContext.getContext().getSession().put("logined_user", null);
			HttpServletResponse res = (HttpServletResponse) ActionContext.getContext().get(StrutsStatics.HTTP_RESPONSE);
			Cookie c = new Cookie("night_user_id","");
			c.setMaxAge(0);
			res.addCookie(c);
		return true;
	}
	
	static public User getcurLoginUser(HttpServletRequest request){
		User user = null;
		if(request!=null){
			user = (User)request.getSession().getAttribute("logined_user");
		}else{
			user = (User)ActionContext.getContext().getSession().get("logined_user");
		}
		if(user !=null){
			return user;
		}
		String user_id = WebUtils.getCookie("night_user_id",request);
		if(user_id != null){
			return getUser(Integer.parseInt(user_id));
		}
			return null;
	}
}
