/*
* AUTHOR: Mark Oakeson
* FILE: CryptogramTextView.java
* ASSIGNMENT: Project 4 - Cryptography
* COURSE: CSc 335; Fall 2020
* PURPOSE: The purpose of this file is to be the main function and loop for the Cryptography text view version game. 
* It creates a CryptoGramController object which also creates a CryptogramModel object.  from there, 
* the program creates a loop that allows users to enter characters to swap on the command line to 
* try to decrypt the encrypted statement.  It does this by printing the encrypted quote, prompting
* the user for a character to replace and another character to replace that character with.  Then,
* the system will print out the quote with the current progress of the user's decrypted string.  This
* loop will repeat until the user get's the correct decryption for the quote
* 
*
* USAGE: 
* CryptogramModel.java - The actual code/functions that control the code aspects of the game
* 
* CryptogramController.java - The interface for controlling the actual code for the game
* 
* CryptogramTextView.java - The text/console based output for this game
* 
* CryptogramGUIView.java - The graphical user interface based output for this game
*/

import java.util.Scanner;

public class CryptogramTextView {
	
	/**
	 * Method is the loop for the Cryptogram Text view, that will print prompts, hints, 
	 * progress, etc. for the user until the game is over.
	 */
	public void playGame() {
		Scanner scanner = new Scanner(System.in);
		CryptogramController control = new CryptogramController(new CryptogramModel());
		while (!control.isGameOver() ) {
			System.out.println(control.getUsersProgress());
			System.out.println("Enter a command (type help to see commands):");
			String[] line = scanner.nextLine().split(" ");
			if(line[0].toUpperCase().equals("REPLACE")) {
				control.makeReplacement(line[1], line[3]);
			}
			else if(line.length == 3) {
				control.makeReplacement(line[0],line[2]);
			}
			else if(line[0].toUpperCase().equals("FREQ") ) {
				System.out.println(control.getFreq());
			}
			else if(line[0].toUpperCase().equals("HINT")) {
				String[] hint = control.giveHint();
				System.out.println("Hint: Replace "+ hint[0]+" with " +hint[1]);
				control.giveHint();
			}
			else if(line[0].toUpperCase().equals("EXIT")) {
				break;
			}
			else if(line[0].toUpperCase().equals("HELP") ) {
				control.giveHelp();
			}
			else {
				System.out.print("Invalid Command! Try again");
			}

		}
		if(control.isGameOver()) {
			System.out.println("You got it!");
		}
		else {
			System.out.println("The game has ended early.");
			System.exit(0);
		}
	}
}
