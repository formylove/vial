package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.Opus;
import ink.moshuier.silken.entity.gallery.Dynasty;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface DynastyService extends BaseService<Dynasty>{
	public Dynasty get(String name);
	public JSONArray getTree();
	public List<Dynasty> getZhonghua(boolean zhonghua);
}
