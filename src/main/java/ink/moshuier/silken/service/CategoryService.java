package ink.moshuier.silken.service;

import java.util.List;

import ink.moshuier.silken.entity.Category;
import ink.moshuier.silken.service.base.BaseService;

public interface CategoryService extends BaseService<Category>{
	public Category get(String name);
	List<Category> list();
	public void updateOrder(int before,int after);
}
