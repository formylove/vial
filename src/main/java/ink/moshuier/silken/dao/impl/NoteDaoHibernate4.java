package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.NoteDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Note;
@Repository("noteDaoHibernate4")
public class NoteDaoHibernate4 extends BaseDaoHibernate4<Note> implements NoteDao 
{	
	public NoteDaoHibernate4() {
	}

	@Override
	public Note get(int id) {
		return get(Note.class,id);
	}
	//�������˾Ͳ�����д��
}
