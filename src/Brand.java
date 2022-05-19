import java.util.Hashtable;

public class Brand {
	String brandName;
	Hashtable<String, String> flavours = new Hashtable<String, String>();
	
	
	public void setName(String brandName) {
		this.brandName = brandName;
	}
	
	public String getBrandName() {
		return brandName;
	}
	
	public void addFlavour(String name, String numberOfBoxes) {
		if(flavours.get(name) != null) {
			
			/*int number = Integer.parseInt(numberOfBoxes);
			int originalNumber = Integer.parseInt(flavours.get(name));
			int newTotalNumber = number+originalNumber;*/
			
			flavours.put(name, Integer.toString(Integer.parseInt(numberOfBoxes)+Integer.parseInt(flavours.get(name))));
		}
		else {
			flavours.put(name, numberOfBoxes);
		}
		
	}
	
	public void toStringFlavours() {
		flavours.forEach((k,v) -> System.out.println(k+" "+v));
	}
}
