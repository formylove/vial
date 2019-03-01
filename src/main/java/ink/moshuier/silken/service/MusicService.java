package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface MusicService extends BaseService<Music>{
	public Music get(String name);
	public Music delete(String name);
	public void clear(Integer precedence);
	public JSONArray getTree();
	public List<Music> getZhonghua(boolean zhonghua);
	List<Music> list(int maxSize,int pageNum,String order,String conditions);
	public List<Music> getHomepageList();
	public List<String> getAllStyles();
}
