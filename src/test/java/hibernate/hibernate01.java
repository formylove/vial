package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;
import utils.HibernateUtil;

public class hibernate01 {

	public static void main(String[] args) {
//		Session s = HibernateUtil.currentSession(1);
//		Transaction tx = s.beginTransaction();
//		Note n = new Note();
//		n.setAuthor("�޹���");
//		n.setTitle("��������");
//		��¼�޸�		
//		s.save(n);
//		n = (Note) s.load(Note.class,5);
//		n.setAuthor_desc("ming");
//		Log.print("�����������س־û�ʵ��", n2.getAuthor());
//		n2 = n;
		//ֻ�Ͷ�Ӧָ��ļ�¼�йأ�����������޹�
//		n2.setAuthor_link("www");
//		s.flush();//���ú�commit��ͬ
//		�ύ�޸�
//		tx.commit();
//		HibernateUtil.closeSession();
		Session s2 = HibernateUtil.currentSession(1);
		Note n2 = (Note) s2.load(Note.class, 12);
		Transaction tx = s2.beginTransaction();
		n2.setAuthority(222);
		n2.setDel_flag(true);
		s2.update(n2);
//		s2.delete(n2);
		tx.commit();
		
	}

}
