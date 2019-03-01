package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.StateDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.State;
@Repository("stateDaoHibernate4")
public class StateDaoHibernate4 extends BaseDaoHibernate4<State> implements StateDao 
{	
	public StateDaoHibernate4() {
	}

	@Override
	public State get(int id) {
		return get(State.class,id);
	}
	//�������˾Ͳ�����д��
}
