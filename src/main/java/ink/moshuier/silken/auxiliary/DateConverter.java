package ink.moshuier.silken.auxiliary;

import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.common.TimeManager;
public class DateConverter extends StrutsTypeConverter {

	@Override
	public Date convertFromString(Map arg0, String[] jspValue, Class toClass) {
		Log.print("����", toClass);
		return TimeManager.transformToDate(jspValue[0]);
	}

	@Override
	public String convertToString(Map arg0, Object input) {
		return ((Integer)input).toString();
	}}


