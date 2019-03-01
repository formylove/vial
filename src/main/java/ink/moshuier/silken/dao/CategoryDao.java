package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Category;

public interface CategoryDao extends BaseDao<Category>{
	Category get(int id);
}
