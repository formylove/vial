package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.gallery.Nation;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface NationService extends BaseService<Nation>{
	public Nation get(String name);
	public List<Nation> getAll(boolean flag);
	public JSONArray getTree();
	public List<Nation> getZhonghua();
}
