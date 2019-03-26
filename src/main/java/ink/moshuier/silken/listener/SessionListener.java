package ink.moshuier.silken.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class SessionListener implements HttpSessionListener{
	public HttpServletRequest request;
	private static Logger log = LogManager.getLogger(SessionListener.class.getName());
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		HttpSession  session = (HttpSession)event.getSession();
		// TODO Auto-generated method stubN
		log.error("session created");
		System.out.println("session " + session.getId() + " created");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
		HttpSession  session = (HttpSession)event.getSession();
		log.error("session destroyed");
		System.out.println("session " + session.getId() + " detroyed");
	}

	
}
