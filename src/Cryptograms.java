/**
* 
* @author Mark: Mark Oakeson
* FILE: Cryptograms.java
* @version ASSIGNMENT: Project 1 - Cryptography
* COURSE: CSc 335; Fall 2020
* Purpose PURPOSE: The purpose of this file is to be the main class that begins the game.  It asks the user
*what version (text or GUI) they want to play, then launches the game with the requested interface
* 
*
* @usage: 
* CryptogramModel.java - The actual code/functions that control the code aspects of the game
* 
* CryptogramController.java - The interface for controlling the actual code for the game
* 
* * CryptogramTextView.java - The text/console based output for this game
* 
* CryptogramGUIView.java - The graphical user interface based output for this game
*/

import java.util.Scanner;

import javafx.application.Application;

public class Cryptograms {
	
	/**
	 * 
     * Purpose: Method is the main function for the entire Cryptography project.
     * Takes the user input and either will launch the text or GUI version of the game
     * 
     * Parameters:
     * @param:  A String of initial arguments (Should not have any initial args)
     *
     */
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the desired view: ");
		String[] line = scanner.nextLine().split(" ");
		if(line[0].toUpperCase().equals("-TEXT")) {
			CryptogramTextView textView = new CryptogramTextView();
			textView.playGame();
		}
		else if(line[0].toUpperCase().equals("-WINDOW")) {
			Application.launch(CryptogramGUIView.class, args);
		}
	}

}
