import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Medical extends HospComponents {
	
	//data fields
	private String name;
	private String manufacturer;
	private String expiryDate;
	private int cost;
	private int count;
	private String atrArr[] = {"Name", "Manufacturer", "ExpiryDate", "Cost", "Count"}; //attribute array  
    private int sizeArr[] = {150, 200, 200, 100, 100}; //size array
	
	
	//constructor
	public Medical() {
		
	}
	
	public Medical(String name, String manu, String exp, int cost, int count) {
		this.name = name;
		this.manufacturer = manu;
		this.expiryDate = exp;
		this.cost = cost;
		this.count = count;
	}
	

	//set methods
	public void setName(String name) {
		this.name = name;
	}
		
	public void setManufacturer(String manu) {
		this.manufacturer = manu;
	}
		
	public void setExpiryDate(String exp) {
		this.expiryDate = exp;
	}
		
	public void setCost(int cost) {
		this.cost = cost;
	}
		
	public void setCount(int count) {
		this.count = count;
	}
		
		
	//get methods
	public String getName() {
		return name;
	}
		
	public String getManufacturer() {
		return manufacturer;
	}
		
	public String getExpiryDate() {
		return expiryDate;
	}
		
	public int getCost() {
		return cost;
	}
		
	public int getCount() {
		return count;
	}
		
	
	@Override
	public String[] getatrArr() {
		return atrArr;
	}

	
	@Override
	public int[] getsizeArr() {
		return sizeArr;
	}
	
	@Override
	public TextField[] getParas() {	
		//textfields
        TextField addName = new TextField();
        TextField addManu = new TextField();
        TextField addExpiry = new TextField();
        TextField addCost = new TextField();
        TextField addCount = new TextField();
        
        TextField[] tfArr = {addName, addManu, addExpiry, addCost, addCount}; //textfield array       
        return tfArr;
        
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public void getNew(TextField[] fields, ObservableList<?> medicals, JFrame al) {
		
		try {
			Integer.parseInt(fields[3].getText());
			Integer.parseInt(fields[4].getText());
		}
			
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(al,"Invalid integer input!","Alert",JOptionPane.ERROR_MESSAGE);  
			return;
		}
		
		((ObservableList<Medical>)medicals).add(new Medical(fields[0].getText(), fields[1].getText(), fields[2].getText(), Integer.parseInt(fields[3].getText()), Integer.parseInt(fields[4].getText())));
	
	}
	
	
	@Override
	public void drawExisting(TableView<?> medicalTable) {
        Medical selectedItem = (Medical) medicalTable.getSelectionModel().getSelectedItem();
        medicalTable.getItems().remove(selectedItem);
	}


}
