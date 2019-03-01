package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.CommentDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Comment;
@Repository("commentDaoHibernate4")
public class CommentDaoHibernate4 extends BaseDaoHibernate4<Comment> implements CommentDao 
{	
	public CommentDaoHibernate4() {
	}

	@Override
	public Comment get(int id) {
		return get(Comment.class,id);
	}
	//�������˾Ͳ�����д��
}
