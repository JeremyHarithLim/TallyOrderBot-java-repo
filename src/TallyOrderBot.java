import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class TallyOrderBot {
	static ArrayList<Person> persons = new ArrayList<Person>();
	static Scanner sc = new Scanner(System.in);
	static BufferedReader breader = new BufferedReader(new InputStreamReader(System.in));

	private enum MenuItem {
		ADDPERSON,
		REMOVEPERSON,
		ADDORDER,
		REMOVEORDER,
		SHOWTALLY,
		END;
	}
	
	public static void main(String[] args) throws IOException {
		menu();
		/*Person person = getPerson();
		persons.add(person);
		addOrder(person);
		tallyOrders();*/
	}

	public static void menu() throws IOException{
		boolean found = false;
		MenuItem menuChoice = MenuItem.END;

		System.out.println("Please enter the command you would like to run: ");
		for(MenuItem menuItem: MenuItem.values()) {
			System.out.println(menuItem.toString());
		}
		

		while(!found) {
			String input = breader.readLine();
			for(MenuItem menuItem: MenuItem.values()) {
				if(input.equalsIgnoreCase(menuItem.toString())) {
					menuChoice = menuItem;
					found = true;
				}
			}
		}

		switch(menuChoice) {
			case ADDORDER:
				break;
			case REMOVEORDER:
				break;
			case ADDPERSON:
				break;
			case REMOVEPERSON:
				break;
			case SHOWTALLY:
				break;
			case END:
				break;
			default:
				break;
			
		}
	}
	
	public static Person getPerson() throws IOException{
		String name;
		Person person;
		System.out.println("Please input your name: ");
		name = breader.readLine();
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
	
	public static void addOrder(Person p) throws IOException {
		String brandName;
		String flavour;
		String number;
		Brand brand = new Brand();
		String[] input;
		
		System.out.println("Please input your brand: ");
		brandName = breader.readLine();
		brand.setName(brandName);
		p.addBrand(brand);
		
		System.out.println("Please enter your orders for this brand followed by the return key. Enter the name of the flavour followed by the number.");
		
		//need to sanitise inputs
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
