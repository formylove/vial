package ink.moshuier.silken.service;

import ink.moshuier.silken.entity.gallery.item.Scenery;
import ink.moshuier.silken.service.base.BaseService;

public interface SceneryService extends BaseService<Scenery>{
	public Scenery get(String name);
}
