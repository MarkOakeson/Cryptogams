import java.util.Observable;

/**
* @author Mark Oakeson
* FILE: CryptogramController.java
* @version ASSIGNMENT: Project 4 - Cryptography
* COURSE: CSc 335; Fall 2020
* PURPOSE: The purpose of this file is to be the controlling interface for the Cryptography project.
* It connects the main function and the actual code for controlling the game.  Performs basic
* functions for the user such as: telling if the game is over, processing the requested letter swap, 
* and returning the users current attempt and the original encrypted string.  Class extends Observable 
* in order to easily update the GUI in CryptogramGUIView.java
* 
*
* USAGE: 
* CryptogramModel.java - The actual code/functions that control the code aspects of the game, used
* 	through the functions implemented below
* 
* Cryptograms.java - The main function that runs the game, uses this file as the interface to call
* 	the functions in CryptogramsModel
* 
* CryptogramTextView.java - The text/console based output for this game
* 
* CryptogramGUIView.java - The graphical user interface based output for this game
*/

public class CryptogramController extends Observable{
	private CryptogramModel model;
	
	/**
     * Purpose: Constructor method that creates a CryptogramModel object and updates 
     * observers 
     * 
     * Parameters:
     * @param:  A CryptogramModel object to use throughout the controller class
     *
     * Returns:
     * None
     */

	public CryptogramController (CryptogramModel model) { 
		this.model = model;
		setChanged();
		notifyObservers();
		}
		
		
	/**
     * Purpose: Method checks if the user's guessed decryption string matches the original 
     * quote string
     * 
     * Parameters:
     * None
     *
     * Returns:
     * @return Returns True or False based on the boolean check
     */
	public boolean isGameOver() { 
		if(model.getAnswer().equals(model.getDecryptedString())) {
			return true;
		}
		return false;
		}
	
	
	/**
     * Purpose: Method replaces the character in the encrypted string with 
     * the letter to replace it with and updates observers.  Passes a String array
     * to the update() funciton in the Observer interface to insert user's guess into the TextFields in the 
     * graphic user interface
     * 
     * Parameters:
     * @param letterToReplace:  A char of the character in the encrypted string to remove
     * @param replacementLetter:  A char of the character to replace the encrypted string to replace
     *
     * Returns:
     * None
     */
	public void makeReplacement(String letterToReplace, String replacementLetter) {
		model.setReplacement(letterToReplace, replacementLetter);
		String[] arr = new String[2];
		arr[0] = letterToReplace;
		arr[1] = replacementLetter;
		setChanged();
		notifyObservers(arr);
	}
	
	
	/**
     * Purpose: Method returns the original encrypted string for the view
     * 
     * Parameters:
     * None
     *
     * Returns:
     * @return a String of the encrypted string
     */
	public String getEncryptedQuote() { 
		/* for the view to display */ 
		return model.getEncryptedString();
		}
	
	
	
	/**
     * Purpose: Method prints the users decrypted in progress string for the view
     * 
     * Parameters:
     * None
     *
     * Returns:
     * @return a String of the user's decrypted code so far
     */
	public String getUsersProgress() { 
		/* for the view to display */ 
		return model.getPro();
	} 
	
	
	/**
     * Purpose: Method prints the number of times each letter appears in the encrypted
     * quote
     * 
     * Parameters:
     * None
     *
     * Returns:
     * @return a String of the number of times each letter appears in the encrypted quote
     */
	public String getFreq() {
		return model.getFrequency();
	}
	
	/**
     * Purpose: Method gives the user a hint by returning either a missed guess or
     * an un-attempted guess to retry with different letters.  Also updates Observers
     * for the GUI
     * 
     * Parameters:
     * None
     *
     * Returns:
     * @return hint string
     * a String stating a letter to remove and the letter to replace it
     */
	public String[] giveHint() {
		String[] arr = model.hint();
		setChanged();
		notifyObservers(arr);
		return arr;
	}
	
	
	
	/**
     * Purpose: Method gives the user a list of possible commands to pass through the command
     * line to perform certain functions in the main file
     * 
     * Parameters:
     * None
     *
     * Returns:
     * none
     */
	public void giveHelp() {
		System.out.println("The list of commands are:\n - Replace\n - Freq\n - Hint");
		System.out.println(" - Exit\n - Help");
	}
}
