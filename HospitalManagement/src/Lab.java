import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Lab extends HospComponents {
	
	//data fields
	private String lab;
	private int cost;
	private String atrArr[] = {"Laboratory", "Cost"}; //attribute array  
    private int sizeArr[] = {200, 100}; //size array
    
	
	//constructor
	public Lab() {
		
	}
	
	public Lab(String lab, int cost) {
		this.lab = lab;
		this.cost = cost;
	}
	

	//set methods
	public void setLaboratory(String lab) {
		this.lab = lab;
	}
			
	public void setCost(int cost) {
		this.cost = cost;
	}
		
		
	//set methods
	public String getLaboratory() {
		return lab;
	}
			
	public int getCost() {
		return cost;
	}
		
		
	@Override
	public TextField[] getParas() {	
		//textfields
        TextField addLab = new TextField();
        TextField addCost = new TextField();
        
        TextField[] tfArr = {addLab, addCost}; //textfield array       
        return tfArr;
        
	}
	

	@Override
	public String[] getatrArr() {
		return atrArr;
	}

	
	@Override
	public int[] getsizeArr() {
		return sizeArr;
	}



	@SuppressWarnings("unchecked")
	@Override
	public void getNew(TextField[] fields, ObservableList<?> labs, JFrame al) {
		try {
			Integer.parseInt(fields[1].getText());
		}
			
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(al,"Invalid Cost input!","Alert",JOptionPane.ERROR_MESSAGE);  
			return;
		}
		
		((ObservableList<Lab>)labs).add(new Lab(fields[0].getText(), Integer.parseInt(fields[1].getText())));
	
	}
	
	
	@Override
	public void drawExisting(TableView<?> labTable) {
        Lab selectedItem = (Lab) labTable.getSelectionModel().getSelectedItem();
        labTable.getItems().remove(selectedItem);
	}
}
