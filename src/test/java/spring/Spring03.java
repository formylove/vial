package spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ink.moshuier.silken.common.Log;
import entity.Customer;
import entity.Employee;

public class Spring03 {

	@SuppressWarnings({ "resource", "static-access" })
	public static void main(String[] args) {
//ʹ��AbstractApplicationContext 
AbstractApplicationContext  ctx = new ClassPathXmlApplicationContext("beans03.xml");
//�ӿڻ������
Employee employee = ctx.getBean("employee",Employee.class);
employee.run();
//�������
//Log.print("�������", employee.getAddr().getDetail());
//static���ô���bean������bean
Employee emp = ctx.getBean("emp",Employee.class);
emp.eat();
Log.print("���� ����", emp.getName());
//Log.print("���� ��ַ", emp.getAddr().getDetail());
Customer cus = ctx.getBean("cus",Customer.class);
cus.eat();
//Log.print("�˿� ����", cus.getName());
//Log.print("�˿� ��ַ", cus.getAddr().getDetail());
//bean id ��ȡ��addr����
//Log.print("bean id", cus.getAddr().getBeanName());
//Log.print("bean id", emp.getAddr().getBeanName());
//depend on
Log.print("bean id", emp.getOpus());
//propertyPath����ȡ����bean����ֵ
Log.print("propertyPath", ctx.getBean("empDetail"));
//��þ�̬field
Log.print("bean id", emp.getMAX_AGE());
//���սӿ���
//�ص�ctx
ctx.registerShutdownHook();
	
	
	
	
	
	
	
	
	
	}

}
