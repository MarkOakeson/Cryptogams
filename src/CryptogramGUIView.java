import java.util.ArrayList;
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
	private ArrayList<VBox> letters;
	private ArrayList<TextField> tFieldList;
	private ArrayList<Label> labels;
	private String quote;
	public CryptogramGUIView() {
		CryptogramController control1 = new CryptogramController(new CryptogramModel());
		this.control = control1;
		this.letters = new ArrayList<VBox>();
		this.labels = new ArrayList<Label>();
		this.tFieldList = new ArrayList<TextField>();
		this.quote = control.getEncryptedQuote();
		getLetters(quote);
		control.addObserver(this);
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
		setNewPuzzle(rightSide);
	
		GridPane cryptoText = setCryptogramText();
		
		
		
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
		hintButton.setOnAction(event -> {
			String[] arr = control.giveHint();
			control.makeReplacement(arr[0], arr[1]);
		});
		
	}
	
	public void setNewPuzzle(GridPane rightSide) {
		Button newPuzzle = new Button();
		newPuzzle.setText("New Puzzle");
		rightSide.add(newPuzzle, 0, 0);
		GridPane g = null;
		newPuzzle.setOnAction(event -> {
			System.err.println(control.getUsersProgress());
			this.control = new CryptogramController(new CryptogramModel());
			this.letters = new ArrayList<VBox>();
			this.labels = new ArrayList<Label>();
			this.tFieldList = new ArrayList<TextField>();
			this.quote = control.getEncryptedQuote();
			getLetters(quote);
		});
	}
	
	private void getLetters(String quote) {
		for(int i = 0; i < quote.length();i++) {
			letters.add(new VBox());
			labels.add(new Label());
			tFieldList.add(new TextField());
		}
	}
	public GridPane setCryptogramText() {
		GridPane gridPane = new GridPane();
		HashSet<String> puncSet = new HashSet<String>();
		puncSet.add(" ");
		puncSet.add("-");
		puncSet.add(".");
		puncSet.add("\'");
		int row = 0;
		for(int i = 0; i < letters.size(); i++) {
			VBox vbox = letters.get(i);
			TextField text = tFieldList.get(i);
			String letter = Character.toString(quote.charAt(i));
			Label label = labels.get(i);
			label.setText(letter);
			if(puncSet.contains(letter)) {
				text.setText(letter);
				text.setDisable(true);
			}

			text.setOnKeyReleased(event -> {
				
				String l = event.getCode().toString();
				System.out.println(l);
				String[] arr = new String[2];
				arr[0] = label.getText();
				arr[1] = l;
				
				control.makeReplacement(label.getText(), l);
				event.consume();
			});
			
			vbox.getChildren().add(text);
			vbox.getChildren().add(label);
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			System.err.println(letters);
			gridPane.add(vbox, i%30, row);
			if(i%30 == 29) {
				row++;
			}
		}
		return gridPane;
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		//String[] arr = (String[]) arg;
		String[] hehe = (String[]) arg;
		System.out.println("UPDATE");
		System.out.println(hehe[0]);
		System.out.println(hehe[1]);
		for(int i = 0; i < tFieldList.size();i++) {
			System.out.println(labels.get(i).getText());
			if(labels.get(i).getText().equals(hehe[0])) {
				System.out.println("HEHE");
				tFieldList.get(i).setText(hehe[1]);
			}
		}
		//setCryptogramText();
	}

	


}
