package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.gallery.Nation;
import ink.moshuier.silken.entity.gallery.State;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface StateService extends BaseService<State>{
	public State get(String name);
	public JSONArray getTree();
	public List<State> getZhonghua();
}
