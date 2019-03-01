package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.gallery.Corporation;

public interface CorporationDao extends BaseDao<Corporation>{
	Corporation get(int id);
}
