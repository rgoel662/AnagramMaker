import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
/**
 *	Provides utilities for word games:
 *	1. finds all words in the dictionary that match a list of letters
 *	2. prints an array of words to the screen in tabular format
 *	3. finds the word from an array of words with the highest score
 *	4. calculates the score of a word according to a table
 *
 *	Uses the FileUtils and Prompt classes.
 *	
 *	@author Rishabh Goel
 *	@since	10/20/22
 */
 
public class WordUtilities
{
	private String[] words;		// the dictionary of words
	
	/* Constructor */
	public WordUtilities() { 
		words = new String[48831];
		// words = new String[90934];
		// loadWords();
	}

	/**
	 *	Determines if a word's characters match a group of letters
	 *	@param word		the word to check
	 *	@param letters	the letters
	 *	@return			true if the word's chars match; false otherwise
	 */
	private boolean wordMatch(String word, String letters) {
		// if the word is longer than letters return false
		if (word.length() > letters.length()) return false;
		
		// while there are still characters in word, check each word character
		// with letters
		while (word.length() > 0) {
			// using the first character in word, find the character's index inside letters
			// and ignore the case
			int index = letters.toLowerCase().indexOf(Character.toLowerCase(word.charAt(0)));
			// if the word character is not in letters, then return false
			if (index < 0) return false;
			
			// remove character from word and letters
			word = word.substring(1);
			letters = letters.substring(0, index) + letters.substring(index + 1);
		}
		// all word letters were found in letters
		return true;
	}
	
	/**
	 *	finds all words that match some or all of a group of alphabetic characters
	 *	Precondition: letters can only contain alphabetic characters a-z and A-Z
	 *	@param letters		group of alphabetic characters
	 *	@return				an ArrayList of all the words that match some or all
	 *						of the characters in letters
	 */
	public ArrayList<String> allWords(String letters) {
		ArrayList<String> wordsFound = new ArrayList<String>();
		// check each word in the database with the letters
		for (String word: words)
			if (wordMatch(word, letters))
				wordsFound.add(word);
		return wordsFound;
	}
	
	/**
	 *	Sort the words in the database
	 */
	public void sortWords() {
		SortMethods sm = new SortMethods();
		sm.mergeSort(words);
	}

	
	/**	Load all of the dictionary from a file into words array. */
	public void loadWords (String fileName) { 
		Scanner input = FileUtils.openToRead(fileName);
		int counter = 0;
		while(input.hasNext()){
			words[counter] = input.nextLine();
			counter++;
		}
	}
	
	/**	Find all words that can be formed by a list of letters.
	 *  @param letters	string containing list of letters
	 *  @return			array of strings with all words found.
	 */
	public String [] findAllWords (String letters)
	{	
		int count = 0;
		for (int i = 0 ; i < words.length; i ++){
			if (isWordMatch(words[i], letters)){
				count ++;
			}
		}
		String[] outWords = new String[count];
		int count2 = 0;
		for (int i = 0; i < words.length; i++){
			if (isWordMatch(words[i], letters)){
				outWords[count2] = words[i];
				count2++;
			}
		}
		return outWords;
	}
	
	/**
	 *  Decides if a word matches a group of letters.
	 *
	 *  @param word  The word to test.
	 *  @param letters  A string of letters to compare
	 *  @return  true if the word matches the letters, false otherwise
	 */
	public boolean isWordMatch (String word, String letters) {
		boolean hasMatch = true;
		for (int i = 0 ; i < word.length(); i ++){
			if (getCount(word.charAt(i), word) > getCount(word.charAt(i), letters)){
				hasMatch = false; 
			}
		}
		return hasMatch;
	}
	
	/**
	 * Counts the number of appearances a character makes in a string
	 * 
	 * @param letter	The letter that is being searched for
	 * @param target	The string that the letter is being searched in
	 * @return			The number of appearances the character made
	 */
	private int getCount(char letter, String target){
		int count = 0;
		for (int i = 0 ; i < target.length() ; i ++){
			if (target.charAt(i) == letter){
				count ++;
			}
		}
		return count;
	}
	
	
	/**	Print the words found to the screen.
	 *  @param words	array containing the words to be printed
	 */
	public void printWords (String [] wordList) { 
		for (int i = 0 ; i < wordList.length; i ++){
			System.out.printf("%-13s", wordList[i]);
			if ((i + 1) % 5 == 0)
				System.out.println();
		}
		System.out.println();
	}
	
	/**	Finds the highest scoring word according to a score table.
	 *
	 *  @param word  		An array of words to check
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return   			The word with the highest score
	 */
	public String bestWord (String [] wordList, int [] scoreTable)
	{
		String best = "";
		int highestScore = 0;
		for (int i = 0; i < wordList.length; i ++){
			int score = getScore(wordList[i], scoreTable);
			if (score > highestScore){
				highestScore = score;
				best = wordList[i];
			}
		}
		return best;
	}
	
	/**	Calculates the score of one word according to a score table.
	 *
	 *  @param word			The word to score
	 *  @param scoreTable	An array of 26 integer scores in letter order
	 *  @return				The integer score of the word
	 */
	public int getScore (String word, int [] scoreTable)
	{	
		word = word.toLowerCase();
		int score = 0;
		for (int i = 0; i < word.length() ; i ++){
			score += scoreTable[word.charAt(i) - 'a'];
		}
		return score;
	}

	public String[] getWords(){
		return words;
	}

	public String removeLetters(String initial, String letters){
		String out = "";
		for (int i = 0; i < initial.length(); i++){
			boolean shouldAdd = true; 
			for (int j = 0; j < letters.length(); j++){
				if (initial.charAt(i) == letters.charAt(j)){
					shouldAdd = false;
					if (j == letters.length() -1)
						letters = letters.substring(0, j);
					else
						letters = letters.substring(0, j) + letters.substring(j+1);
					break;
				}
			}
			if(shouldAdd)
				out += initial.charAt(i);
		}
		return out;
	}
}
