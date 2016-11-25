package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // Add a constructor
    public DictionaryLL() {
    	dict = new LinkedList<String>();
    }

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// Implement this method
    	String wordToAdd = word.toLowerCase();
    	if (dict.contains(wordToAdd)) {
    		return false;
    	} else {
    		dict.add(wordToAdd);
            return true;
    	}
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // Implement this method
    	return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        // Implement this method
    	String wordToFind = s.toLowerCase();
    	if (dict.contains(wordToFind)) {
    		return true;
    	}
        return false;
    }

    
}
