package ProjectFive;

/**
 * COSC 310
 * MainIndexingClass.java
 * 
 * This class is the indexing class which handles all of the indexing operations, there are only 
 * two public methods, indexWord() and PrintResults()
 * 
 * 
 * @author Nathaniel Churchill
 * Professor: Waleed Farag
 * Date: 4/27/17
 *
 */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainIndexingClass {
	//attributes
	private TreeMap<String, TreeSet<Integer>> wordMap = new TreeMap<>(); //main and final data structure for the output
	
	private TreeSet<Integer> finalChapterSet;
	private TreeSet<String> wordSet;
	//set of Word objects
	private LinkedList<Word> wordList;
	
	/**
	 * The constructor for the main indexing class which makes all the necessary data structures
	 */
	public MainIndexingClass(){
		 wordMap = new TreeMap<>();
		 finalChapterSet = new TreeSet<>();
		 wordSet = new TreeSet<>();
		 wordList = new LinkedList<>();
	}
	
	
	
	
	/**
	 * This method creates a Word object and puts it into a LinkedList as well as putting the the word into a set
	 * @param word the word from the file
	 * @param chapter the chapter the word was found in
	 */
	public void indexWord(String word, int chapter){
		boolean added = wordSet.add(word); // testing to see if the word is in the set
		
		Word tempWord = new Word(word, chapter); //creating a temporary Word object
		
		if(added == true){ // if the word does not exist in the word set, add the Word to the LinkedList
			wordList.add(tempWord);
			
		}else {//if the word exists in the word set, find the matching Word in the linked list and add the chapter
			int index = wordList.indexOf(tempWord);
			wordList.get(index).addChapter(chapter);
		}
	}
	
	
	
	
	/**
	 * This private method performs some operations necessary before printing
	 */
	private void calculate(){
		// build chapter set from LinkedLists
		for(Word word : wordList){
			wordMap.put(word.word, word.chapterSet);
		}
	}
	
	
	
	/**
	 * This method prints the word and the corresponding word set
	 */
	public void printResults() {
		calculate(); // finishes the required operations
		
		for (String element : wordSet){// for every word get its chapters and print them
			System.out.println(element + " " + wordMap.get(element).toString());
		}
		
	}
	
	
	
	/**
	 * This private class represents a word and its chapter set
	 * 
	 * @author Nathaniel Churchill
	 *
	 */
	private class Word implements Comparable{
		//attributes
		private String word;
		private TreeSet<Integer> chapterSet = new TreeSet<>();
		
		/**
		 * Constructor for the private Word class
		 * @param word the word
		 * @param chapter the chapter of the given word
		 */
		public Word(String word, int chapter){
			this.word = word;
			chapterSet.add(chapter);
		}
		
		/**
		 * This method adds a chapter to a particular Word
		 * @param chapter the chapter to be added
		 */
		public void addChapter(int chapter){
			chapterSet.add(chapter);
		}
		
		
		@Override
		public int compareTo(Object obj){
			return this.word.compareTo(((Word)obj).word);
		}
		@Override
		public boolean equals(Object obj) {
			return this.word.equals(((Word)obj).word);
		}
		
	}
}
