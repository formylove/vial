package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.gallery.State;

public interface StateDao extends BaseDao<State>{
	State get(int id);
}
