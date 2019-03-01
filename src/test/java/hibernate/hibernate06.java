package hibernate;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import ink.moshuier.silken.entity.essay.Essay;
import ink.moshuier.silken.service.CategoryService;
import ink.moshuier.silken.service.EssayService;
import ink.moshuier.silken.service.OpusService;
import ink.moshuier.silken.service.UserService;

public class hibernate06 {

	public static void main(String[] args) {
		System.out.println("OOO");
		Logger l = Logger.getLogger("loog");
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext(
				new String[]{"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/applicationContext.xml"
						,"C:/Users/Administrator/git/iBlog/WebContent/WEB-INF/daoContext.xml"
				});
		UserService us = ctx.getBean("userService",UserService.class);
		OpusService os = ctx.getBean("opusService",OpusService.class);
		CategoryService cs = ctx.getBean("categoryService",CategoryService.class);
		EssayService es = ctx.getBean("essayService",EssayService.class);
		Essay e = new Essay();
		e.setAuthor("�εĽ���");
		e.setAuthor("��������");
		e.setContent("ج��");
		ink.moshuier.silken.entity.Category c = new ink.moshuier.silken.entity.Category(5500);
		c = cs.get(1);
		c.setProfile("http://t12.baidu.com/it/u=4214312156,64162565&fm=58");
		e.setCategory(c);
		l.info("xxxxxxxxxxxxxxxxxxxxxxxx");
//		System.out.println(e.getCategory().isDel_flag());
		es.save(e);
//		UserDaoHibernate4 ud = ctx.getBean("userDaoHibernate4",UserDaoHibernate4.class);
//		Session s = ud.getSession();
//		User u = us.get(9);
//		Note n = ns.get(3);
//		Opus opus = new Opus();
//		opus.setId(2);
		//��ӹ���ֻ��д��Id�㹻��������all����
//		n.setOpus(opus);
//		System.out.println("OOxxO");
//		ns.update(n);
//		System.out.println("OOxxO2");
//		u.setId(1);
//		u.setNick_name("Aurora");
//		u.setEmail("907251709@qq.com");
//		u.setPassword("2222222222");
//		u.setToken(UUID.randomUUID().toString());
//		u.setMotto("free for life");
//		u.setPotrait("http://tp3.sinaimg.cn/2497336742/180/5740840181/0");
//		us.save(u);
	}

}
