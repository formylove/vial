package TESTCASE;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.Note;

public class TestRefTrans {

	public static void main(String[] args) {
		Note n = new Note();
		n.setAuthor("helloKitty");
		change(n);
		//���ô��ݣ����������õı�����������
		Log.print("after change", n.getTitle());
		replace(n);
		//���ô��ݣ�����
		Log.print("after replace", n.getAuthor());
		String s = "original";
		changeStr(s);
		Log.print("String after change", s);
		
	}
public static void change(Note note) {
	note.setTitle("nice lady");
}
public static void replace(Note note) {
	Note n = new Note();
	n.setAuthor("dazuihou");
	note = n;
}
public static void changeStr(String s) {
	s = s + "changed";
}
}
