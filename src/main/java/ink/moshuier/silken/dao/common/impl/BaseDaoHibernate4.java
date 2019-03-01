package ink.moshuier.silken.dao.common.impl;

import org.hibernate.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ink.moshuier.silken.dao.common.BaseDao;
import ink.moshuier.silken.entity.Music;
import ink.moshuier.silken.entity.Note;

import java.util.List;

import javax.annotation.Resource;

@Repository("baseDaoHibernate4")
public class BaseDaoHibernate4<T> implements BaseDao<T>
{	
	// DAO������г־û������ײ�������SessionFactory���
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;
	public BaseDaoHibernate4() {
	}
	// ����ע��SessionFactory�����setter����
	// ����ID����ʵ��
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz , int id)
	{
		Session s = sessionFactory.getCurrentSession();
		//load����һ�����񣬲���������
		T rvt = (T)s.get(entityClazz , id);
		return  rvt;
	}
	// ����ʵ��
	public int save(T entity)
	{
//		getSessionFactory().getCurrentSession().refresh(entity);
		int cnt = (int) getSessionFactory().getCurrentSession()
			.save(entity);
		return cnt;
	}
	// ����ʵ��
	public void persist(T entity)
	{
	getSessionFactory().getCurrentSession()
				.persist(entity);
	}
	// ����ʵ��
	public void update(T entity)
	{
		getSessionFactory().getCurrentSession().saveOrUpdate(entity);
	}
	// ɾ��ʵ��
	public void delete(T entity)
	{
		getSessionFactory().getCurrentSession().delete(entity);
//		���������Ҫflush
//		s.flush();
	}
	@Override
	public Session getSession()
	{  
		Session s = sessionFactory.openSession();
		return s;
	}
	// ����IDɾ��ʵ��
	public void delete(Class<T> entityClazz , int id)
	{
		getSessionFactory().getCurrentSession()
			.createQuery("delete " + entityClazz.getSimpleName()
				+ " en where en.id = ?0")
			.setParameter("0" , id)
			.executeUpdate();
	}
	// ��ȡ����ʵ��
	public List<T> findAll(Class<T> entityClazz)
	{
		return find("from "+ entityClazz.getSimpleName() + " en");
	}
	// ����HQL����ѯʵ��
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql)
	{
		Session s = getSession();
		return (List<T>)s
			.createQuery(hql)
			.list();
	}
	// ��ȡʵ������
	public long findCount(Class<T> entityClazz)
	{
		List<?> l = find("select count(*) from "
			+ entityClazz.getSimpleName());
		// ���ز�ѯ�õ���ʵ������
		if (l != null && l.size() == 1 )
		{
			return (Long)l.get(0);
		}
		return 0;
	}


	// ���ݴ�ռλ������HQL����ѯʵ��
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql , Object... params)
	{
		// ������ѯ
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		// Ϊ����ռλ����HQL������ò���
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		return (List<T>)query.list();
	}
		// ������ѯ
		public List<T> list(String name,int maxSize,int pageNum,String order,String conditions){
			String hql = "from " + name + (conditions!=null?" where "+conditions:" ") + (order!=null?" order by " + order:"");
			Session s = getSession();
			List<T> objs = s.createQuery(hql).setMaxResults(maxSize).setFirstResult((pageNum-1)*maxSize).list();
			return objs;
			}
		public Integer getCnt(String name,String order,String conditions){
			String hql = "select count(*) from " + name + (conditions!=null?" where "+conditions:" ") + (order!=null?" order by ":"") + order;
			Session s = getSession();
			Integer cnt = Integer.parseInt(((Long)s.createQuery(hql).uniqueResult()).toString());
			return cnt;
		}
	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql,int pageNo, int pageSize)
	{
		// ������ѯ
		return getSessionFactory().getCurrentSession()
			.createQuery(hql)
			// ִ�з�ҳ
			.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}
	/**
	 * ʹ��hql �����з�ҳ��ѯ����
	 * @param hql ��Ҫ��ѯ��hql���
	 * @param params ���hql��ռλ��������params���ڴ���ռλ������
	 * @param pageNo ��ѯ��pageNoҳ�ļ�¼
	 * @param pageSize ÿҳ��Ҫ��ʾ�ļ�¼��
	 * @return ��ǰҳ�����м�¼
	 */
	@SuppressWarnings("unchecked")
	protected List<T> findByPage(String hql , int pageNo, int pageSize
		, Object... params)
	{
		// ������ѯ
		Query query = getSessionFactory().getCurrentSession()
			.createQuery(hql);
		// Ϊ����ռλ����HQL������ò���
		for(int i = 0 , len = params.length ; i < len ; i++)
		{
			query.setParameter(i + "" , params[i]);
		}
		// ִ�з�ҳ�������ز�ѯ���
		return query.setFirstResult((pageNo - 1) * pageSize)
			.setMaxResults(pageSize)
			.list();
	}
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	public SessionFactory getSessionFactory()
	{
		return this.sessionFactory;
	}
}
