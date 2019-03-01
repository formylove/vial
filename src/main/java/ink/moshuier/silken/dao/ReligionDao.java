package ink.moshuier.silken.dao;


import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.gallery.Religion;

public interface ReligionDao extends BaseDao<Religion>{
	Religion get(int id);
}
