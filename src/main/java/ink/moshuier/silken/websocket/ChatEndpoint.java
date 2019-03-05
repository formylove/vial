package ink.moshuier.silken.websocket;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import ink.moshuier.silken.common.StrUtils;
import ink.moshuier.silken.common.TimeManager;
import ink.moshuier.silken.entity.User;
import ink.moshuier.silken.service.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@ServerEndpoint(value = "/websocket/chat",configurator=GetHttpSessionConfigurator.class)
public class ChatEndpoint {
	private static final Map<Integer,CopyOnWriteArrayList<ChatEndpoint>> clientMap = new ConcurrentHashMap<Integer,CopyOnWriteArrayList<ChatEndpoint>>();
	private static final CopyOnWriteArrayList<ChatEndpoint> administrator = new CopyOnWriteArrayList<ChatEndpoint>();
	private static final CopyOnWriteArrayList<ChatEndpoint> visitors = new CopyOnWriteArrayList<ChatEndpoint>();
	private  User user;
	private Session session;
	static private UserService userService;
	static ApplicationContext ctx;
	private static final String GUEST_PREFIX = "�ÿ�";
	private static final String ADMIN_LOGIN = "admin_login";
	private static final String ADMIN_LOGIN_REMIND = "admin_login_remind";
	private static final String USER_LOGIN = "user_login";
	private static final String USER_LOGIN_REMIND = "user_login_remind";
	private static final String PROPOSAL_LOGIN = "proposal_login";
	private static final String ADMIN_LOGOUT_REMIND = "admin_logout_remind";
	private static final String ADMIN_LOGOUT = "admin_logout";
	private static final String USER_LOGOUT_REMIND = "user_logout_remind";
	private static final String OBJECT_LOGOUT = "user_logout";
	private static final String USER = "user";
	private static final String ADMIN = "admin";
	private static final String PUBLISHER = "publisher";
	private static final String TARGET_ID = "target_id";
	private static final String CONTENT = "content";
	private static final String TEXT = "text";
	private static final String LOGIN = "login";
	private static final String LOGINOUT = "loginout";
	static{
		ctx = ContextLoader.getCurrentWebApplicationContext();  
		userService = (UserService) ctx.getBean("userService");  
		
	}
	public ChatEndpoint()
	{	
	}
	// ���ͻ������ӽ���ʱ�Զ������÷���
		@OnOpen
		public void start(Session session,EndpointConfig config)
		{	
			this.session = session;
			String sessionId = session.getId();
			user =userService.getcurLoginUser(config);
			JSONObject jMsg = new JSONObject();
			JSONObject jMsg_Self = new JSONObject();
			jMsg_Self.accumulate("sessionId", sessionId);
			System.out.println((user!=null?user.getId():"000")+"_______" + session.getId());
			try {
			if(user != null){
				login();
				}else{
					visitors.add(this);//���ӵ�δ��¼Session
					jMsg_Self.accumulate("type", PROPOSAL_LOGIN);
					jMsg_Self.accumulate("hasAdmin", !administrator.isEmpty());
					session.getBasicRemote().sendText(jMsg_Self.toString());
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ���ͻ��˶Ͽ�����ʱ�Զ������÷���

		// ÿ���յ��ͻ�����Ϣʱ�Զ������÷���
		@OnMessage
		public void incoming(String message,Session  config)
		{
			JSONObject jMsg =  JSONObject.fromObject(message);
			jMsg.accumulate("time", TimeManager.getNowNoYear());
			try {
			if(TEXT.equalsIgnoreCase(jMsg.get("type").toString())){
				if(user != null){
					jMsg.accumulate("userId", user.getId());
					jMsg.accumulate("gender", String.valueOf(user.getGender()));
					jMsg.accumulate("nickName", user.getNick_name());
					
					
				int userId = (jMsg.get("target") != null)?Integer.parseInt(jMsg.get("target").toString()):-1;
				if(isOnline(userId)){//�Է�����
					sendTo(userId, jMsg.toString());
				}else{
					jMsg.accumulate("content", "�Է�������,��Ϣ����������ʽ����");
					sendMe(jMsg.toString());
				}
			}else{//δ��¼�����
				jMsg.remove("type");
				jMsg.accumulate("type", PROPOSAL_LOGIN);//���Ǹ���,���Ǳ������---type: ["text", "proposal_login"]
				jMsg.accumulate("hasAdmin", !administrator.isEmpty());
				this.session.getBasicRemote().sendText(jMsg.toString());
			}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		@OnClose
		public void end(CloseReason reason)
		{
			try {
				Thread.sleep(10000);//�ȴ�һ��,������û�����µ�¼
				JSONObject jMsg = new JSONObject();
			if(this.user != null){
				int userId = user.getId();
				jMsg.accumulate("time", TimeManager.getNowNoYear());
				jMsg.accumulate("userId", user.getId());
				jMsg.accumulate("gender", String.valueOf(user.getGender()));
				jMsg.accumulate("nickName", user.getNick_name());
				if(isAdmin()){
					administrator.remove(this);
					//��������,Ⱥ��֪ͨ
					if(administrator.isEmpty()){
						jMsg.accumulate("type", ADMIN_LOGOUT_REMIND);
						broadcastAll(jMsg.toString(),null);
					}
				}else{
					clientMap.get(userId).remove(this);//ɾ��session��¼
					if(clientMap.get(userId).isEmpty()){
						clientMap.remove(userId);//ɾ���û���¼
						jMsg.accumulate("type", USER_LOGOUT_REMIND);
						informAdmin(jMsg.toString());
					}
				}
			}else{
				visitors.remove(this);
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ���ͻ���ͨ�ų��ִ���ʱ�������÷���
		@OnError
		public void onError(Throwable t) throws Throwable
		{
			System.out.println("Throwable:"+t);
		}
public JSONArray listAllOnline(){
	JSONArray jUsers = new JSONArray();
	for(Integer id:clientMap.keySet()){
			User user = clientMap.get(id).get(0).user;
			JSONObject jClient = new JSONObject();
			jClient.accumulate("id", id);
			jClient.accumulate("nickName", user.getNick_name());
			jUsers.add(jClient);
	}
	return jUsers;
}


public void login()
{
	JSONObject jMsg = new JSONObject();
	JSONObject jMsg_Self = new JSONObject();
	try{
		jMsg.accumulate("time", TimeManager.getNowNoYear());
		jMsg.accumulate("userId", user.getId());
		jMsg.accumulate("nickName", user.getNick_name());
		jMsg.accumulate("gender", String.valueOf(user.getGender()));
		jMsg_Self.accumulate("time", TimeManager.getNowNoYear());
		jMsg_Self.accumulate("sessionId", this.session.getId());//ͬһ���߳���sessionͬʱֻ֧��һ��ͨ��
		
		//վ��
		if(isAdmin()){
			if(administrator.isEmpty()){//�״ε�½
			//֪ͨ�����û�
			jMsg.accumulate("type", ADMIN_LOGIN_REMIND);
			broadcastAll(jMsg.toString(),user);
			}
			administrator.add(this);
			//֪ͨ�Լ�
			jMsg_Self.accumulate("type", ADMIN_LOGIN);
			this.session.getBasicRemote().sendText(jMsg_Self.toString());
		}
		//��ͨ�û�
		else{
			//�״ε�½
			if(clientMap.get(user.getId()) == null){
				CopyOnWriteArrayList<ChatEndpoint> epoint = new CopyOnWriteArrayList<ChatEndpoint>();
				epoint.add(this);
				clientMap.put(user.getId(), epoint);
				//֪ͨվ��
				jMsg.accumulate("type", USER_LOGIN_REMIND);
				informAdmin(jMsg.toString());
			}else{
				clientMap.get(user.getId()).add(this);//��Ӿͺ�
			}
			if(visitors.contains(this)){//˵����;��¼
				visitors.remove(this);
			}else{//ˢ�µ�¼,��֪�Լ�״̬,��ʼ������Ա��������Ϣ
				jMsg_Self.accumulate("type", USER_LOGIN);
				jMsg_Self.accumulate("hasAdmin", !administrator.isEmpty());
				if(!administrator.isEmpty()){
					jMsg_Self.accumulate("adminId", administrator.get(0).user.getId());
				}
				this.session.getBasicRemote().sendText(jMsg_Self.toString());
			}
		}
		
	}
	catch (Exception e)
	{
		
	}
}
public static void activateSession(String sessionId,User user)
{	
	for(ChatEndpoint ePoint : visitors){
		if(ePoint.session.getId().equals(sessionId)){
			ePoint.user = user;
			//ɾ��δ��¼�ĵǼ�����
			if(visitors.contains(ePoint)){
				visitors.remove(ePoint);
			}
			//��ʼ����Ϣ
			ePoint.login();
			break;
		}
	}
}
public static void appendSession(User curUser)
{	if(curUser != null){
	int userId = curUser.getId();
	JSONObject jMsg = new JSONObject();
	jMsg.accumulate("time", TimeManager.getNowNoYear());
	jMsg.accumulate("userId", userId);
	jMsg.accumulate("gender", String.valueOf(curUser.getGender()));
	jMsg.accumulate("nickName", curUser.getNick_name());
	JSONObject jMsg_Self = new JSONObject();
	jMsg_Self.accumulate("time", TimeManager.getNowNoYear());
	if(!administrator.isEmpty() || administrator.get(0).user.getId() == userId){
		//֪ͨ�Լ�����ҳ��
		jMsg_Self.accumulate("type", ADMIN_LOGOUT);
		sendTo(userId, jMsg_Self.toString());
		jMsg.accumulate("type", ADMIN_LOGOUT_REMIND);
		broadcastAll(jMsg.toString(),null);
		visitors.addAll(administrator);
		administrator.clear();
	}else if(clientMap.containsKey(userId)){
		visitors.addAll(clientMap.get(userId));
		clientMap.remove(userId);
		jMsg.accumulate("type", USER_LOGOUT_REMIND);
		informAdmin(jMsg.toString());
	}
}
}
//ʵ�ֹ㲥��Ϣ�Ĺ��߷���
private static void broadcastAll(String msg,User self)
{
	broadcastOnline(msg,self);
	broadcastUnlogined(msg);
}
private static void broadcastOnline(String msg,User self)
{
	try{
		int excludeId = self!=null ? self.getId():0;
		// �������������������пͻ���
		for (Integer clientID : clientMap.keySet())
		{
			if(clientID != excludeId){
				for (Iterator iterator = clientMap.get(clientID).iterator(); iterator.hasNext();) {
					ChatEndpoint chatEndpoint = (ChatEndpoint) iterator.next();
					chatEndpoint.session.getBasicRemote().sendText(msg);
				}
			}
		}
	}
	catch (IOException e)
	{
		
	}
}
private static void broadcastUnlogined(String msg)
{
	try{
		// �������������������пͻ���
		for (ChatEndpoint ePoint : visitors)
		{
			ePoint.session.getBasicRemote().sendText(msg);
		}
	}
	catch (IOException e)
	{
		
	}
}
private static void informAdmin(String msg)
{
	if(!administrator.isEmpty()){
		sendTo(administrator.get(0).user.getId(),msg);
	}
}

private boolean isAdmin(){
	return user.getAuthority() == 0;
}
private boolean isOnline(int userId){
	if(clientMap.containsKey(userId) || (administrator.get(0) !=null && administrator.get(0).user.getId() == userId)){
		return true;
	}
	return false;
}
public static void sendTo(Integer userId,String message){
	CopyOnWriteArrayList<ChatEndpoint> obj = null;
	if(clientMap.containsKey(userId)){
		obj = clientMap.get(userId);
	}else{
		obj = administrator;
	}
	for(ChatEndpoint c:obj){
		try {
			c.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
public void sendMe(String message){
	CopyOnWriteArrayList<ChatEndpoint> obj = null;
	if(clientMap.containsKey(user.getId())){
		obj = clientMap.get(user.getId());
	}else{
		obj = administrator;
	}
	for(ChatEndpoint c:obj){
		try {
			c.session.getBasicRemote().sendText(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





}
