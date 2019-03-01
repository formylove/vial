package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.gallery.Corporation;
import ink.moshuier.silken.entity.gallery.item.Figure;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface CorporationService extends BaseService<Corporation>{
	public Corporation get(String name);
	public JSONArray getTree(String industry);
	public List<Corporation> getAll(String industry,boolean zhonghua);
}
