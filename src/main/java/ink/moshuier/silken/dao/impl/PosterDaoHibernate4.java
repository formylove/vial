package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.PosterDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Poster;
@Repository("posterDaoHibernate4")
public class PosterDaoHibernate4 extends BaseDaoHibernate4<Poster> implements PosterDao 
{	
	public PosterDaoHibernate4() {
	}

	@Override
	public Poster get(int id) {
		return get(Poster.class,id);
	}
	//�������˾Ͳ�����д��
}
