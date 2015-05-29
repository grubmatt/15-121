package HW8;

/**
 * 
 * @author Matt Gruber <mgruber1>
 * @section A
 *
 */

// You may not import any additonal classes or packages.
import java.util.*;

public class StringIterator implements Iterator<String> {
	private String string;
	private int index;

	// Do not change this method.
	public StringIterator(String s) {
		string = s;
		index = 0;
	}

	/**
	 * Complete the hasNext() method as indicated in the spec.
	 */
	public boolean hasNext() {
		if(string.length() > index)
			return true;
		else
			return false;
	}

	/**
	 * Complete the next() method as indicated in the spec.
     * This method should not perform any checks or validations ensuring
     * the current index is valid. 
	 */
	public String next() {
		String charToReturn = ""+string.charAt(index);
		index++;
		return charToReturn;
	}

	// Do not implement this method!
	public void remove() {
		return;
	}
}
