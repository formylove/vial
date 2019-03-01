package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.gallery.City;

public interface CityDao extends BaseDao<City>{
	City get(int id);
}
