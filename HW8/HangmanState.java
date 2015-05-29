package HW8;

/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

// You may not import any additional classes or packages.
import java.util.*;

public class HangmanState {
	//Do not change any of these global fields.
	public static final int NORMAL_MODE = 0;
	public static final int HURTFUL_MODE = 1;
	public static final int HELPFUL_MODE = 2;

	public String theAnswer;
	public Set<String> lettersGuessed;
	public String feedbackToUser;
	public Set<String> possibleAnswers;

	/**
	 * Complete the HangmanState constructor as indicated in the spec.
	 */
	public HangmanState(Set<String> knownWords) {
		// Sets theAnswer randomly
		Iterator<String> wordSet = knownWords.iterator();
		int randomIteration = (int) (Math.random()*knownWords.size());
		for(int i=0;i<randomIteration;i++)
			theAnswer = wordSet.next();
		
		lettersGuessed = new HashSet<String>();
		String tempFeedback = "";
		for(int i=0;i<theAnswer.length();i++)
			tempFeedback += "-";
		feedbackToUser = tempFeedback;
		
		possibleAnswers = knownWords;
		updatePossibleAnswers();
	}

	/**
	 * Complete the feedbackFor method as indicated in the spec.
	 */
	public String feedbackFor(String answer) {
		String answerGuessed = "";
		StringIterator answerIter = new StringIterator(answer);
		
		while(answerIter.hasNext())
		{
			String currentChar = answerIter.next();
			Iterator<String> letterSet = lettersGuessed.iterator();
			boolean letterFound = false;
			while(letterSet.hasNext())
			{
				if(letterSet.next().equals(currentChar))
				{
					letterFound = true;
					answerGuessed += currentChar;
				}
			}
			if(!letterFound)
				answerGuessed+="-";
		}
		return answerGuessed;
	}

	/**
	 * Complete the wrongGuesses method as indicated in the spec.
	 */
	public Set<String> wrongGuesses() {
		Set<String> wrongGuesses = new HashSet<String>();
		Iterator<String> letterSet = null;
		if(lettersGuessed!=null)
		{
			letterSet = lettersGuessed.iterator();
		}
		else
			return wrongGuesses;
		
		while(letterSet.hasNext())
		{
			String currentChar = letterSet.next();
			if(!feedbackToUser.contains(currentChar))
				wrongGuesses.add(currentChar);
		}
		return wrongGuesses; 
	}

	/**
	 * Complete the letterGuessedByUser method as indicated in the spec.
	 */
	public void letterGuessedByUser(String letter, int mode) {
		
		lettersGuessed.add(letter);
		
		if(mode==0)
			feedbackToUser = feedbackFor(theAnswer);
		else if(mode==1)
			feedbackToUser = feedbackFor(mostCommonFeedback(generateFeedbackMap()));
		else
		{
			Map<String, Integer> tempMap = generateFeedbackMap();
			if(tempMap.size()>1) tempMap.remove(feedbackToUser);
			feedbackToUser = mostCommonFeedback(tempMap);
		}
		updatePossibleAnswers();
		return; 
	}

	/**
	 * Complete the updatePossibleAnswers() as indicated in the spec.
	 */
	public void updatePossibleAnswers() {
		Iterator<String> possibleWordSet = possibleAnswers.iterator();
		Set<String> temp = new HashSet<String>();
		while(possibleWordSet.hasNext())
		{
			String possibleWord = possibleWordSet.next();
			String compare = feedbackFor(possibleWord);
			if(compare.equals(feedbackToUser))
				temp.add(possibleWord);
		}

		possibleAnswers = temp;
	}

	/**
	 * Complete the generateFeedbackMap method as indicated in the spec.
	 */
	public Map<String, Integer> generateFeedbackMap() {
		Map<String, Integer> feedbackMap = new HashMap<String, Integer>();
		Iterator<String> possibleWordSet = possibleAnswers.iterator();
		while(possibleWordSet.hasNext())
		{
			String compare = feedbackFor(possibleWordSet.next());
			if(feedbackMap.containsKey(compare))
			{
				int value = feedbackMap.get(compare);
				feedbackMap.put(compare, value+1);
			}
			else
				feedbackMap.put(compare, 1);
		}
		
		return feedbackMap; 
	}

	/**
	 * Complete the mostCommonFeedback method as indicated in the spec.
	 */
	public String mostCommonFeedback(Map<String, Integer> feedbackMap) {
		Set<String> allKeys = feedbackMap.keySet();
		String highestKey = null;
		int highestVal = 0;
		Iterator<String> keyIter = allKeys.iterator();
		while(keyIter.hasNext())
		{
			String currentKey = keyIter.next();
			if(feedbackMap.get(currentKey)>highestVal)
			{
				highestKey = currentKey;
				highestVal = feedbackMap.get(currentKey);
			}
		}
		
		return highestKey; // remove this line when you're done
	}

    /* Do not modify the methods below. */

	public String getFeedbackToUser() {
		return feedbackToUser;
	}

	public String toString() {
		return feedbackToUser + "\t\t" + wrongGuesses() + "\t\t"
				+ possibleAnswers.size();
	}
}
