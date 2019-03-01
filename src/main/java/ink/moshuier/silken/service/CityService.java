package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.gallery.City;
import ink.moshuier.silken.entity.gallery.State;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface CityService extends BaseService<City>{
	public City get(String name);
	public JSONArray getTree();
	public List<City> getZhonghua();
}
