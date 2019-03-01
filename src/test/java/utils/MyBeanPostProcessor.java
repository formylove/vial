package utils;

import entity.Customer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import ink.moshuier.silken.common.Log;

public class MyBeanPostProcessor implements BeanPostProcessor {

	public MyBeanPostProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		//����Ҫ.class
		if (bean instanceof Customer) {
			((Customer)bean).eat();
		}
		Log.print("��"+beanName, "����ǿ������");
		//���뷵��bean�������ò���ʵ�����޷��½�
		return bean;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		Log.print("��"+beanName, "ǰ��ǿ������");
		return bean;
	}

}
