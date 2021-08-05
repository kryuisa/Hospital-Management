import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Doctor extends Person {

	//data fields
	private String specialist;
	private String workTime;
	private String qualification;
	private int room;
    private String atrArr[] = {"Id", "Name", "Specialist", "Qualifications", "WorkTime", "Room"}; //attribute array  
    private int sizeArr[] = {80, 150, 200, 100, 100, 80}; //size array
	
	//constructor
	public Doctor() {
		
	}
	
	public Doctor(String id, String name, String spec, String qual, String wtime, int room) {
		super(id, name);
		this.specialist = spec;
		this.workTime = wtime;
		this.qualification = qual;
		this.room = room;
	}

	
	//set methods
	public void setSpecialist(String spec) {
		this.specialist = spec;
	}
		
	public void setWorkTime(String wtime) {
		this.workTime = wtime;
	}
		
	public void setQualification(String qual) {
		this.qualification = qual;
	}
		
	public void setRoom(int room) {
		this.room = room;
	}
		
		
	//get methods
	public String getSpecialist() {
		return specialist;
	}
		
	public String getWorkTime() {
		return workTime;
	}
		
	public String getQualification() {
		return qualification;
	}
	
	public int getRoom() {
		return room;
	}
	
	
	@Override
	public TextField[] getParas() {	
		//textfields
        TextField addId = new TextField();
        TextField addName = new TextField();
        TextField addSpec = new TextField();
        TextField addQual = new TextField();
        TextField addWTime = new TextField();
        TextField addRoom = new TextField();
        
        TextField[] tfArr = {addId, addName, addSpec, addQual, addWTime, addRoom}; //textfield array       
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
	public void getNew(TextField[] fields, ObservableList<?> doctors, JFrame al) {
		
		try {
			Integer.parseInt(fields[5].getText());
		}
			
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(al,"Invalid Room input!","Alert",JOptionPane.ERROR_MESSAGE);  
			return;
		}
		
		((ObservableList<Doctor>)doctors).add(new Doctor(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(), Integer.parseInt(fields[5].getText())));
	
	}
	
	
	@Override
	public void drawExisting(TableView<?> doctorTable) {
        Doctor selectedItem = (Doctor) doctorTable.getSelectionModel().getSelectedItem();
        doctorTable.getItems().remove(selectedItem);
	}
	
}
