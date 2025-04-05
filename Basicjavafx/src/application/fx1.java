package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;


public class fx1 extends Application{
	@Override
	public void  start(Stage primarystage) {
		Button bu=new Button("HI");
		StackPane layout=new StackPane();
		layout.getChildren().add(bu);
		Scene myname=new Scene (layout,400,450);
		
		primarystage.setScene(myname);
		primarystage.setTitle("First project");
		primarystage.show();
		
		Stage stage=new Stage();
		Button but=new Button("Shithi");
		StackPane lay=new StackPane(but);
		Scene sc=new Scene(lay,400,450);
		stage.setTitle("Second project");
		stage.setScene(sc);
		stage.show();
		stage.setResizable(false);
		
		
		
	}
	
	
	public static void main(String args[]) {
		launch(args);
		
	}
}