package ink.moshuier.silken.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

public class MsgConstants {
public static final String SUCCESS="success"; 
public static final String DONE="done"; 
public static final String OK="OK"; 
public static final String EDIT="edit"; 
public static final String DISPLAY="display"; 
public static final String HOMEPAGE="homepage"; 
public static final String LIST="list"; 
public static final String DATAGRID="datagrid"; 
public static final String RETRIEVE="retrieve"; 
public static final String NO="N"; 
public static final String YES="Y"; 
public static Properties ISO31661ALPHA3 = new Properties(); 
public static Properties DYNASTY = new Properties(); 
public static Properties AUTHORITY = new Properties(); 
public static Properties SCORE = new Properties(); 
public static Properties RATE = new Properties();
public static Properties GENDER = new Properties();
public static Map<Integer,String> CU = new LinkedHashMap<Integer,String>();
public MsgConstants() {
	AUTHORITY.put("10", "�����˿ɼ�");
	AUTHORITY.put("0", "�Լ��ɼ�");
	AUTHORITY.put("5", "��¼�ɼ�");
	AUTHORITY.put("1", "�麣ip�ɼ�");
	AUTHORITY.put("2", "�麣ip���ɼ�");
	System.out.println("pppppppppppppppppp");
	GENDER.put('f', "Ů");
	GENDER.put('m', "��");
	
	CU.put(100000000, "��");
	CU.put(1000000, "����");
	CU.put(10000, "��");
	CU.put(1000, "ǧ");
	CU.put(100, "��");
	CU.put(10, "ʮ");
	CU.put(1, "һ");
	
	DYNASTY.put("nope", "�ִ�");
	DYNASTY.put("���", "���");
	DYNASTY.put("�峯", "�峯");
	DYNASTY.put("����", "����");
	DYNASTY.put("Ԫ��", "Ԫ��");
	DYNASTY.put("�γ�", "�γ�");
	DYNASTY.put("�Ƴ�", "�Ƴ�");
	DYNASTY.put("����", "����");
	DYNASTY.put("����", "����");
	DYNASTY.put("ս��", "ս��");
	
	RATE.put("nope", "����");
	RATE.put("�ٶ�һ�飡", "�ٶ�һ�飡");
	RATE.put("�ض�", "�ض�");
	RATE.put("ֵ��һ��", "ֵ��һ��");
	RATE.put("����һ��", "����һ��");
	RATE.put("��ֵ�ö�", "��ֵ�ö�");
	RATE.put("��", "��");
	
	ISO31661ALPHA3.put("�й�", "�й�");
	ISO31661ALPHA3.put("����", "����");
	ISO31661ALPHA3.put("�ձ�", "�ձ�");
	ISO31661ALPHA3.put("����", "����");
	ISO31661ALPHA3.put("̩��", "̩��");
	ISO31661ALPHA3.put("����", "����");
	ISO31661ALPHA3.put("Ӣ��", "Ӣ��");
	ISO31661ALPHA3.put("�¹�", "�¹�");
	ISO31661ALPHA3.put("����˹", "����˹");
	ISO31661ALPHA3.put("������", "������");
	ISO31661ALPHA3.put("�����", "�����");
	ISO31661ALPHA3.put("�µ���", "�µ���");
	ISO31661ALPHA3.put("����", "����");
}

//����app��������ִֻ��һ��
static {
	AUTHORITY.put("10", "�����˿ɼ�");
	AUTHORITY.put("0", "�Լ��ɼ�");
	AUTHORITY.put("5", "��¼�ɼ�");
	AUTHORITY.put("1", "�麣ip�ɼ�");
	AUTHORITY.put("2", "�麣ip���ɼ�");
	
	GENDER.put('f', "Ů");
	GENDER.put('m', "��");
	
	CU.put(100000000, "��");
	CU.put(1000000, "����");
	CU.put(10000, "��");
	CU.put(1000, "ǧ");
	CU.put(100, "��");
	CU.put(10, "ʮ");
	CU.put(1, "һ");
	
	DYNASTY.put("nope", "�ִ�");
	DYNASTY.put("���", "���");
	DYNASTY.put("�峯", "�峯");
	DYNASTY.put("����", "����");
	DYNASTY.put("Ԫ��", "Ԫ��");
	DYNASTY.put("�γ�", "�γ�");
	DYNASTY.put("�Ƴ�", "�Ƴ�");
	DYNASTY.put("����", "����");
	DYNASTY.put("����", "����");
	DYNASTY.put("ս��", "ս��");
	
	RATE.put("nope", "����");
	RATE.put("�ٶ�һ�飡", "�ٶ�һ�飡");
	RATE.put("�ض�", "�ض�");
	RATE.put("ֵ��һ��", "ֵ��һ��");
	RATE.put("����һ��", "����һ��");
	RATE.put("��ֵ�ö�", "��ֵ�ö�");
	RATE.put("��", "��");
	
	ISO31661ALPHA3.put("�й�", "�й�");
	ISO31661ALPHA3.put("����", "����");
	ISO31661ALPHA3.put("�ձ�", "�ձ�");
	ISO31661ALPHA3.put("����", "����");
	ISO31661ALPHA3.put("̩��", "̩��");
	ISO31661ALPHA3.put("����", "����");
	ISO31661ALPHA3.put("Ӣ��", "Ӣ��");
	ISO31661ALPHA3.put("�¹�", "�¹�");
	ISO31661ALPHA3.put("����˹", "����˹");
	ISO31661ALPHA3.put("������", "������");
	ISO31661ALPHA3.put("�����", "�����");
	ISO31661ALPHA3.put("�µ���", "�µ���");
	ISO31661ALPHA3.put("����", "����");
	Log.print("msgConstant", "init");
}
	public static void init() {}
}