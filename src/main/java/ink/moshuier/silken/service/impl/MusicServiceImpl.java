package ink.moshuier.silken.service.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ink.moshuier.silken.common.MusicUtils;
import ink.moshuier.silken.dao.FigureDao;
import ink.moshuier.silken.dao.MusicDao;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.essay.Essay;
import ink.moshuier.silken.service.MusicService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@Service("musicService")
@Transactional
public class MusicServiceImpl implements MusicService {
	@Resource(name = "musicDaoHibernate4")
	private MusicDao musicDao;
	
	public MusicServiceImpl() {
	}
	@Override
	public List<Music> list(int maxSize,int pageNum,String order,String conditions){
	return musicDao.list("Music m", maxSize, pageNum, order, conditions);
	}
	public int save(Music music) {
		Music e = get(music.getName());
		if(e == null){
			return musicDao.save(music);
		}else{
			return e.getId();
		}
	}
	@Override
	public Music get(String name) {
		String hql ="from Music where name=:name";
		Session s = musicDao.getSession();
		@SuppressWarnings("unchecked")
		Music essay = (Music)s.createQuery(hql).setParameter("name", name).
		setFirstResult(0).setMaxResults(1).uniqueResult();
		return essay;
	}
	
	@Override
	public void persist(Music music) {
		musicDao.persist(music);
	}
	
	@Override
	public void delete(int id) {
		Music n = get(id); 
		delete(n);
	}

	@Override
	public void delete(Music music) {
		musicDao.delete(music);
		MusicUtils.delete(music.getName());
	}
	@Override
	public Music delete(String name) {
		Music music = get(name);
		delete(music);
		return music;
	}
	@Override
	public void update(Music music) {
		musicDao.update(music);
	}
	@Override
	public Music remove(int id) {
		Music music = get(id);
		return music;
	}

	@Override
	public Music get(int id) {
		return musicDao.get(id);
	}

	@Override
	public void recover(int id) {
		Music music = get(id);
		if(null != music){
			musicDao.update(music);
		}
	}

	public MusicDao getMusicDao() {
		return musicDao;
	}

	public void setMusicDao(MusicDao musicDao) {
		this.musicDao = musicDao;
	}

	@Override
	public JSONArray getTree() {
		JSONArray tree = new JSONArray();
		////////////////////////zhonghua////////////////////
		JSONObject zhonghua = new JSONObject();
		zhonghua.accumulate("text", "�л�");
		zhonghua.accumulate("state", "closed");
		JSONArray yanhuang = new JSONArray();
		List<Music> zh = getZhonghua(true);
		if(zh !=null && zh.size() > 0){
			for(Music music: zh){
				
				JSONObject zisun = new JSONObject();
				zisun.accumulate("id", music.getId());
				zisun.accumulate("text", music.getName());
				yanhuang.add(zisun);
			}
			zhonghua.accumulate("children", yanhuang);
			tree.add(zhonghua);
		}
		////////////////////////zhonghua////////////////////
		JSONObject foreign = new JSONObject();
		foreign.accumulate("text", "����");
		foreign.accumulate("state", "closed");
		JSONArray xiou = new JSONArray();
		List<Music> fr = getZhonghua(false);
		if(fr !=null && fr.size() > 0){
			for(Music music: fr){
				
				JSONObject bangyang = new JSONObject();
				bangyang.accumulate("id", music.getId());
				bangyang.accumulate("text", music.getName());
				xiou.add(bangyang);
			}
			foreign.accumulate("children", xiou);
			tree.add(foreign);
		}
	return tree;
	}

	@Override
	public List<Music> getZhonghua(boolean zhonghua) {
		String hql ="from Music m where m.nation.zhonghua = "+(zhonghua?1:0);
		Session s = musicDao.getSession();
		List<Music> musics = (List<Music>)s.createQuery(hql).list();
		return musics;
	}
	public static List<String> getStyles(){
		String hql ="select m.style from Music m group by m.style having m.style is not null and m.style <> '' ORDER BY count(*) DESC";
		ServletContext context =  ServletActionContext.getServletContext(); 
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context); 
		MusicDao musicDao = (MusicDao) ctx.getBean("musicDaoHibernate4");
		Session s = musicDao.getSession();
		List<String> styles = s.createQuery(hql).list();
		return styles;
	}
	public List<String> getAllStyles(){
		String hql ="select m.style from Music m group by m.style having m.style is not null and m.style <> '' and count(*)>0 ORDER BY count(*) DESC";
		Session s = musicDao.getSession();
		List<String> styles = s.createQuery(hql).list();
		return styles;
	}
	@Override
	public void clear(Integer precedence) {
		if(precedence != 0){
		String hql ="update Music m set m.precedence = 0 where m.precedence = :precedence";
		Session s = musicDao.getSession();
		s.createQuery(hql).setParameter("precedence", precedence).executeUpdate();
		}
	}

	@Override
	public List<Music> getHomepageList() {
		String hql ="from Music order by precedence desc,music_id desc";
		Session s = musicDao.getSession();
		List<Music> musics = (List<Music>)s.createQuery(hql).setMaxResults(3).list();
		return musics;
	}




}
