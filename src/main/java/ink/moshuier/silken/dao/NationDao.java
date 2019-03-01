package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.gallery.Nation;

public interface NationDao extends BaseDao<Nation>{
	Nation get(int id);
}
