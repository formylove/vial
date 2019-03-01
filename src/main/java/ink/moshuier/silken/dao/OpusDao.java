package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.entity.Opus;

public interface OpusDao extends BaseDao<Opus>{
	Opus get(int id);
}
