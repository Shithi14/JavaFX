package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;



public class pp extends Application{
	@Override
	public void start(Stage primarystage) {
		
		Button bu=new Button ("OK");
		Scene sc=new Scene(bu,400,450);
		
		primarystage.setScene(sc);
		primarystage.setTitle("My javafx");
		primarystage.show();
		
		
		
		Stage st=new Stage();
		Button b=new Button("My stage");
		Scene s=new Scene(b,400,450);
		st.setScene(s);
		st.setTitle("Second Stage");
		st.show();
		st.setResizable(false);		
		
	}
	
	public static void main(String args[]) {
		launch(args);
		
	}
	
}