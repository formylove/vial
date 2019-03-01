package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.essay.Essay;

public interface EssayDao extends BaseDao<Essay>{
	Essay get(int id);
}
