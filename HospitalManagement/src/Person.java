
public abstract class Person extends HospComponents {

	//data fields
	private String id;
	private String name;
	
	
	//constructor
	public Person() {
		
	}
	
	public Person(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	
	//set methods
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	//get methods
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}	
	

}
