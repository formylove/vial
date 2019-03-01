package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.gallery.item.Figure;
import ink.moshuier.silken.service.base.BaseService;
import net.sf.json.JSONArray;

public interface FigureService extends BaseService<Figure>{
	public Figure get(String name);
	public List<Figure> getAll(String domain,boolean zhonghua);
	public JSONArray getTree(String domain);
}
