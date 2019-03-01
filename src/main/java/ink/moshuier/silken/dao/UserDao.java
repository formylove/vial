package ink.moshuier.silken.dao;

import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.User;

public interface UserDao extends BaseDao<User>{
	User get(int id);
}
