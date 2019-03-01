package tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import ink.moshuier.silken.common.Log;

public class testDate {

	public static void main(String[] args) throws ParseException {
		ArrayList e = new ArrayList<>();
		System.out.println(e.isEmpty());
		e.remove(0);//����
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Log.print("��������", cal.get(Calendar.DAY_OF_YEAR));
		cal.set(Calendar.DAY_OF_YEAR, cal.get(Calendar.DAY_OF_YEAR)+ 1);
		Log.print("��������", cal.get(Calendar.DAY_OF_YEAR));
		SimpleDateFormat x = new SimpleDateFormat("MM/dd/yyy");
		Date f;
			f = x.parse("11/22/2015");
		Date n = new Date();
	System.out.println(n.getYear() - f.getYear());	
	}

}
