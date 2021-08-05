import javax.swing.JFrame;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Facility extends HospComponents {
	
	//data fields
	private String facility;
	private String atrArr[] = {"Facility"}; //attribute array  
    private int sizeArr[] = {200}; //size array
	
	
	//constructor
	public Facility() {
		
	}
	
	public Facility(String fac) {
		this.facility = fac;
	}
	

	//set methods
	public void setFacility(String fac) {
		this.facility = fac;
	}
	
	
	//get methods
	public String getFacility() {
		return facility;
	}
	
	
	@Override
	public TextField[] getParas() {	
		//textfields
        TextField addFac = new TextField();        
        TextField[] tfArr = {addFac}; //textfield array       
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
	public void getNew(TextField[] fields, ObservableList<?> facilities, JFrame al) {
		((ObservableList<Facility>)facilities).add(new Facility(fields[0].getText()));
	
	}
	
	
	@Override
	public void drawExisting(TableView<?> facilityTable) {
        Facility selectedItem = (Facility) facilityTable.getSelectionModel().getSelectedItem();
        facilityTable.getItems().remove(selectedItem);
	}
}
