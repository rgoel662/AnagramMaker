import java.util.ArrayList;

/**
 *	AnagramMaker - <description goes here>
 *
 *	Requires the WordUtilities, SortMethods, Prompt, and FileUtils classes
 *
 *	@author	
 *	@since	
 */
public class AnagramMaker {
								
	private final String FILE_NAME = "randomWords.txt";	// file containing all words
	
	private WordUtilities wu;	// the word utilities for building the word
								// database, sorting the database,
								// and finding all words that match
								// a string of characters
	
	// variables for constraining the print output of AnagramMaker
	private int numWords;		// the number of words in a phrase to print
	private int maxPhrases;		// the maximum number of phrases to print
	private int numPhrases;		// the number of phrases that have been printed
		
	/*	Initialize the database inside WordUtilities
	 *	The database of words does NOT have to be sorted for AnagramMaker to work,
	 *	but the output will appear in order if you DO sort.
	 */
	public AnagramMaker() {
		wu = new WordUtilities();
		wu.loadWords(FILE_NAME);
		wu.sortWords();
	}
	
	public static void main(String[] args) {
		AnagramMaker am = new AnagramMaker();
		am.run();
	}
	
	/**	The top routine that prints the introduction and runs the anagram-maker.
	 */
	public void run() {
		printIntroduction();
		runAnagramMaker();
		System.out.println("\nThanks for using AnagramMaker!\n");
	}
	
	/**
	 *	Print the introduction to AnagramMaker
	 */
	public void printIntroduction() {
		System.out.println("\nWelcome to ANAGRAM MAKER");
		System.out.println("\nProvide a word, name, or phrase and out comes their anagrams.");
		System.out.println("You can choose the number of words in the anagram.");
		System.out.println("You can choose the number of anagrams shown.");
		System.out.println("\nLet's get started!");
	}
	
	/**
	 *	Prompt the user for a phrase of characters, then create anagrams from those
	 *	characters.
	 */
	public void runAnagramMaker() {
		ArrayList<String> anagram = new ArrayList<>();
		System.out.println(wu.allWords("digit"));
		anagramMaker("digit", anagram);
	}

	public void anagramMaker(String phrase, ArrayList<String> anagram){
		if(phrase.length() != 0){
			ArrayList<String> allWords = wu.allWords(phrase);
			for (int i = 0 ; i < allWords.size(); i++){
				anagram.add(allWords.get(i));
				String newPhrase = wu.removeLetters(phrase, allWords.get(i));
				System.out.println(newPhrase);
				anagramMaker(newPhrase, anagram);
				anagram.remove(anagram.size() - 1);
			}

		} else {
			// for (int i = 0; i < anagram.size(); i++){
			// 	System.out.print(anagram.get(i) + " ");
			// }
			// System.out.println();
		}
	}
	
}