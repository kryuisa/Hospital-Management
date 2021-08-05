import javax.swing.JFrame;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public abstract class HospComponents {

	public abstract String[] getatrArr();								//gets attributes
	public abstract int[] getsizeArr();									//gets textfield size
	public abstract TextField[] getParas();								//get textfields
	public abstract void drawExisting(TableView<?> table);				//draws an existing entry
	public abstract void getNew(TextField[] fields,  ObservableList<?> obl, JFrame al);	 //gets new input

}
