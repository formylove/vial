package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.ReligionDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.Religion;
@Repository("religionDaoHibernate4")
public class ReligionDaoHibernate4 extends BaseDaoHibernate4<Religion> implements ReligionDao 
{	
	public ReligionDaoHibernate4() {
	}

	@Override
	public Religion get(int id) {
		return get(Religion.class,id);
	}
	//�������˾Ͳ�����д��
}
