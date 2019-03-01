package ink.moshuier.silken.dao.impl;


import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.MusicDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Music;
@Repository("musicDaoHibernate4")
public class MusicDaoHibernate4 extends BaseDaoHibernate4<Music> implements MusicDao 
{	
	public MusicDaoHibernate4() {
	}

	@Override
	public Music get(int id) {
		return get(Music.class,id);
	}
	//�������˾Ͳ�����д��
}
