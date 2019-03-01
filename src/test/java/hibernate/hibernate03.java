package hibernate;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.service.NoteService;

public class hibernate03 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[]{"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/applicationContext.xml"
						,"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/daoContext.xml"
				});
		NoteService ns = ctx.getBean("noteService",NoteService.class);
//		Note n0 = ns.get(23);
//		Log.print("get", n0.getAuthor());
//		Note n = new Note();
//		n.setAuthor("��ѩ��");
//		n.setTitle("��¥��");
//		//save��persist ������ӵ����ݿ�
//		ns.persist(n);
//		�Ѿ�����
//		Log.print("id", n.getId());
//		Note n3 = ns.get(6);
//		ns.delete(7);
//		ns.remove(11);
//		Note n0 = ns.get(23);
//		n0.setRead_cnt(n0.getRead_cnt()+1);
//		n0.setFavor_cnt(n0.getFavor_cnt()+1);
//		ns.update(n0);
	}

}
