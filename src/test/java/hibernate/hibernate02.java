package hibernate;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.service.NoteService;

public class hibernate02 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[]{"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/applicationContext.xml"
						,"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/daoContext.xml"
				});
		NoteService ns = ctx.getBean("noteService",NoteService.class);
		Note n = ns.get(3);
		Log.print("??????????????????", n.getAuthor());
		n = ns.get(3);
		Log.print("??????????????????", n.getAuthor());
	}

}
