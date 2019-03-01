package entity;

import java.util.*;

import javax.persistence.*;
/**
 * Description:
 * <br/>��վ: <a href="http://www.crazyit.org">���Java����</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
@Entity
@Table(name="address_inf")
public class Address
{
	// ��ʶ����
	@Id @Column(name="address_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	// �����ַ��ϸ��Ϣ�ĳ�Ա����
	private String addressDetail;
	// �����Addressʵ�����й�����Personʵ��
	@ManyToMany(targetEntity=Person.class)
	// ӳ�����ӱ�ָ�����ӱ�ı���Ϊperson_address
	@JoinTable(name="person_address",
		// ӳ�����ӱ�����Ϊaddress_id������У�
		// ���в��յ�ǰʵ���Ӧ���address_id������
		joinColumns=@JoinColumn(name="address_id"
			, referencedColumnName="address_id"),
		// ӳ�����ӱ�����Ϊperson_id������У�
		// ���в��յ�ǰʵ���Ӧ���person_id������
		inverseJoinColumns=@JoinColumn(name="person_id"
			, referencedColumnName="person_id")
	)
	private Set<Person> persons
		= new HashSet<>();

	// �޲����Ĺ�����
	public Address()
	{
	}
	// ��ʼ��ȫ����Ա�����Ĺ�����
	public Address(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}

	// addressId��setter��getter����
	public void setAddressId(int addressId)
	{
		this.addressId = addressId;
	}
	public int getAddressId()
	{
		return this.addressId;
	}

	// addressDetail��setter��getter����
	public void setAddressDetail(String addressDetail)
	{
		this.addressDetail = addressDetail;
	}
	public String getAddressDetail()
	{
		return this.addressDetail;
	}

	// persons��setter��getter����
	public void setPersons(Set<Person> persons)
	{
		this.persons = persons;
	}
	public Set<Person> getPersons()
	{
		return this.persons;
	}
}