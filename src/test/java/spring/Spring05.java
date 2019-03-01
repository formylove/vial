package spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import ink.moshuier.silken.common.IPParser;
import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Opus;
import entity.Employee;

public class Spring05 {

	@SuppressWarnings({ "resource", "static-access", "rawtypes" })
	public static void main(String[] args) {
		ExpressionParser parser = new SpelExpressionParser();
		//�����ַ�������
Expression exp = parser.parseExpression("'hello world'.toUpperCase()");
	Log.print("toUpperCase", exp.getValue());
	Employee emp = new Employee("xiaojianren", new Opus());
//	exp = parser.parseExpression("name");
//	Log.print("name", exp.getValue());
	exp = parser.parseExpression("'Hello World'");
	Log.print("String", exp.getValue(String.class));
//	����ת��
	exp = parser.parseExpression("0.23");
	Log.print("Double", exp.getValue(Double.class));
	//------------ʹ��SpEL��������-----------
	// ����һ������
	exp = parser.parseExpression("new String[]{'java' , 'Struts' , 'Spring'}");
	Log.print("Obj[] size", ((Object[])exp.getValue()).length);
	
	//------------ʹ��SpEL����List����-----------
    exp = parser.parseExpression("{'java' , 'Struts' , 'Spring'}");
    Log.print("list", ((List)exp.getValue()).get(0));
	// ��������ά��List����
	exp = parser.parseExpression("{{'���Java����' , '���Android����'}, {'��' , 'ս����'}}");
	Log.print("list[]", ((List)((List)exp.getValue()).get(1)).get(0));

	//------------ʹ��SpEL����List���ϡ�Map���ϵ�Ԫ��-----------
	List<String> list = new ArrayList<String>();
	list.add("Java web");
	list.add("Spring");
	Map<String, Double> map = new HashMap<String, Double>();
	map.put("Java" , 80.0);
	map.put("Spring" , 89.0);
	//����һ��EvaluationContext������ΪSpEL����������������
	EvaluationContext ctx = new StandardEvaluationContext();
	// ������������
	ctx.setVariable("mylist" , list);
	ctx.setVariable("mymap" , map);
	// ����List���ϵĵڶ���Ԫ��
	Log.print("myList[1]", parser.parseExpression("#mylist[1]").getValue(ctx));
	// ����Map���ϵ�ָ��Ԫ��
	Log.print("#mymap['Java']", parser.parseExpression("#mymap['Java']").getValue(ctx));

//	------------ʹ��SpEL���÷���-----------
	// ����String�����substring()����
	Log.print("subString", parser.parseExpression("'HelloWorld'.substring(2, 5)").getValue());
	list.add("java");
	
	list.add("struts");
	list.add("spring");
	list.add("hibernate");
	// ����һ������
	ctx.setVariable("mylist" , list);
	// ����ָ������������Ķ����subList()����
	Log.print("myList.size", parser.parseExpression("#mylist.subList(4, 5).get(0)").getValue(ctx));

	//------------��SpEL��ʹ�������-----------
//	// �Լ��ϵĵ�һ��Ԫ�ؽ��и�ֵ
	parser.parseExpression("#mylist[0]='���Java����'").getValue(ctx);
//	// ���潫��� ���Java����
	Log.print("list ��ֵ", list.get(0));
	// ʹ����Ŀ�����
	Log.print("��Ŀ�����", parser.parseExpression("#mylist.size()>3?'myList���ȴ���3':'myList���Ȳ�����3'").getValue(ctx));

	//------------��SpEL��ʹ�����������-----------
	//�෽������
	parser.parseExpression("T(Employee).sell()").getValue();
	Log.print("Employee", parser.parseExpression("T(System).getProperty('os.name')").getValue());

	//------------��SpEL��ʹ�ð�ȫ��������-----------
	// ʹ�ð�ȫ�����������null
	Log.print("ʹ�ð�ȫ�����������null", parser.parseExpression("#emp?.name").getValue());
	//------------��SpEL��ʹ�ñ��ʽģ��-----------
	Employee p1 = new Employee("�����" ,new Opus());
	Employee p2 = new Employee("��˽�" ,new Opus());
	Expression expr = parser.parseExpression(
		"�ҵ�������#{name},������#{MAX_AGE}"
		, new TemplateParserContext());
	// ��ʹ��p1����name��height���������ʽģ���е�#{}
	System.out.println(expr.getValue(p1));
	// ��ʹ��p2����name��height���������ʽģ���е�#{}
	System.out.println(expr.getValue(p2));
	//------------��SpEL�жԼ��Ͻ���ͶӰ-----------
	list.add("���Java����");
	list.add("���Ajax����");
	list.add("���iOS����");
	list.add("����Java EE��ҵӦ��ʵս");
	ctx.setVariable("mylist" , list);
	// �õ����¼��ϵ�Ԫ����ԭ���ϵ�ÿ��Ԫ��length()��������ֵ
	expr = parser.parseExpression("#mylist.![length()]");
	Log.print("ӳ�亯��", expr.getValue(ctx));
	List<Employee> list2 = new ArrayList<Employee>();
	list2.add(new Employee( "�����" , new Opus()));
	list2.add(new Employee( "��˽�" , new Opus()));
	list2.add(new Employee( "ţħ��" , new Opus()));
	ctx.setVariable("mylist2" , list2);
	// �õ����¼��ϵ�Ԫ����ԭ���ϵ�ÿ��Ԫ��name����ֵ
	expr = parser.parseExpression
		("#mylist2.![name]");
	Log.print("ӳ������", expr.getValue(ctx));
	//------------ʡ�԰���-----------
	Log.print("Math", parser.parseExpression(
			"T(Math).sqrt(9)").getValue());
	//------------��SpEL�е��ù�����-----------
	parser.parseExpression(
			"T(javax.swing.JOptionPane).showConfirmDialog(null, '���ӻ��������')").getValue();
}

}
