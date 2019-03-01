package spring;

import java.util.Date;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.service.NoteService;

public class Spring01 {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
//ApplicationContext cxt = new FileSystemXmlApplicationContext("C:/Users/Administrator/git/iBlog/src/test/beans.xml");
ApplicationContext cxt = new ClassPathXmlApplicationContext("beans01.xml");
NoteService ns = cxt.getBean("noteService",NoteService.class);
//Note note = ns.getNote(0);
Opus opus = cxt.getBean("@xia",Opus.class);
//����ע��
//Log.print("����ע��",note.title);
//depend on,�����ڹ��캯��֮��ע��
//Log.print("����ע��",note.getOpus().getName());
//����ʹ��
Log.print("����",opus.name);
//indexʹ��
//System.out.println("index:" + opus.original_name);
//�Զ�װ��
//System.out.println("byName:" + note.comments.get(0).user_name);
//System.out.println("��С:" + note.comments.size());
NoteService ns2 = cxt.getBean("noteService",NoteService.class);
//����ģʽ
Log.print("�Ƿ���ͬһʵ��", ns == ns2);
//���ʻ�
Log.print("Ĭ���������", cxt.getMessage("common.login", null, Locale.getDefault()));
Log.print("Ӣ���в������",cxt.getMessage("common.join.date", new Object[]{new Date()}, new Locale("en", "US")));
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	}

}
