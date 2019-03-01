package ink.moshuier.silken.dao;


import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Record;

public interface RecordDao extends BaseDao<Record>{
	Record get(int id);
}
