import java.util.Scanner;
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
 
public class WordUtils
{
	private String[] words;		// the dictionary of words
	
	// File containing dictionary of almost 100,000 words.
	private final String WORD_FILE = "/home/rg5210662/MyDocuments/WordUtils/wordList.txt";
	
	/* Constructor */
	public WordUtils() { 
		words = new String[90934];
		loadWords();
	}
	
	/**	Load all of the dictionary from a file into words array. */
	private void loadWords () { 
		Scanner input = FileUtils.openToRead(WORD_FILE);
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
	/***************************************************************/
	/************************** Testing ****************************/
	/***************************************************************/
	public static void main (String [] args)
	{
		WordUtils wu = new WordUtils();
		wu.run();
	}
	
	public void run() {
		String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
		String [] word = findAllWords(letters);
		System.out.println();
		printWords(word);
		
		// Score table in alphabetic order according to Scrabble
		int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
		String best = bestWord(word,scoreTable);
		System.out.println("\nHighest scoring word: " + best + "\nScore = " 
							+ getScore(best, scoreTable) + "\n");
	}
}
