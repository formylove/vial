package ink.moshuier.silken.dao;


import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Record;
import ink.moshuier.silken.entity.gallery.Dynasty;

public interface DynastyDao extends BaseDao<Dynasty>{
	Dynasty get(int id);
}
