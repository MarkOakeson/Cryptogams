import java.util.Arrays;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class CryptogramGUIView extends Application implements Observer {
	private static CryptogramController control;
	public CryptogramGUIView() {
		this.control = new CryptogramController(new CryptogramModel());
	}
	public static void main(String[] args) {
		System.out.println("MAIN");
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("Start");
		BorderPane main = new BorderPane();
		GridPane rightSide = new GridPane();

		GridPane cryptoText = setCryptogramText();
	
		setNewPuzzle(rightSide);
		setHint(rightSide);
		
		main.setCenter(cryptoText);
		main.setRight(rightSide);
		
		Scene scene = new Scene(main, 900, 400);
		primaryStage.setTitle("FX Test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	
	private void setHint(GridPane rightSide) {
		Button hintButton = new Button();
		hintButton.setText("Hint");
		rightSide.add(hintButton, 0, 1);
		CheckBox checkBox = new CheckBox("Show Hints");
		rightSide.add(checkBox, 0, 2);
		GridPane frequency = new GridPane();
		String freq = control.getFreq();
		System.out.println(freq);
		int col = 0;
		int row = 0;
		int letterIndex = 0;
		int countIndex = 3;
		int counter = 0;
		for( int i = 0; i < 26; i++) {
			String letter = freq.substring(letterIndex,letterIndex + 1) + "  ";
			letter += freq.substring(countIndex,countIndex+1);
			Label letterFreq = new Label(letter);
			frequency.add(letterFreq, col, row);
			letterIndex = letterIndex + 5;
			countIndex = countIndex + 5;
			counter++;
			if(counter == 7) {
				letterIndex = letterIndex + 1;
				countIndex = countIndex + 1;
				counter = 0;
			}
			row++;
			if(i == 12) {
				col = col + 3;
				row = 0;
			}
		}
		frequency.setHgap(10);
		frequency.setVisible(false);
		rightSide.add(frequency,  0,  3);
		checkBox.setOnAction((event) -> {
			if(checkBox.isSelected()) {
				frequency.setVisible(true);
			}
			else {
				frequency.setVisible(false);
			}
		});
		
	}
	
	private void setNewPuzzle(GridPane rightSide) {
		Button newPuzzle = new Button();
		newPuzzle.setText("New Puzzle");
		rightSide.add(newPuzzle, 0, 0);
	}
	
	
	public GridPane setCryptogramText() {
		GridPane gridPane = new GridPane();
		String test = control.getEncryptedQuote();
		HashSet<String> puncSet = new HashSet<String>();
		puncSet.add(" ");
		puncSet.add("-");
		puncSet.add(".");
		puncSet.add("\'");
		int row = 0;
		for(int i = 0; i < test.length(); i++) {
			VBox vbox = new VBox();
			TextField tField;
			String letter = Character.toString(test.charAt(i));
			Label label = new Label(letter);
			if(puncSet.contains(letter)) {
				tField = new TextField(letter);
				tField.setDisable(true);
			}
			else {
				tField = new TextField();
			}
			///////////////////////////////////////////////////////////////////////////////////////////////////
			tField.setOnKeyPressed(event -> {
				String l = event.getCode().toString();
				String[] arr = new String[2];
				arr[0] = label.getText();
				arr[1] = l;
				update(control, arr);
			});
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			vbox.getChildren().add(tField);
			vbox.getChildren().add(label);
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			gridPane.add(vbox, i%30, row);
			if(i%30 == 29) {
				row++;
			}
		}
		return gridPane;
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		String[] arr = (String[]) arg;
		String toRemove =  arr[0];
		String toReplace = arr[1];
		control.makeReplacement(toRemove, toReplace);
		
	}
	


}
