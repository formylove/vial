package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.CategoryDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.Category;
@Repository("categoryDaoHibernate4")
public class CategoryDaoHibernate4 extends BaseDaoHibernate4<Category> implements CategoryDao 
{	
	public CategoryDaoHibernate4() {
	}

	@Override
	public Category get(int id) {
		return get(Category.class,id);
	}
	//??????????????????
}
