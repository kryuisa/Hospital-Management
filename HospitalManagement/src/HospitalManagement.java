
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

 
public class HospitalManagement extends Application {
 

    public static void main(String[] args) {
        launch(args);
    }
 
    
	@Override
    public void start(Stage primaryStage) {
		
    	//initialize arrays
    	 
    	//doctor array values
    	ObservableList<Doctor> doctors = FXCollections.observableArrayList(
    			new Doctor("195", "Dr.Alisa Yap Yi Hui", "Surgeon", "8-11AM", "MBSS", 11),
    			new Doctor("002", "Dr.John Lim", "Physician", "10-3AM", "MBSS,MS", 45),
    			new Doctor("003", "Dr.Amy Chia", "Surgeon", "7-11AM", "MBSS,MD", 85),
    			new Doctor("004", "Dr.Jude Mathis", "Orthopaedician", "2-9PM", "MBSS", 20),
    			new Doctor("005", "Dr.Wai Tan", "Neurologist", "3-10PM", "MBSS,MS", 55)
		);
	
		//patients array values
    	ObservableList<Patient> patients = FXCollections.observableArrayList(
    			new Patient("195", "Alisa Yap Yi Hui", "Influenza", "F", "Y", 20),
    			new Patient("001", "Koah Wong", "Diabetes", "M", "Y", 45),
    			new Patient("002", "Lily Cheah", "Stroke", "F", "N", 39),
    			new Patient("003", "Ahmad Singh", "Pneumonia", "M", "N", 12),
    			new Patient("004", "Alvin Brown", "Cancer", "M", "Y", 67)
		);
		
		//medicals
    	ObservableList<Medical> medicals = FXCollections.observableArrayList(
    			new Medical("Acetaminophen", "Claspia Pharmacy", "01/09/2022", 70, 350),
    			new Medical("Metformin", "West Pharmaceutical", "20/12/2025", 110, 50),
    			new Medical("Methadone", "Bioassay", "20/12/2020", 70, 770),
    			new Medical("Ibuprofen", "Polpharma", "31/08/2025", 65, 90),
    			new Medical("Doxycycline", "West Pharmaceutical", "06/03/2027", 200, 350)
		);
	
		//laboratories array values
    	ObservableList<Lab> laboratories = FXCollections.observableArrayList(
    			new Lab("Microbiology Lab", 1300000),
    			new Lab("Parasitology Lab", 950000),
    			new Lab("Virology Lab", 500000),
    			new Lab("Hematology Lab", 750000),
    			new Lab("Coagulation Lab", 2200000)
		);
	
		//facilities array values
    	ObservableList<Facility> facilities = FXCollections.observableArrayList(
    			new Facility("Pharmacy"),
    			new Facility("Wards"),
    			new Facility("Canteen"),
    			new Facility("Pastoral Care"),
    			new Facility("Parking")
		);
    	
    	//staff array values
    	ObservableList<Staff> staffs = FXCollections.observableArrayList(
        		new Staff("195", "Alisa Yap Yi Hui", "Intern", "F", 38000),
        		new Staff("001", "Milla Miller", "Receptionist", "F", 42000),
        		new Staff("002", "Rowen Lim", "Security", "M", 36000),
        		new Staff("003", "Mike Tan", "Nurse", "M", 38000),
        		new Staff("004", "Ali Muhammad", "Receptionist", "M", 42000)
    	);
 
    	
    	Staff staff = new Staff();
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		Medical medical = new Medical();
		Lab lab = new Lab();
		Facility facility = new Facility();
		

    	//CREATE BORDERPANE
        BorderPane borderPane = new BorderPane();
  		borderPane.setStyle("-fx-border-color:black");
          
  		Scene scene = new Scene(borderPane, 400, 250);
        primaryStage.setTitle("Welcome to the HMS");
        primaryStage.setWidth(1200);
        primaryStage.setHeight(750);
        

        //displays title
      	Text title= new Text("Hospital Management System");
      	title.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 24));
      	title.setFill(Color.BLUE);
      	borderPane.setTop(title);
      	
      	
      	//displays current date and time
        Label curTime = new Label();
        curTime.setFont(Font.font("Courier", FontWeight.BOLD, FontPosture.REGULAR, 12));
        curTime.setTextFill(Color.LIGHTSEAGREEN);
        
        Thread timerThread = new Thread(() -> {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("'Date:' dd MMM yyyy '\t\t\tTime:' hh:mm:ss a");
            while (true) {
                try {
                    Thread.sleep(1000);
                } 
                
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                final String time = simpleDateFormat.format(new Date());
                Platform.runLater(() -> {
                    curTime.setText(time);
                });
            }
        });   
        
        timerThread.start();
      	
        //add header, set styles
      	VBox header = new VBox();  			
      	header.getChildren().add(title);
      	header.getChildren().add(curTime);
      	header.setStyle("-fx-background-color: #acf8a6");
      	header.setAlignment(Pos.CENTER);
      	header.setPrefHeight(70);
      	borderPane.setTop(header);

	    final Map<String, VBox> menuOptions;
	    
        menuOptions = new LinkedHashMap<>();  
        menuOptions.put("*Welcome*", welcPane());
        menuOptions.put("Doctors", newPane(doctors, doctor, "Doctors", constructDoctorTable(doctors, doctor)));
        menuOptions.put("Patients", newPane(patients, patient, "Patients", constructPatientTable(patients, patient)));
        menuOptions.put("Medical", newPane(medicals, medical, "Medicals", constructMedicalTable(medicals, medical)));
        menuOptions.put("Lab", newPane(laboratories, lab, "Laboratories", constructLabTable(laboratories, lab)));
        menuOptions.put("Facility", newPane(facilities, facility, "Facilities", constructFacilityTable(facilities, facility)));
        menuOptions.put("Staff", newPane(staffs, staff, "Staff", constructStaffTable(staffs, staff)));
        
        borderPane.setCenter(welcPane());
        
        VBox nav = new VBox();

        for (String userOption : menuOptions.keySet()) {
            Button button = new Button(userOption);
            button.setPrefSize(120, 30);
            button.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    borderPane.setCenter(menuOptions.get(userOption));
                }
            });
            nav.getChildren().add(button);
        }

        borderPane.setLeft(nav);      
        nav.setPrefWidth(120);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }

		

	public void catLabel(Label la) {
		la.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20));
	}
		
	
	public VBox newPane(ObservableList<?> obl, HospComponents hc, String cat, TableView<?> tbv) {
		
		VBox vb = new VBox();
		
		Label stflabel = new Label(cat); 
        catLabel(stflabel);
        
        
        vb.getChildren().addAll(stflabel, tbv, addRow(hc, obl, tbv));
        vb.setStyle("-fx-background-color: #ededed");
        vb.setPadding(new Insets(10, 10, 10, 10));
        return vb;
	}
	
	
	public VBox welcPane() {
		
		VBox vb = new VBox();
		
		Label welcome = new Label("Welcome to the Hospital Management System!");
        welcome.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 28));
        welcome.setTextFill(Color.GREY);
        
        Label text = new Label("Click on a button on the left to begin.");
        text.setFont(Font.font("Courier", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        text.setTextFill(Color.GREY);
        
       	vb.setAlignment(Pos.CENTER);
       	vb.setStyle("-fx-background-color: #ededed");
		vb.getChildren().addAll(welcome, text);
		return vb;
	}
	
	
	public HBox addRow(HospComponents hc, ObservableList<?> obl, TableView<?> tbv) {
		
		HBox hb = new HBox();
		
		TextField[] fields = hc.getParas();
		
        for (int i = 0; i < fields.length; i++) {
        	fields[i].setPrefWidth(hc.getsizeArr()[i]);
        	fields[i].setPromptText(hc.getatrArr()[i]);
        	hb.getChildren().add(fields[i]);
        }
        
        //add buttons
        Button addButton = new Button("Add");
        Button drawButton = new Button("Delete");        
        hb.getChildren().addAll(addButton, drawButton);
    	
        addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
            public void handle(ActionEvent e) {
            	
                JFrame al = new JFrame();
            	for (int i = 0; i < fields.length; i++) {
            		if(fields[i].getText().isEmpty()) {
            			JOptionPane.showMessageDialog(al,"Some feilds are empty!","Alert",JOptionPane.WARNING_MESSAGE);  
            			return;	
            		}
            	}

            	///have to specify new item keep where
            	hc.getNew(fields, obl, al);
            	
                for (int i = 0; i < fields.length; i++) {
                	fields[i].clear();
                }
        	}          
        });
        
        drawButton.setOnAction(e -> { 	
        	int confirm = JOptionPane.showConfirmDialog(null, "Delete this Entry?", "Delete", JOptionPane.YES_NO_OPTION);
        	if (confirm == JOptionPane.YES_OPTION) {
        		hc.drawExisting(tbv);
        	} 
        });
        
		return hb;
	}
    
	
	public TableView<Staff> constructStaffTable(ObservableList<Staff> staffs, Staff staff) {
		
		TableView<Staff> staffTable = new TableView<Staff>();
		int []sizeArr = staff.getsizeArr();
		
		TableColumn<Staff, String> idCol = new TableColumn<>("Id");
        idCol.setMinWidth(sizeArr[0]);
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
 
        TableColumn<Staff, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(sizeArr[1]);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
 
        TableColumn<Staff, String> designationCol = new TableColumn<>("Designation");
        designationCol.setMinWidth(sizeArr[2]);
        designationCol.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        
        TableColumn<Staff, String> sexCol = new TableColumn<>("Sex");
        sexCol.setMinWidth(sizeArr[3]);
        sexCol.setCellValueFactory(new PropertyValueFactory<>("Sex"));
 
        TableColumn<Staff, String> salCol = new TableColumn<>("Salary");
        salCol.setMinWidth(sizeArr[4]);
        salCol.setCellValueFactory(new PropertyValueFactory<>("Salary"));

        //get columns
        staffTable.getColumns().add(idCol);
        staffTable.getColumns().add(nameCol);
        staffTable.getColumns().add(designationCol);
        staffTable.getColumns().add(sexCol);
        staffTable.getColumns().add(salCol);	
        
        staffTable.setItems((ObservableList<Staff>) staffs);
        
        return (TableView<Staff>)staffTable;
	}
	
	
	public TableView<Patient> constructPatientTable(ObservableList<Patient> patients, Patient patient) {
		
		TableView<Patient> patientTable = new TableView<Patient>();
		int []sizeArr = patient.getsizeArr();
		
		TableColumn<Patient, String> idCol = new TableColumn<>("Id");
	    idCol.setMinWidth(sizeArr[0]);
	    idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));

	    TableColumn<Patient, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(sizeArr[1]);
	    nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

	    TableColumn<Patient, String> diseaseCol = new TableColumn<>("Disease");
	    diseaseCol.setMinWidth(sizeArr[2]);
	    diseaseCol.setCellValueFactory(new PropertyValueFactory<>("Disease"));
	    
	    TableColumn<Patient, String> admitCol = new TableColumn<>("AdmitStatus");
	    admitCol.setMinWidth(sizeArr[3]);
	    admitCol.setCellValueFactory(new PropertyValueFactory<>("AdmitStatus"));

	    TableColumn<Patient, String> sexCol = new TableColumn<>("Sex");
	    sexCol.setMinWidth(sizeArr[4]);
	    sexCol.setCellValueFactory(new PropertyValueFactory<>("Sex"));

	    TableColumn<Patient, String> ageCol = new TableColumn<>("Age");
	    ageCol.setMinWidth(sizeArr[5]);
	    ageCol.setCellValueFactory(new PropertyValueFactory<>("Age"));
	    
	    
	    //get columns
	    patientTable.getColumns().add(idCol);
	    patientTable.getColumns().add(nameCol);
	    patientTable.getColumns().add(diseaseCol);
	    patientTable.getColumns().add(sexCol);
	    patientTable.getColumns().add(admitCol);
	    patientTable.getColumns().add(ageCol);
		
	    patientTable.setItems((ObservableList<Patient>) patients);
        
        return (TableView<Patient>)patientTable;		
	}
	
	
	public TableView<Lab> constructLabTable(ObservableList<Lab> laboratory, Lab lab) {
		
		TableView<Lab> labTable = new TableView<Lab>();
		
		TableColumn<Lab, String> labCol = new TableColumn<>("Laboratory");
		labCol.setMinWidth(200);
		labCol.setCellValueFactory(new PropertyValueFactory<>("Laboratory"));
		TableColumn<Lab, String> labCost = new TableColumn<>("Cost");
		labCost.setMinWidth(100);
		labCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));    
		    
		
		//get columns
		labTable.getColumns().add(labCol);
		labTable.getColumns().add(labCost);
			
		labTable.setItems((ObservableList<Lab>) laboratory);
	        
	    return (TableView<Lab>)labTable;
			
	}
	
	
	public TableView<Medical> constructMedicalTable(ObservableList<Medical> medicals, Medical medical) {
		
		TableView<Medical> medicalTable = new TableView<Medical>();
		int []sizeArr = medical.getsizeArr();
		
		TableColumn<Medical, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(sizeArr[0]);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));
 
        TableColumn<Medical, String> manuCol = new TableColumn<>("Manufacturer");
        manuCol.setMinWidth(sizeArr[1]);
        manuCol.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
 
        TableColumn<Medical, String> expiryCol = new TableColumn<>("ExpiryDate");
        expiryCol.setMinWidth(sizeArr[2]);
        expiryCol.setCellValueFactory(new PropertyValueFactory<>("ExpiryDate"));
        
        TableColumn<Medical, String> costCol = new TableColumn<>("Cost");
        costCol.setMinWidth(sizeArr[3]);
        costCol.setCellValueFactory(new PropertyValueFactory<>("Cost"));
 
        TableColumn<Medical, String> countCol = new TableColumn<>("Count");
        countCol.setMinWidth(sizeArr[4]);
        countCol.setCellValueFactory(new PropertyValueFactory<>("Count"));

        //get columns
        medicalTable.getColumns().add(nameCol);
        medicalTable.getColumns().add(manuCol);
        medicalTable.getColumns().add(expiryCol);
        medicalTable.getColumns().add(costCol);
        medicalTable.getColumns().add(countCol);	
        
        medicalTable.setItems((ObservableList<Medical>) medicals);
        
        return (TableView<Medical>)medicalTable;
	}


	public TableView<Facility> constructFacilityTable(ObservableList<Facility> facilities, Facility facility) {
		
		TableView<Facility> facilityTable = new TableView<Facility>();
		int []sizeArr = facility.getsizeArr();
		
		TableColumn<Facility, String> facCol = new TableColumn<>("Facility");
		facCol.setMinWidth(sizeArr[0]);
		facCol.setCellValueFactory(new PropertyValueFactory<>("Facility"));
		    		    
		//get columns
		facilityTable.getColumns().add(facCol);		
		facilityTable.setItems((ObservableList<Facility>) facilities);
	        
	    return (TableView<Facility>)facilityTable;
	    
	}


	public TableView<Doctor> constructDoctorTable(ObservableList<Doctor> doctors, Doctor doctor) {
		
		TableView<Doctor> doctorTable = new TableView<Doctor>();
		int []sizeArr = doctor.getsizeArr();
		
		TableColumn<Doctor, String> idCol = new TableColumn<>("Id");
	    idCol.setMinWidth(sizeArr[0]);
	    idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));

	    TableColumn<Doctor, String> nameCol = new TableColumn<>("Name");
	    nameCol.setMinWidth(sizeArr[1]);
	    nameCol.setCellValueFactory(new PropertyValueFactory<>("Name"));

	    TableColumn<Doctor, String> specialistCol = new TableColumn<>("Specialist");
	    specialistCol.setMinWidth(sizeArr[2]);
	    specialistCol.setCellValueFactory(new PropertyValueFactory<>("Specialist"));
	    
	    TableColumn<Doctor, String> qualificationCol = new TableColumn<>("Qualification");
	    qualificationCol.setMinWidth(sizeArr[3]);
	    qualificationCol.setCellValueFactory(new PropertyValueFactory<>("Qualification"));

	    TableColumn<Doctor, String> worktimeCol = new TableColumn<>("WorkTime");
	    worktimeCol.setMinWidth(sizeArr[4]);
	    worktimeCol.setCellValueFactory(new PropertyValueFactory<>("WorkTime"));

	    TableColumn<Doctor, String> roomCol = new TableColumn<>("Room");
	    roomCol.setMinWidth(sizeArr[5]);
	    roomCol.setCellValueFactory(new PropertyValueFactory<>("Room"));
	    
	    
	    //get columns
	    doctorTable.getColumns().add(idCol);
	    doctorTable.getColumns().add(nameCol);
	    doctorTable.getColumns().add(specialistCol);
	    doctorTable.getColumns().add(qualificationCol);
	    doctorTable.getColumns().add(worktimeCol);
	    doctorTable.getColumns().add(roomCol);
		
	    doctorTable.setItems((ObservableList<Doctor>) doctors);
        
        return (TableView<Doctor>)doctorTable;
	}


	
	
}
	
	
	
	
	
	
	