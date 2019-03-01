package ink.moshuier.silken.dao;



import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.gallery.item.Figure;

public interface FigureDao extends BaseDao<Figure>{
	Figure get(int id);
}
