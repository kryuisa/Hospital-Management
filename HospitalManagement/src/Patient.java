import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


public class Patient extends Person {

	private String disease;
	private String sex;
	private String admitStatus;
	private int age;
	private String atrArr[] = {"Id", "Name", "Disease", "Sex", "AdmitStatus", "Age"}; //attribute array  
    private int sizeArr[] = {80, 150, 250, 100, 100, 100}; //size array
    
	
	//constructor
	public Patient() {
		
	}
	
	public Patient(String id, String name, String dise, String sex, String adm, int age) {
		super(id, name);
		this.disease = dise;
		this.sex = sex;
		this.admitStatus = adm;
		this.age = age;
	}
	
	
	//set methods
	public void setDisease(String dise) {
		this.disease = dise;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public void setAdmitStatus(String adm) {
		this.admitStatus = adm;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	
	//get methods
	public String getDisease() {
		return disease;
	}
	
	public String getSex() {
		return sex;
	}
	public String getAdmitStatus() {
		return admitStatus;
	}
	
	public int getAge() {
		return age;
	}
	

	@Override
	public TextField[] getParas() {	
		//textfields
        TextField addId = new TextField();
        TextField addName = new TextField();
        TextField addDisease = new TextField();
        TextField addSex = new TextField();
        TextField addAdmit = new TextField();
        TextField addAge = new TextField();
        
        TextField[] tfArr = {addId, addName, addDisease, addSex, addAdmit, addAge}; //textfield array       
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
	public void getNew(TextField[] fields, ObservableList<?> patients, JFrame al) {
		
		try {
			Integer.parseInt(fields[5].getText());
		}
			
		catch(NumberFormatException ex) {
			JOptionPane.showMessageDialog(al,"Invalid Age input!","Alert",JOptionPane.ERROR_MESSAGE);  
			return;
		}
		
		((ObservableList<Patient>)patients).add(new Patient(fields[0].getText(), fields[1].getText(), fields[2].getText(), fields[3].getText(), fields[4].getText(), Integer.parseInt(fields[5].getText())));
	
	}

	@Override
	public void drawExisting(TableView<?> patientTable) {
        Patient selectedItem = (Patient) patientTable.getSelectionModel().getSelectedItem();
        patientTable.getItems().remove(selectedItem);
	}

	
}
