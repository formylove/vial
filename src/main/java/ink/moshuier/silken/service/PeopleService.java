package ink.moshuier.silken.service;

import ink.moshuier.silken.entity.gallery.People;
import ink.moshuier.silken.service.base.BaseService;

public interface PeopleService extends BaseService<People>{
	public People get(String name);
}
