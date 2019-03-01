package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.Poster;
import ink.moshuier.silken.service.base.BaseService;

public interface PosterService extends BaseService<Poster>{
	public Poster get(String name);
	List<Poster> list();
	public void updateOrder(int before,int after);
}
