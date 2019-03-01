package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Music;

public interface MusicDao extends BaseDao<Music>{
	Music get(int id);
}
