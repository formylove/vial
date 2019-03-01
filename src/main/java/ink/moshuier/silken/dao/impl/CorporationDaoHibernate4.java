package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.CorporationDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.Corporation;
@Repository("corporationDaoHibernate4")
public class CorporationDaoHibernate4 extends BaseDaoHibernate4<Corporation> implements CorporationDao 
{	
	public CorporationDaoHibernate4() {
	}

	@Override
	public Corporation get(int id) {
		return get(Corporation.class,id);
	}
	//�������˾Ͳ�����д��
}
