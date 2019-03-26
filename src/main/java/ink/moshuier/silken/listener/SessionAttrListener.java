package ink.moshuier.silken.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
@WebListener
public class SessionAttrListener implements HttpSessionAttributeListener{
	private static Logger log = LogManager.getLogger(SessionListener.class.getName());
	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		log.error("session add#" + event.getName() + " : " + event.getValue());
		System.out.println("session add#" + event.getName() + " : " + event.getValue());
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		log.error("session remove#" + event.getName() + " : " + event.getValue());
		System.out.println("session remove#" + event.getName() + " : " + event.getValue());
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		log.error("session change#" + event.getName() + " : " + event.getValue());
		System.out.println("session change#" + event.getName() + " : " + event.getValue());
	}
}
