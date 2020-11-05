/**
* 
* @author Mark: Mark Oakeson
* FILE: Cryptograms.java
* @version ASSIGNMENT: Project 1 - Cryptography
* COURSE: CSc 335; Fall 2020
* Purpose PURPOSE: The purpose of this file is to be the main function and loop for the Cryptography game. 
* This is also the view file for the entire Cryptography project.
* It creates a CryptoGramController object which also creates a CryptogramModel object.  from there, 
* the program creates a loop that allows users to enter characters to swap on the command line to 
* try to decrypt the encrypted statement.  It does this by printing the encrypted quote, prompting
* the user for a character to replace and another character to replace that character with.  Then,
* the system will print out the quote with the current progress of the user's decrypted string.  This
* loop will repeat until the user get's the correct decryption for the quote
* 
*
* @usage: 
* CryptogramModel.java - The actual code/functions that control the code aspects of the game
* 
* CryptogramController.java - The interface for controlling the actual code for the game
*/

import java.util.Scanner;

import javafx.application.Application;

public class Cryptograms {
	
	/**
	 * 
     * Purpose: Method is the main function for the entire Cryptography project.
     * Creates the CryptogramController and CryptogramModel, then the loop will
     * prompt the user with information about the encrypted quote, their current attempt
     *  at decoding, the letter they want to remove and the letter they want to replace it with.
     *  This loop will recur until the user solves the quote.
     * 
     * Parameters:
     * @param:  A String of initil arguments (Should not have any initial args)
     *
     * Return:
     * Nothing, but as the View model, prints various information about their current progress
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
