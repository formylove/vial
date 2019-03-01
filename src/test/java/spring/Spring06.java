package spring;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import ink.moshuier.silken.common.IPParser;
import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Opus;
import entity.Customer;

public class Spring06 {

	@SuppressWarnings({ "resource", "static-access", "rawtypes", "unused" })
	public static void main(String[] args) {
		//ʹ��AbstractApplicationContext 
		AbstractApplicationContext  ctx = new ClassPathXmlApplicationContext("beans6.xml");
		//�ӿڻ������
//		Employee employee = ctx.getBean("employee",Employee.class);
		Customer cus = ctx.getBean("cus",Customer.class);
}

}
