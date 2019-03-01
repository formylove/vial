package tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ink.moshuier.silken.common.Log;
import ink.moshuier.silken.entity.User;

public class testCollection {
	public static void main(String[] args) throws IOException {
		List<User> users = new ArrayList<User>();
		Log.print("size", users.size());
}
}
