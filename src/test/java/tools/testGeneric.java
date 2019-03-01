package tools;

import java.util.ArrayList;
import java.util.List;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.User;

public class testGeneric {
	public static void main(String[] args) throws Exception {
		List<User> us = new ArrayList<User>();
		Log.print("Class", us.getClass());
		Log.print("equal", us instanceof ArrayList);
		//���Ͳ�������
//		Log.print("equal", us instanceof ArrayList<User>);
	}
}
