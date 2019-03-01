package tools;

import java.util.Calendar;
import java.util.Date;

import ink.moshuier.silken.common.Log;

public class testCalendar {

	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Log.print("��������", cal.get(Calendar.DAY_OF_YEAR));
		cal.add(Calendar.MINUTE, 33);
		Log.print("�Ӻ�ʱ��", cal.getTime());
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+ 1);
		Log.print("��������", cal.get(Calendar.DAY_OF_YEAR));
	}

}
