package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.NationDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.Nation;
@Repository("nationDaoHibernate4")
public class NationDaoHibernate4 extends BaseDaoHibernate4<Nation> implements NationDao 
{	
	public NationDaoHibernate4() {
	}

	@Override
	public Nation get(int id) {
		return get(Nation.class,id);
	}
	//�������˾Ͳ�����д��
}
