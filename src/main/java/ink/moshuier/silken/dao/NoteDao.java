package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Note;
public interface NoteDao extends BaseDao<Note>
{
	// ����ID����ʵ��
	Note get(int id);
}
