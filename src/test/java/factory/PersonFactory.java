package factory;

import entity.Customer;
import entity.Employee;
import entity.Person;

public class PersonFactory {

	public PersonFactory() {
	}
public static Person getPerson(String type) {
	if (type.equals("emp")) {
		return new Employee();
	} else {
		return new Customer();
	}
}
}
