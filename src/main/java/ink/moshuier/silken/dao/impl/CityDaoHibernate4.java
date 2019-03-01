package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.CityDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.City;
@Repository("cityDaoHibernate4")
public class CityDaoHibernate4 extends BaseDaoHibernate4<City> implements CityDao 
{	
	public CityDaoHibernate4() {
	}

	@Override
	public City get(int id) {
		return get(City.class,id);
	}
	//�������˾Ͳ�����д��
}
