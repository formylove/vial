package ink.moshuier.silken.dao.impl;

import org.springframework.stereotype.Repository;

import ink.moshuier.silken.dao.FigureDao;
import ink.moshuier.silken.dao.common.impl.BaseDaoHibernate4;
import ink.moshuier.silken.entity.gallery.item.Figure;
@Repository("figureDaoHibernate4")
public class FigureDaoHibernate4 extends BaseDaoHibernate4<Figure> implements FigureDao 
{	
	public FigureDaoHibernate4() {
	}

	@Override
	public Figure get(int id) {
		return get(Figure.class,id);
	}
	//�������˾Ͳ�����д��
}
