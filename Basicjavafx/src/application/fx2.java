//circle
package application;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


public class fx2 extends Application{
	public void start (Stage primarystage) {
		Circle ci=new Circle();
		ci.setCenterX(100);
		ci.setCenterY(100);
		ci.setRadius(50);
		ci.setStroke(Color.GREEN);
		ci.setFill(Color.BLUE);
		Pane pa=new Pane(ci);
		Scene sc=new Scene(pa,200,200);
		primarystage.setTitle("ShowCircle");
		primarystage.setScene(sc);
		primarystage.show();
		
	}
	public static void main(String args[]) {
		launch(args);
	}
}
