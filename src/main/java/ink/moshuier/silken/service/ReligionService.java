package ink.moshuier.silken.service;

import ink.moshuier.silken.entity.gallery.Religion;
import ink.moshuier.silken.service.base.BaseService;

public interface ReligionService extends BaseService<Religion>{
	public Religion get(String name);
}
