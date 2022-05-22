import java.util.ArrayList;

public class Person {
	String name;
	ArrayList<Brand> brands = new ArrayList<Brand>();

	Person(){}

	Person(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void addBrand(Brand brand) {
		brands.add(brand);
	}
	
	public ArrayList<Brand> getBrandList() {
		return brands;
	}
	
	public Brand getBrand(Brand brand) {
		for(Brand brand1 : brands) {
			if(brand1.getBrandName().equals(brand.getBrandName())) {
				return brand1;
			}
		}
		return null;
	}
}
