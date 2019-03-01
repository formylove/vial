package ink.moshuier.silken.dao.common;

import java.util.List;

import org.hibernate.Session;

public interface BaseDao<T>
{
	// ����ID����ʵ��
	T get(Class<T> entityClazz , int id);
	// ����ʵ��
	int save(T entity);
	// �־û�ʵ��
	void persist(T entity);
	// ����ʵ��
	void update(T entity);
	// ɾ��ʵ��
	void delete(T entity);
	// ���session
	Session getSession();
	// ����IDɾ��ʵ��
	void delete(Class<T> entityClazz , int id);
	// ��ȡ����ʵ��
	List<T> findAll(Class<T> entityClazz);
	List<T> findByPage(String hql,int pageNo, int pageSize);
	// ��ȡʵ������
	long findCount(Class<T> entityClazz);
	public List<T> list(String name,int maxSize,int pageNum,String order,String conditions);
	public Integer getCnt(String name,String order,String conditions);
}
