package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;

public  class evenhandelar extends Application{
	@Override
	public void start(Stage primarystage) {
		
		VBox box=new VBox(50);
		
		
		box.setAlignment(Pos.CENTER);
		Button b1=new Button("1");
		Button b2=new Button ("2");
		Button b3=new Button("3");
		
		
		b1.setPrefWidth(100);
		b2.setPrefWidth(100);
		b3.setPrefWidth(100);
		
		b1.setStyle("-fx-background-color:green;");
		
		
		
		b1.setOnAction(e ->{
			System.out.println("Hi Shithi");
		});
		b2.setOnAction(e ->{
			System.out.println("Gobindhagonj");
		});
		b3.setOnAction(e ->{
			System.out.println("Gaibandha");
		});
		
		
		box.getChildren().addAll(b1,b2,b3);
		
		Scene sc=new Scene(box,400,450);
		
		
		primarystage.setTitle("Set on Action");
		primarystage.setScene(sc);
		primarystage.show();
		
	}
	public static void main(String args[]) {
		launch(args);
		
	}
	
}

