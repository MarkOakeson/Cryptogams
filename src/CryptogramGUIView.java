/**
* @author Mark Oakeson
* FILE: CryptogramController.java
* @version ASSIGNMENT: Project 4 - Cryptography
* COURSE: CSc 335; Fall 2020
* PURPOSE: The purpose of this file is to be the graphic user interface for the Cryptography project.
* It connects the the user's view and the actual code for controlling the game.  Performs basic
* functions for the user such as: showing a list of guesses with how frequently a letter is used, automatically 
* inserting a correct guess into the interface, and starting a new game.
* 
*
* USAGE: 
* CryptogramModel.java - The actual code/functions that control the code aspects of the game, used
* 	through the functions implemented below
* 
* Cryptograms.java - The main function that runs the game, uses this file as the interface to call
* 	the functions in CryptogramController
*/
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
	private ArrayList<VBox> letterList;
	private ArrayList<TextField> tFieldList;
	private ArrayList<Label> labelList;
	private String quote;
	private Button hintButton;
	private CheckBox checkBox;
	
	
	public CryptogramGUIView() {
		this.control = new CryptogramController(new CryptogramModel());
		this.letterList = new ArrayList<VBox>();
		this.labelList = new ArrayList<Label>();
		this.tFieldList = new ArrayList<TextField>();
		this.quote = control.getEncryptedQuote();
		this.hintButton = new Button();
		this.checkBox = new CheckBox();
		getLetters(quote);
		control.addObserver(this);
	}
	
	
	/**
	 * Method starts the graphical user interface for the user
	 * @param args String array, launches the initial args to run JavaFx and the GUI
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	
	/**
	 * Method is the main loop for running the Graphic user interface
	 * @param primaryStage a Stage that is the primary stage for the graphics interface
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane mainPane = new BorderPane();
		GridPane rightPane = new GridPane();
	
		setNewPuzzle(rightPane, primaryStage);
		GridPane cryptoTextPane = setCryptogramText(primaryStage);
		
		setHint(rightPane);
		mainPane.setCenter(cryptoTextPane);
		mainPane.setRight(rightPane);
		
		Scene scene = new Scene(mainPane, 900, 400);
		primaryStage.setTitle("FX Test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Method Sets the Hint section of the interface.  Implements a button to display the list
	 * of how frequently a letter is used in the quote.  Also implements a button that is used to 
	 * automatically insert a correct letter mapping into the interface
	 * @param rightSide A GirdPlane that is used to set the right side of the interface
	 */
	private void setHint(GridPane rightSide) {
		//Button hintButton = new Button();
		hintButton.setText("Hint");
		rightSide.add(hintButton, 0, 1);
		checkBox.setText("Show Hints");
		rightSide.add(checkBox, 0, 2);
		GridPane frequency = new GridPane();
		String freq = control.getFreq();
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
	
	
	/**
	 * Method creates a new Puzzle via the lambda function.  Inserts a "New Game" button 
	 * on the right side of the canvas, and if the button is clicked, the game completely resets.
	 * @param rightSide A GridPane used to set the right side of the stage for the GUI
	 * @param primaryStage A Stage that is the main stage for the interface
	 */
	public void setNewPuzzle(GridPane rightSide, Stage primaryStage) {
		Button newPuzzle = new Button();
		newPuzzle.setText("New Puzzle");
		rightSide.add(newPuzzle, 0, 0);
		GridPane g = null;
		newPuzzle.setOnAction(event -> {
			this.control = new CryptogramController(new CryptogramModel());
			this.letterList = new ArrayList<VBox>();
			this.labelList = new ArrayList<Label>();
			this.tFieldList = new ArrayList<TextField>();
			this.quote = control.getEncryptedQuote();
			control.addObserver(this);
			getLetters(quote);
			
			BorderPane newMain = new BorderPane();
			GridPane newRightPane = new GridPane();
			Button anotherPuzzle = new Button();
			anotherPuzzle.setText("New Puzzle");
			newRightPane.add(newPuzzle, 0, 0);
			GridPane newcryptoTextPane = setCryptogramText(primaryStage);
			setHint(newRightPane);
			newMain.setCenter(newcryptoTextPane);
			newMain.setRight(newRightPane);
			primaryStage.setTitle("Yeehaw");
			Scene newscene = new Scene(newMain, 900, 400);
			primaryStage.setScene(newscene);
			primaryStage.show();
			
		});
	}
	
	/**
	 * Method creates three ArrayLists of the quotes length for the Labels, Letters, and
	 * TextFields in the interface
	 * @param quote A String that is the encrypted quote used in the game
	 */
	private void getLetters(String quote) {
		for(int i = 0; i < quote.length();i++) {
			letterList.add(new VBox());
			labelList.add(new Label());
			tFieldList.add(new TextField());
		}
	}
	
	/**
	 * Method sets the stage with the encrypted quote and textfield's for the user to 
	 * input a character.  If the character at a position is punctuation, then the method sets 
	 * those fields to disabled to not allow the user to change them.  The user inputs a letter and only 
	 * one character will show for every mapping the user requested.  Each time a letter is entered, 
	 * the game will update all other textfields that have the letter the user wants to repalce 
	 * and will checks to see if the game is over.  If the game is over, then a new stage will pop up and
	 * disable the current TextFields, and will not do anything until the user presses new game either 
	 * on the new stage or on the main stage.
	 * @param primaryStage A Stage that is the main stage for the interface
	 * @return  A GridPane that sets the interface with the Cryptogram guess area/ encrypted quote
	 */
	public GridPane setCryptogramText(Stage primaryStage) {
		GridPane gridPane = new GridPane();
		HashSet<String> puncSet = new HashSet<String>();
		puncSet.add(" ");
		puncSet.add("!");
		puncSet.add("-");
		puncSet.add(".");
		puncSet.add("\'");
		int row = 0;
		for(int i = 0; i < letterList.size(); i++) {
			VBox vbox = letterList.get(i);
			TextField text = tFieldList.get(i);
			String letter = Character.toString(quote.charAt(i));
			Label label = labelList.get(i);
			label.setText(letter);
			if(puncSet.contains(letter)) {
				text.setText(letter);
				text.setDisable(true);
			}

			text.setOnKeyReleased(event -> {
				
				String l = event.getCode().toString();
				String[] arr = new String[2];
				arr[0] = label.getText();
				arr[1] = l;
				control.makeReplacement(label.getText(), l);
				event.consume();
			});
			
			vbox.getChildren().add(text);
			vbox.getChildren().add(label);
			vbox.setAlignment(Pos.BOTTOM_CENTER);
			gridPane.add(vbox, i%30, row);
			if(i%30 == 29) {
				row++;
			}
		}
		return gridPane;
	}
	
	
	
	/**
	 * Method overrides the Observer Interface to update the GUI.  Each time a letter is entered, 
	 * the game will update all other TextField's that have the letter the user wants to replace 
	 * and will checks to see if the game is over.  If the game is over, then a new stage will pop up and
	 * disable the current TextFields, and will not do anything until the user presses new game on the main stage.
	 * @param o An Observable Object to be updated
	 * @param arg An Object that was used as a String array to update the textFields 
	 * with the user's requested replacement
	 */
	@Override
	public void update(Observable o, Object arg) {
		String[] guess = (String[]) arg;
		for(int i = 0; i < tFieldList.size();i++) {
			if(labelList.get(i).getText().equals(guess[0])) {
				tFieldList.get(i).setText(guess[1]);
			}
		}
		if(control.isGameOver()) {
			for(int x  = 0; x < tFieldList.size(); x++) {
				tFieldList.get(x).setDisable(true);
			}
			checkBox.setDisable(true);
			hintButton.setDisable(true);
			Stage winStage = new Stage();
			winStage.setTitle("You Won!");
			Button newGame = new Button();
			newGame.setText("OK");
			newGame.setOnMouseClicked(event -> {
				winStage.close();
			});
			BorderPane pane = new BorderPane();
			pane.setCenter(newGame);
			Scene scene = new Scene(pane, 900, 400);
			winStage.setScene(scene);
			winStage.showAndWait();
			
		}
		
	}

	


}
