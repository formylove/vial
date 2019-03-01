package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.OpusDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Opus;
@Repository("opusDaoHibernate4")
public class OpusDaoHibernate4 extends BaseDaoHibernate4<Opus> implements OpusDao 
{	
	public OpusDaoHibernate4() {
	}

	@Override
	public Opus get(int id) {
		return get(Opus.class,id);
	}
	//�������˾Ͳ�����д��
}
