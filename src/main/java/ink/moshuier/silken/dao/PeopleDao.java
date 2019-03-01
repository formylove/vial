package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.gallery.People;

public interface PeopleDao extends BaseDao<People>{
	People get(int id);
}
