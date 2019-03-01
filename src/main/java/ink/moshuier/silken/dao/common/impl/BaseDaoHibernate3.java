package ink.moshuier.silken.dao.common.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import ink.moshuier.silken.dao.common.BaseDao;

/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
public class BaseDaoHibernate3<T> extends HibernateDaoSupport
	implements BaseDao<T>
{
	// ����ID����ʵ��
	public T get(Class<T> entityClazz, int id)
	{
		return getHibernateTemplate().get(entityClazz, id);
	}

	// ����ʵ��
	public int save(T entity)
	{
		return (int) getHibernateTemplate().save(entity);
	}

	// ����ʵ��
	public void update(T entity)
	{
		getHibernateTemplate().saveOrUpdate(entity);
	}

	// ɾ��ʵ��
	public void delete(T entity)
	{
		getHibernateTemplate().delete(entity);
	}

	// ����IDɾ��ʵ��
	public void delete(Class<T> entityClazz, int id)
	{
		delete(get(entityClazz , id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll(Class<T> entityClazz)
	{
		return (List<T>)getHibernateTemplate().find("select en from "
			+ entityClazz.getSimpleName() + " en");
	}

	@Override
	@SuppressWarnings("unchecked")
	public long findCount(Class<T> entityClazz)
	{
		List<Long> list = (List<Long>)getHibernateTemplate().find(
			"select count(*) from " + entityClazz.getSimpleName() + " en");
		return list.get(0);
	}
	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(final String hql,
		final int pageNo, final int pageSize)
	{
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List<T> list = getHibernateTemplate()
			.execute(new HibernateCallback<List<T>>()
		{
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public List<T> doInHibernate(Session session)
			{
				// ִ��Hibernate��ҳ��ѯ
				List<T> result = session.createQuery(hql)
					.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}

	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @param params ���hql��ռλ��������params���ڴ���ռλ������
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(final String hql , final int pageNo, 
		final int pageSize , final  Object... params)
	{
		// ͨ��һ��HibernateCallback������ִ�в�ѯ
		List<T> list = getHibernateTemplate()
			.execute(new HibernateCallback<List<T>>()
		{
			// ʵ��HibernateCallback�ӿڱ���ʵ�ֵķ���
			public List<T> doInHibernate(Session session)
			{
				// ִ��Hibernate��ҳ��ѯ
				Query query = session.createQuery(hql);
				// Ϊ����ռλ����HQL������ò���
				for(int i = 0 , len = params.length ; i < len ; i++)
				{
					query.setParameter(i + "" , params[i]);
				}
				List<T> result = query.setFirstResult((pageNo - 1) * pageSize)
					.setMaxResults(pageSize)
					.list();
				return result;
			}
		});
		return list;
	}

	@Override
	public void persist(T entity) {
	}

	@Override
	public Session getSession() {
		return null;
	}

	@Override
	public List<T> list(String name, int maxSize, int pageNum, String order, String conditions) {
		return null;
	}

	@Override
	public Integer getCnt(String name, String order, String conditions) {
		return null;
	}
}
