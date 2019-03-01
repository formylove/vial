package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.EssayDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.essay.Essay;
@Repository("essayDaoHibernate4")
public class EssayDaoHibernate4 extends BaseDaoHibernate4<Essay> implements EssayDao 
{	
	public EssayDaoHibernate4() {
	}

	@Override
	public Essay get(int id) {
		return get(Essay.class,id);
	}
	//�������˾Ͳ�����д��
}
