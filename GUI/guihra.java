package GUI;

import hra.Turn;
import javafx.application.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class guihra extends Application {
	private Button turn = new Button("Turn");
	private TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);

	@Override
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Stret");
		
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(turn);
		pane.getChildren().add(skrolVypis);
		
		turn.setText("Turn");
		
		turn.setOnAction(e -> { // lambda výraz s odvodením typu z kontextu
			vypis.appendText(Turn.Nastav());
			}
		);
		
		hlavneOkno.setScene(new Scene(pane, 400, 250));
		hlavneOkno.show();
	}
	public static void main(String[] args) {		
	launch(args);
	}
}
