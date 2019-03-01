package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.DynastyDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.Dynasty;
@Repository("dynastyDaoHibernate4")
public class DynastyDaoHibernate4 extends BaseDaoHibernate4<Dynasty> implements DynastyDao 
{	
	public DynastyDaoHibernate4() {
	}

	@Override
	public Dynasty get(int id) {
		return get(Dynasty.class,id);
	}
	//�������˾Ͳ�����д��
}
