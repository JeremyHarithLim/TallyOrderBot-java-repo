import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class TallyOrderBot {
	static ArrayList<Person> persons = new ArrayList<Person>();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException {
		Person person = getPerson();
		persons.add(person);
		getOrder(person);
		tallyOrders();
	}
	
	public static Person getPerson() {
		String name;
		Person person;
		System.out.println("Please input your name: ");
		name = sc.next();
		for(Person person1 : persons) {
			if(name.equals(person1.getName())) {
				System.out.println("Accessing "+name+"'s order.");
				return person1;
			}
		}
		person = new Person();
		person.setName(name);
		
		return person;
	}
	
	public static void getOrder(Person p) throws IOException {
		BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));
		String brandName;
		String flavour;
		String number;
		Brand brand = new Brand();
		String[] input;
		
		System.out.println("Please input your brand: ");
		brandName = sc.next();
		brand.setName(brandName);
		
		p.addBrand(brand);
		
		System.out.println("Please enter your orders for this brand followed by the return key. Enter the name of the flavour followed by the number.");
		
		while(true) {
			input = breader.readLine().split(" ");
			if(input[0].equalsIgnoreCase("done") || input[1].equalsIgnoreCase("done")) {
				break;
			}
			else {
				flavour = input[0];
				number = input[1];
				p.getBrand(brand).addFlavour(flavour, number);
			}
		}
	}
	
	public static void tallyOrders() {
		for(Person person1 : persons) {
			for(Brand brand1 : person1.getBrandList() ) {
				System.out.println(person1.name+"'s order is: ");
				System.out.println(person1.getBrand(brand1).getBrandName());
				person1.getBrand(brand1).toStringFlavours();
			}
		}
	}

}
