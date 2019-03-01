package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.GenreDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Genre;
@Repository("genreDaoHibernate4")
public class GenreDaoHibernate4 extends BaseDaoHibernate4<Genre> implements GenreDao 
{	
	public GenreDaoHibernate4() {
	}

	@Override
	public Genre get(int id) {
		return get(Genre.class,id);
	}
	//�������˾Ͳ�����д��
}
