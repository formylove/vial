package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Poster;

public interface PosterDao extends BaseDao<Poster>{
	Poster get(int id);
}
