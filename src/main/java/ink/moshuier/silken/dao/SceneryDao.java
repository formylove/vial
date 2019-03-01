package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.gallery.item.Scenery;

public interface SceneryDao extends BaseDao<Scenery>{
	Scenery get(int id);
}
