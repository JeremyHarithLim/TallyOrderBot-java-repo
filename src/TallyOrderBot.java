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
		SHOWPERSONS,
		SHOWTALLY,
		END;
	}
	
	public static void main(String[] args) throws IOException {
		menu();
	}

	public static void menu() throws IOException{
		boolean menuLoop = true;
		while(menuLoop){
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
				case SHOWPERSONS:
					showPersons();
					break;
				case SHOWTALLY:
					showTally();
					break;
				case END:
					menuLoop = false;
					break;
				
				default:
					break;
			}
			System.out.println();
		}

		
	}

	public static void addOrder() throws IOException {
		//need to check for which person the user is adding an order to
		// if new person, addPerson, if not, add an order to a current person
		String personName;
		String brandName;
		String flavour;
		String number;
		String[] input;
		Brand brand = new Brand();
		Person p;

		System.out.println("Please enter the name of the person youre adding an order to: ");
		personName = breader.readLine();
		
		if(checkPerson(personName) != null) {
			p = checkPerson(personName);
		}
		else {
			p = new Person(personName);
		
		}
		
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
		persons.add(p);
	}

	public static void removeOrder() throws IOException {
		
	}
	
	public static void addPerson() throws IOException{
		String name;
		Person newPerson;

		System.out.println("Please input your name: ");
		name = breader.readLine();

		if(checkPerson(name) != null) {
			System.out.println("This person already exists.");
		}
		else {
			newPerson = new Person(name);
			persons.add(newPerson);
		}
		
	}

	public static void removePerson() {

	}

	//checks for whether a person of the name already exists, if they do, return the Person object, else returns null.
	public static Person checkPerson(String name) {
		for(Person p : persons) {
			if(name.equalsIgnoreCase(p.getName())) {
				return p;
			}
		}
		return null;	
	}
	
	public static void showTally() {
		for(Person person1 : persons) {
			System.out.println(person1.name+"'s order is: ");
			for(Brand brand1 : person1.getBrandList() ) {
				System.out.println(person1.getBrand(brand1).getBrandName());
				person1.getBrand(brand1).toStringFlavours();
			}
		}
	}

	public static void showPersons() {
		for(Person p: persons) {
			System.out.println(p.getName());
		}
	}

}
