package spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.service.NoteService;
 
public class Spring02 {

	@SuppressWarnings("resource")
public static void main(String[] args) {
ApplicationContext cxt = new ClassPathXmlApplicationContext("beans02.xml");
NoteService ns = cxt.getBean("noteService",NoteService.class);
//Note note = ns.getNote(0);
//Ƕ��bean
//Log.print("Ƕ��bean",note.opus.getName());
//��������
//Log.print("list/bean��������",note.comments.get(0).user_name);
//Log.print("properties��������",note.elses.size());
//Log.print("properties��������",note.elses.getProperty("climate"));
//Log.print("Map��������", note.nations.get("cn"));
	
	
	
	
	
	
	
	
	
	
	}

}
