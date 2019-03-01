package entity;

import java.util.Locale;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Opus;

public class Employee implements ApplicationContextAware, Person {
	private String name;
	private Address addr;
	private ApplicationContext cxt;
	private Opus opus;  
	private static String MAX_AGE = "27";
	public Employee() {
	}
	public Employee(String name,Opus opus) {
		this.name = name;
		this.opus = opus;
	}
	
	/* (non-Javadoc)
	 * @see ink.moshuier.silken.entity.Person#run()
	 */
	@Override
	public void run() {
		Log.print("��ȡ����",cxt.getMessage("common.login", null, Locale.getDefault())); 
	}
	/* (non-Javadoc)
	 * @see ink.moshuier.silken.entity.Person#eat()
	 */
	@Override
	public void eat() {
		Log.print("�����", "��");
	}
	
	public static void sell() {
		Log.print("������", "��");
	}

	@Override
	public void init() {
		Log.print("����", "created");
		
	}

	@Override
	public void end() {
		Log.print("����", "destroy");
		
	}

	@Override
	public void setApplicationContext(ApplicationContext cxt) throws BeansException {

		System.out.println();
		this.cxt = cxt;
	}
	
	public static String getMAX_AGE() {
		return MAX_AGE;
	}

	public static void setMAX_AGE(String mAX_AGE) {
		MAX_AGE = mAX_AGE;
	}

	public Address getAddr() {
		return addr;
	}
	public void setAddr(Address addr) {
		this.addr = addr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ApplicationContext getCxt() {
		return cxt;
	}
	public Opus getOpus() {
		return opus;
	}

	public void setOpus(Opus opus) {
		this.opus = opus;
	}

	public void setCxt(ApplicationContext cxt) {
		this.cxt = cxt;
	}
	
}
