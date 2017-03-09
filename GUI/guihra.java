package GUI;

import Mesta.*;
import hra.Turn;
import javafx.application.*;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.scene.Scene;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;


public class guihra extends Application {
	private Button turn = new Button("Turn");
	private Button vyprava = new Button("Poslat vypravu");
	private TextArea vypis = new TextArea();
	private ScrollPane skrolVypis = new ScrollPane(vypis);
	private TextField start = new TextField();
	private TextField ciel = new TextField();
	private TextField typTovaru = new TextField();
	private Label startOzn = new Label("Start");
	private Label cielOzn = new Label("Ciel");
	private Label typTovaruOzn = new Label("TypTovaru");

	@Override
	public void start(Stage hlavneOkno) {
		hlavneOkno.setTitle("Semitas et Civitas");
		Mesto[] Mesta= Turn.Nastav();
		FlowPane pane = new FlowPane();
		
		pane.getChildren().add(turn);
		pane.getChildren().add(vyprava);
		pane.getChildren().add(skrolVypis);
		pane.getChildren().add(startOzn);
		pane.getChildren().add(start);
		pane.getChildren().add(cielOzn);
		pane.getChildren().add(ciel);
		pane.getChildren().add(typTovaruOzn);
		pane.getChildren().add(typTovaru);

		
		turn.setText("Nove kolo");
		
		turn.setOnAction(e -> { // lambda výraz s odvodením typu z kontextu
			vypis.clear();
			vypis.appendText(Turn.Kolo(Mesta) + ". Kolo\n"
							+ Mesta[0].getPeniaze() + " zlatych\n" 
							+ Turn.vypis(Mesta));
			}
		);
		
		hlavneOkno.setScene(new Scene(pane, 500, 300));
		hlavneOkno.show();
	}
}
