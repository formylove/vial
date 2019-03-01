package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.entity.gallery.City;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface OpusService extends BaseService<Opus>{
	public boolean hasExisted(String name);
	public Opus get(String name);
	public JSONArray getTree();
	public JSONArray getTree(String type);
	public List<Opus> getZhonghua(String type);
}
