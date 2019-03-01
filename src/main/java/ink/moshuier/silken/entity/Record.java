package ink.moshuier.silken.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;

import ink.moshuier.silken.common.MessageUtils;
import ink.moshuier.silken.common.Spider;
import ink.moshuier.silken.common.StrUtils;
import ink.moshuier.silken.common.WebUtils;
import net.sf.json.JSONObject;
@Component("record")
@Entity
@Table(name = "record")
public class Record {
	@Id @Column(name="record_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String qq;
	private String ip;
	private String os;
	private String browser;
	private String device;
	private String province;
	private String city;
	private String district;
	private String provider;
	private String user_agent;
	private String module;
	private String content;
	private String url;
	private String session_id;
//	@Column(columnDefinition = "CURRENT_TIMESTAMP" )
	private Timestamp time;
	public Record(){
		
	}
	public Record(HttpServletRequest request){
		HttpSession session = request.getSession(true);//��û�д����򴴽�һ��session
		//����meta��Ϣ
		setIp(request.getRemoteAddr());
		setUrl(request.getRequestURI());
		setUser_agent(request.getHeader("user-agent"));
		setSession_id(session.getId());
		//����λ����Ϣ
		setProvider((String)session.getAttribute("provider"));
		setProvince((String)session.getAttribute("province"));
		setCity((String)session.getAttribute("city"));
		setDistrict((String)session.getAttribute("district"));
		//�����豸��Ϣ
		setBrowser((String)session.getAttribute("browser"));
		setDevice((String)session.getAttribute("device"));
		setOs((String)session.getAttribute("os"));
		//��ȡλ����Ϣ
		if(StrUtils.isEmpty(province) && StrUtils.isEmpty(city) && StrUtils.isEmpty(district) && StrUtils.isEmpty(provider)){
			//ToDo replace Ip here
			String location = Spider.SendGet(MessageUtils.getMessageFromUrl("api.location") + "122.95.231.214");
			if(StrUtils.notEmpty(location)){
				JSONObject js = JSONObject.fromObject(location);
				if(js != null){
					JSONObject data = js.getJSONObject("data");
					if(data != null){
						setProvince(data.getString("province"));
						setCity(data.getString("city"));
						setDistrict(data.getString("district"));
						setProvider(data.getString("linetype"));
					}
				}
			}
			session.setAttribute("province", getProvince());
			session.setAttribute("city", getCity());
			session.setAttribute("district", getDistrict());
			session.setAttribute("provider", getProvider());
		}
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSession_id() {
		return session_id;
	}

	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getUser_agent() {
		return user_agent;
	}
	public void setUser_agent(String user_agent) {
		this.user_agent = user_agent;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}

}
