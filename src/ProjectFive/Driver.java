package ProjectFive;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * COSC 310
 * Driver.java
 * 
 * This is the driver class for project Five
 * This programs takes a txt file and indexes every alphabetic word
 * and lists every chapter they appear in.
 * 
 * @author Nathaniel Churchill
 * Professor: Waleed Farag
 * Date: 4/27/17
 *
 */

public class Driver {

	public static void main(String[] args) {
		Scanner fileIn = null; //Scanner object
		
		int chapterCounter = 0; //initializing chapter counter to 0
		
		MainIndexingClass operation = new MainIndexingClass(); //creating an object of MainIndexingClass
		
		try{
			fileIn = new Scanner(new File("alice30.txt"));// open the file
			fileIn.useDelimiter("[^a-zA-Z]+"); //use delimiter
			
			while (fileIn.hasNext()){ // while there is more input
				
				String word = fileIn.next(); // get the next word

				word = word.toLowerCase(); // convert to lower case

				// checking for the chapter number
				if (word.equals("chapter")) {
					chapterCounter++;
				}
				
				operation.indexWord(word, chapterCounter); //index word

			}
		} catch (Exception e) {// catching exceptions
			System.out.println("There was an error in file I-O  " + e);
		}
		fileIn.close(); // closing files

		// printing the results
		operation.printResults();
		
	}

}
