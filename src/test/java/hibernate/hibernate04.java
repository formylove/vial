package hibernate;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.common.TimeManager;
import ink.moshuier.silken.entity.Comment;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.entity.User;
import ink.moshuier.silken.service.CommentService;
import ink.moshuier.silken.service.MusicService;
import ink.moshuier.silken.service.NoteService;
import ink.moshuier.silken.service.OpusService;

public class hibernate04 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[]{"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/applicationContext.xml"
						,"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/daoContext.xml"
				});
		NoteService ns = ctx.getBean("noteService",NoteService.class);
		OpusService os = ctx.getBean("opusService",OpusService.class);
		CommentService cs = ctx.getBean("commentService",CommentService.class);
		MusicService ms = ctx.getBean("musicService",MusicService.class);
//		n.setAuthor("��ʥ̾");
//		n.setTitle("��ʥ̾��ˮ�");
//		Opus opus = new Opus();
//		opus.setName("ˮ䰴���");
//		n.setOpus(opus);
//		ns.save(n);
//		n = ns.get(1);
//		n.getOpus().setAuthor_directior("ʩ����");;
		Note n = new Note();
		n.setId(2);
		n.setAuthor("Hachi");
		n.setTitle("�����������");
		n.setMusic(ms.get(13));
		n.setOpus(os.get(2));
		n.setCreate_date(TimeManager.getDate());
		n.setCreate_time(TimeManager.getTime());
		ns.update(n);
//		Opus o =new Opus();
//		o.setName("��ի־��");
//		os.save(o);
//		Genre g = new Genre();
//		g.setName("־��С˵");
//		g.setType("book");
//		n.setOpus(o);
//		o.getGenres().add(g);
//		Note n = ns.get(2);
//		Log.print("comment", n.getComments().get(0).getContent());
//		Music music = new Music();
//		music.setName("��");
//		n.setMusic(music);
//		Comment c = new Comment();
//		c.setContent("�����������Զ�ƪС˵��");
//		User u = new User();
//		u.setRegister_date(new Timestamp(System.currentTimeMillis()));
//		u.setToken(UUID.randomUUID().toString());
//		Log.print("timestamp", u.getRegister_date());
//		Log.print("token", u.getToken());
//		u.setNick_name("Hachi");
//		User u2 = new User();
//		u2.setNick_name("������");
//		c.setPublisher(u);
//		c.setTarget(u2);
//		c.setNote(n);
//		ns.update(n);
//		c.setId(n.getId());
//		c.setFloor(1);
//		c.setUnit(1);
//		cs.save(c);
//		o.setName("��ի־��");
//		ns.delete(n);
	}

}
