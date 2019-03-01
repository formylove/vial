package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.RecordDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Record;
@Repository("recordDaoHibernate4")
public class RecordDaoHibernate4 extends BaseDaoHibernate4<Record> implements RecordDao 
{	
	public RecordDaoHibernate4() {
	}

	@Override
	public Record get(int id) {
		return get(Record.class,id);
	}
	//�������˾Ͳ�����д��
}
