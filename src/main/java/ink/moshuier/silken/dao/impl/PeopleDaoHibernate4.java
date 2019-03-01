package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.PeopleDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.People;
@Repository("peopleDaoHibernate4")
public class PeopleDaoHibernate4 extends BaseDaoHibernate4<People> implements PeopleDao 
{	
	public PeopleDaoHibernate4() {
	}

	@Override
	public People get(int id) {
		return get(People.class,id);
	}
	//�������˾Ͳ�����д��
}
