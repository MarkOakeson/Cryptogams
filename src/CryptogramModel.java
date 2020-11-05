/**
* AUTHOR: Mark Oakeson
* FILE: CryptogramModel.java
* @version ASSIGNMENT: Project 1 - Cryptography
* COURSE: CSc 335; Fall 2020
* PURPOSE: The purpose of this file is to be the model for the Cryptography project.  This file
* contains the code for creating the encryption HashMap, reading in the quotes from "quotes.txt",
* randomly picking a quote, and encrypting that quote.  It also contains the original quote as a 
* String and keeps track of the user's current guesses and the guessed string.
* 
*
* @usage: 
* CryptogramController.java - The interface that this file implements
* 
* Cryptograms.java - The main function that runs the game, uses this file as the interface to call
* 	the functions in CryptogramsModel
*/
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Observable;
import java.util.Scanner;


public class CryptogramModel extends Observable {
	
	
	private HashMap<Character, Character> keyMap;
	private HashMap<Character, Character> userMap;
	private String answerStr;
	protected String encStr;
	private HashMap<Character, Integer> freq;
	
	
	/**
     * Purpose: Constructor method reads in the "quotes.txt" file, then picks a random quote as the answer
     * String, creates a map for the alphabet to encrypted alphabet, creates a map for the users guesses, 
     * and encrypts the answer string to another string.  Method will also check that the encryption map 
     * will not have duplicates
     * 
     * Parameters:
     * None
     *
     * Returns:
     * None
     */
    public CryptogramModel() {
	/* Read a random line from the quotes.txt file. Make the keys and set the answer */
    	Scanner fileScanner = null;
    	try {
			fileScanner = new Scanner(new File("quotes.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
    	this.freq = new HashMap<Character, Integer>();
    	this.answerStr = fileScanner.nextLine();
    	//this.answerStr = getRandomLine(fileScanner);
    	this.keyMap = keyMapGenerator();
    	boolean check = checkDuplicates();
    	while(check) {
    		this.keyMap = keyMapGenerator();
    		check = checkDuplicates();
    	}
    	//System.out.println(check);
    	this.userMap = new HashMap<Character, Character>();
    	this.encStr = "";
    	stringEncrypt();
	}
    
    
    /**
     * Purpose: Method is a helper method for the constructor to create the encrypted map
     * to encrypt the answer string as the encrypted string
     * 
     * Parameters:
     * None
     *
     * Returns:
     * @return HashMap A HashMap of the encryption keys, stored as keyMap
     */
    private HashMap<Character, Character> keyMapGenerator() {
    	ArrayList<Character> letters = new ArrayList<Character>();
    	HashMap<Character, Character> map = new HashMap<Character, Character>();
    	String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    	for(int i = 0; i < str.length(); i++) {
    		char toAdd = str.charAt(i);
    		letters.add(toAdd);
    		freq.put(toAdd, 0);
    	}
    	Collections.shuffle(letters);
    	for(int i = 0; i < str.length(); i++) {
    			map.put(str.charAt(i), letters.get(i));
    	}
    	return map;
    }
    
    
    /**
     * Purpose: Method is a helper method for the keyMapGenerator(), to check if there are 
     * any duplicates in the map, then returns a boolean
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return True or false on if there are repeats in the map
     */
    private boolean checkDuplicates() {
    	for(Character c: keyMap.keySet()) {
    		if(c == keyMap.get(c)) {
    			return true;
    			
    		}
    	}
    	return false;
    }
    
    
    /**
     * Purpose: Method is a helper method for the constructor to add all of the quotes from
     * the "quotes.txt" file into an ArrayList, then randomly pick the first item in the list 
     * after shuffling it
     * 
     * Parameters:
     * @param fileScanner:  A Scanner that is used to read the quotes file in
     *
     * Returns:
     *  @return A randomly selected String from the file
     */
    private String getRandomLine(Scanner fileScanner) {
    	ArrayList<String> quoteList = new ArrayList<String>(); 
    	while(fileScanner.hasNext()) {
    		quoteList.add(fileScanner.nextLine());
    	}
    	fileScanner.close();
    	Collections.shuffle(quoteList);
    	return quoteList.get(0);
    	
    }
    
    
    /**
     * Purpose: Method is a helper method for the constructor to create the encrypted string
     * from the randomly selected quote
     * 
     * Parameters:
     * None
     *
     * Returns:
     * Nothing, but updates the encStr
     **/
    private void stringEncrypt() {
    	for(int i = 0; i < this.answerStr.length(); i++) {
    		char c = Character.toUpperCase(answerStr.charAt(i));
    		if(keyMap.containsKey(c)) {
    			encStr += keyMap.get(c);
    			int val = freq.get(keyMap.get(c));
				val++;
				freq.put(keyMap.get(c), val);
    		}
    		else {
    			encStr += answerStr.charAt(i);
    		}
    	} 
    }
    
    
    /**
     * Purpose: Method "attempts" a user guess by updating the userMap with the letter to remove
     * as the key and the replacement letter as the value, checks that the replacement or removed
     * letter request is not punctuation
     * 
     * Parameters:
     * @param key:  A Char of the letter to remove
     * @param val:  A Char of the letter to replace
     *
     * Returns:
     * Nothing, but updates the userMap
     */
	public void setReplacement(String key, String val) { /* add to our decryption attempt */
		Character k = key.toUpperCase().charAt(0);
		Character v = val.toUpperCase().charAt(0);
		if(k =='.' || k == ',' || k == '\'' || k == '-') {
			System.out.println("Cannot replace punctuation! Try again.");
		}
		else if(v =='.' || v == ',' || v == '\'' || v == '-') {
			System.out.println("Cannot replace punctuation! Try again.");
		}
		else{
			userMap.put(k, v);
		}
	}
	
	
	
	/**
     * Purpose: Method returns the original encrypted quote
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return A String of the original encrypted quote
     */
	public String getEncryptedString() {
		return encStr;
	}
	
	
	
	/**
     * Purpose: Method returns the user's attempted decrypted quote by checking the
     * user HashMap and the key hashMap
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return A String of the user's decrypted quote
     */
	public String getDecryptedString() {
	/* return the decrypted string with the guessed replacements or
	spaces */ 
		String retval = "";
		for(int i = 0; i < encStr.length(); i++) {
			if(userMap.containsKey(encStr.charAt(i))){
				retval += userMap.get(encStr.charAt(i));
			}
			else {
				if(keyMap.containsKey(encStr.charAt(i))) {
					retval += " ";
				}
				else {
					retval += encStr.charAt(i);
				}
			}
		}
		return retval;
		}
	
	
	
	/**
     * Purpose: Method returns the original quote
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return A String of the original quote
     */
	public String getAnswer() {
		return answerStr.toUpperCase();
	    }
	
	
	/**
     * Purpose: Method returns a List as a string of how many times each letter
     * appears in the encrypted string
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return A String of how many times each letter appears in the encrypted string
     */
	public String getFrequency() {
		String retval = "";
		int count = 1;
		for(Character c: freq.keySet()) {
				if(count % 8 ==0) {
					retval += "\n";
					count = 1;
				}
				retval += c + ": "+ freq.get(c) + " ";
				count++;
		}
		return retval;
	}
	
	
	/**
     * Purpose: Method gives the user a hint by returning either a missed guess or
     * an un-attempted guess to retry with different letters
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return a String stating a letter to remove and the letter to replace it
     */
	public void hint() {
		HashSet<Character> set = getAnswerSetOfChars();
		for(Character c: keyMap.keySet()) {
			if(set.contains(c)) {
				if(!userMap.containsValue(c)) {
					System.out.println("Hint: Replace "+ keyMap.get(c)+" with " +c);
					return;
				}
			}
		}
	}
	
	private HashSet<Character> getAnswerSetOfChars(){
		HashSet<Character> set = new HashSet<Character>();
		for(int i = 0; i < answerStr.length(); i++) {
			Character c = Character.toUpperCase(answerStr.charAt(i));
			if(!set.contains(answerStr.charAt(i))) {
				set.add(c);
			}	
		}
		return set;
	}
	
	/**
     * Purpose: Method returns the user's progress as well as the encrypted string as a 
     * string to be printed for the user's view.  It also wraps the text around if there are 
     * more than 80 characters
     * 
     * Parameters:
     * None
     *
     * Returns:
     *  @return a String to be printed to provide a view of the user's attempt at decrypting the string
     * as well as the encrypted string
     */
	public String getPro() {
		String retval = "";
		int start = 0;
		int count = 0;
		HashSet<Character> punctuation = new HashSet<Character>();
		punctuation.add(',');
		punctuation.add('.');
		punctuation.add(' ');
		for(int i = 0; i < encStr.length(); i++) {
			if(encStr.charAt(start) == ' ') {
				start++;
			}
			if(punctuation.contains(encStr.charAt(i)) && (i - start) <= 80) {
				count = i;
			}
			else if((i - start) > 80) {
				retval += getDecryptedString().substring(start+1, count)+ "\n";
				retval += encStr.substring(start+1, count) + "\n\n";
				
				start = count;
				count = 0;
			}
		}
		retval += getDecryptedString().substring(start)+"\n";
		retval += encStr.substring(start);
		return retval;
	}
	

}
