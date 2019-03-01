package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import ink.moshuier.silken.entity.Genre;
import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.service.GenreService;
import ink.moshuier.silken.service.OpusService;
import utils.HibernateUtil;

public class hibernate08 {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[]{"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/applicationContext.xml"
						,"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/daoContext.xml"
				});
		Session s = HibernateUtil.currentSession(1);
		Transaction t = s.getTransaction();
		OpusService os = ctx.getBean("opusService",OpusService.class);
		GenreService gs = ctx.getBean("genreService",GenreService.class);
		Opus o = new Opus();
		o.setName("��¥��xsxc");
		Genre g = gs.get(3);
		o.getGenres().add(g);
		s.save(o);
		t.commit();
	}

}
