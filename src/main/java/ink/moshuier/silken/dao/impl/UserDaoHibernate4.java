package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.UserDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.User;
@Repository("userDaoHibernate4")
public class UserDaoHibernate4 extends BaseDaoHibernate4<User> implements UserDao 
{	
	public UserDaoHibernate4() {
	}

	@Override
	public User get(int id) {
		return get(User.class,id);
	}
	//�������˾Ͳ�����д��
}
