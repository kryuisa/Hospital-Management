import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Staff extends Person {

	//data fields
	private String designation;
	private String sex;
	private int salary;

    private String atrArr[] = {"Id", "Name", "Designation", "Sex", "Salary"}; //attribute array  
    private int sizeArr[] = {80, 150, 200, 100, 100}; //size array
	
    
	//constructor
	public Staff() {
		
	}
	
	public Staff(String id, String name, String desig, String sex, int sal) {
		super(id, name);
		this.designation = desig;
		this.sex = sex;
		this.salary = sal;
	}
	
	
	//set methods
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setDesignation(String desig) {
		this.designation = desig;
	}
	
	public void setSalary(int sal) {
		this.salary = sal;
	}
	
	
	//get methods
	public String getSex() {
		return sex;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public int getSalary() {
		return salary;
	}
	
	
	@Override
	public int[] getsizeArr() {
		return sizeArr;
	}
	
	
	@Override
	public String[] getatrArr() {
		return atrArr;
	}
	
	
	@Override
	public TextField[] getParas() {	
		//textfields
        TextField addId = new TextField();
        TextField addName = new TextField();
        TextField addDesigna = new TextField();
        TextField addSex = new TextField();
        TextField addSal = new TextField();
        
        TextField[] tfArr = {addId, addName, addDesigna, addSex, addSal}; //textfield array       
        return tfArr;
        
	}

		
	@SuppressWarnings("unchecked")
	@Override
	public void getNew(TextField[] fields, ObservableList<?> staffs, JFrame al) {
		
		try {
			Integer.parseInt(fields[4].getText());
		}
			
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(al,"Invalid Salary input!","Alert",JOptionPane.ERROR_MESSAGE);  
			return;
		}
		
		((ObservableList<Staff>)staffs).add(new Staff(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), Integer.parseInt(fields[4].getText())));
	
	}


	@Override
	public void drawExisting(TableView<?> staffTable) {
        Staff selectedItem = (Staff) staffTable.getSelectionModel().getSelectedItem();
        staffTable.getItems().remove(selectedItem);
	}
	
}
