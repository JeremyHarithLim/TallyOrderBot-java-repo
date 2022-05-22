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
			if(!found) {
				System.out.println("I do not understand that command, please reenter your command: ");
			}
			
		}

		switch(menuChoice) {
			case ADDORDER:
				addOrder();
				break;
			case REMOVEORDER:
				removeOrder();
				break;
			case ADDPERSON:
				addPerson();
				break;
			case REMOVEPERSON:
				removePerson();
				break;
			case SHOWTALLY:
				showTally();
				break;
			case END:
				break;
			default:
				break;
			
		}
	}

	public static void addOrder() throws IOException {
		//need to check for which person the user is adding an order to
		// if new person, addPerson, if not, add an order to a current person
		String brandName;
		String flavour;
		String number;
		Brand brand = new Brand();
		String[] input;
		Person p = new Person();
		
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

	public static void removeOrder() {

	}
	
	public static Person addPerson() throws IOException{
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

	public static void removePerson() {

	}
	
	public static void showTally() {
		for(Person person1 : persons) {
			for(Brand brand1 : person1.getBrandList() ) {
				System.out.println(person1.name+"'s order is: ");
				System.out.println(person1.getBrand(brand1).getBrandName());
				person1.getBrand(brand1).toStringFlavours();
			}
		}
	}

}
