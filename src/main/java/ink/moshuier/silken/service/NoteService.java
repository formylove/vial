package ink.moshuier.silken.service;


import ink.moshuier.silken.entity.Note;
import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.service.base.BaseService;

public interface NoteService extends BaseService<Note>{

	void change(Note note,Opus opus);

	void read(int id);

	boolean hasExisted(String title);

	void like(int id);
	
	Note get(String title);

	void undoLike(int id);

}
