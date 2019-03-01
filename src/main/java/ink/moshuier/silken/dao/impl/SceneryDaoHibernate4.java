package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.SceneryDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.item.Scenery;
@Repository("sceneryDaoHibernate4")
public class SceneryDaoHibernate4 extends BaseDaoHibernate4<Scenery> implements SceneryDao 
{	
	public SceneryDaoHibernate4() {
	}

	@Override
	public Scenery get(int id) {
		return get(Scenery.class,id);
	}
	//�������˾Ͳ�����д��
}
