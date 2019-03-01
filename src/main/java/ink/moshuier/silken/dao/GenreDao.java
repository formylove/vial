package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Genre;

public interface GenreDao extends BaseDao<Genre>{
	Genre get(int id);
}
