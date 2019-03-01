package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Comment;

public interface CommentDao extends BaseDao<Comment>{
	Comment get(int id);
}
