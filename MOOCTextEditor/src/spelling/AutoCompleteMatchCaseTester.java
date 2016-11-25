package spelling;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 */

public class AutoCompleteMatchCaseTester {

	private String dictFile = "data/words.small.txt"; 

	AutoCompleteMatchCase emptyDict; 
	AutoCompleteMatchCase smallDict;
	AutoCompleteMatchCase largeDict;
	
	/** @throws java.lang.Exception */
	@Before
	public void setUp() throws Exception {
		emptyDict = new AutoCompleteMatchCase();
		
		smallDict = new AutoCompleteMatchCase();
		smallDict.addWord("hello");
		smallDict.addWord("HElLo");
		smallDict.addWord("help");
		smallDict.addWord("he");
		smallDict.addWord("hem");
		smallDict.addWord("hot");
		smallDict.addWord("hey");
		smallDict.addWord("a");
		smallDict.addWord("subsequent");
        smallDict.addWord("Christine");
		
		largeDict = new AutoCompleteMatchCase();
		DictionaryLoader.loadDictionary(largeDict, dictFile);
	}

	
	/** Test method size */
	@Test
	public void testSize() {
		assertEquals("Testing size for emptyDict", 0, emptyDict.size());
		assertEquals("Testing size for smallDict", 10, smallDict.size());
		assertEquals("Testing size for largeDict", 4440, largeDict.size());
	}
	
	/** Test method isWord */
	@Test
	public void testIsWord() {
		assertEquals("Testing isWord on emptyDict: Hello", false, emptyDict.isWord("Hello"));
		assertEquals("Testing isWord on smallDict: Hello", true, smallDict.isWord("Hello"));	
		assertEquals("Testing isWord on smallDict: hello", true, smallDict.isWord("hello"));
		assertEquals("Testing isWord on smallDict: HELLO", true, smallDict.isWord("HELLO"));
		assertEquals("Testing isWord on largeDict: Hello", false, largeDict.isWord("Hello"));	
		assertEquals("Testing isWord on largeDict: hello", true, largeDict.isWord("hello"));

		assertEquals("Testing isWord on emptyDict: hellow", false, emptyDict.isWord("hellow"));
		assertEquals("Testing isWord on smallDict: hellow", false, smallDict.isWord("hellow"));
		assertEquals("Testing isWord on largeDict: hellow", false, largeDict.isWord("hellow"));
		
		assertEquals("Testing isWord on emptyDict: empty string", false, emptyDict.isWord(""));
		assertEquals("Testing isWord on smallDict: empty string", false, smallDict.isWord(""));
		assertEquals("Testing isWord on largeDict: empty string", false, largeDict.isWord(""));
		
		assertEquals("Testing isWord on smallDict: no", false, smallDict.isWord("no"));
		assertEquals("Testing isWord on largeDict: no", true, largeDict.isWord("no"));
		
		assertEquals("Testing isWord on smallDict: subsequent", true, smallDict.isWord("subsequent"));
        assertEquals("Testing isWord on largeDict: subsequent", true, largeDict.isWord("subsequent"));
        
        assertEquals("Testing isWord on smallDict: Christine", true, smallDict.isWord("Christine"));
        assertEquals("Testing isWord on smallDict: CHRISTINE", true, smallDict.isWord("CHRISTINE"));
        assertEquals("Testing isWord on smallDict: christine", false, smallDict.isWord("christine"));
	}
	
	/** Test method addWord */
	@Test
	public void addWord() {
		assertEquals("Asserting hellow is not in emptyDict", false, emptyDict.isWord("hellow"));
		assertEquals("Asserting hellow is not in smallDict", false, smallDict.isWord("hellow"));
		assertEquals("Asserting hellow is not in largeDict", false, largeDict.isWord("hellow"));

		emptyDict.addWord("hellow");
		smallDict.addWord("hellow");
		largeDict.addWord("hellow");

		assertEquals("Asserting hellow is now in emptyDict", true, emptyDict.isWord("hellow"));
		assertEquals("Asserting hellow is now in smallDict", true, smallDict.isWord("hellow"));
		assertEquals("Asserting hellow is now in largeDict", true, largeDict.isWord("hellow"));
		
		assertEquals("Asserting xyzabc is not in emptyDict", false, emptyDict.isWord("xyzabc"));
		assertEquals("Asserting xyzabc is not in smallDict", false, smallDict.isWord("xyzabc"));
		assertEquals("Asserting xyzabc is not in largeDict", false, largeDict.isWord("xyzabc"));
		
		emptyDict.addWord("XYZAbC");
		smallDict.addWord("XYZAbC");
		largeDict.addWord("XYZAbC");

		assertEquals("Asserting xyzabc is now in emptyDict", true, emptyDict.isWord("Xyzabc"));
		assertEquals("Asserting xyzabc is now in smallDict", true, smallDict.isWord("XYzabc"));
		assertEquals("Asserting xyzabc is now in largeDict", true, largeDict.isWord("XYZabc"));
		
		assertEquals("Asserting \"\" is not in emptyDict", false, emptyDict.isWord(""));
		assertEquals("Asserting \"\" is not in smallDict", false, smallDict.isWord(""));
		assertEquals("Asserting \"\" is not in largeDict", false, largeDict.isWord(""));
		
		assertEquals("Asserting no is not in smallDict", false, smallDict.isWord("no"));
		assertEquals("Asserting no is now in largeDict", true, largeDict.isWord("no"));
		
		assertEquals("Asserting subsequent is now in smallDict", true, smallDict.isWord("subsequent"));
		assertEquals("Asserting subsequent is now in largeDict", true, largeDict.isWord("subsequent"));
	}
	
	/** Test method predictCompletions */
	@Test
	public void testPredictCompletions() {
		List<String> completions;
		completions = smallDict.predictCompletions("", 0);
		assertEquals(0, completions.size());
		
		completions = smallDict.predictCompletions("", 4);
		assertEquals(4, completions.size());
		assertTrue(completions.contains("a"));
		assertTrue(completions.contains("he"));
		boolean twoOfThree = completions.contains("hey") && completions.contains("hot") ||
				             completions.contains("hey") && completions.contains("hem") ||
				             completions.contains("hot") && completions.contains("hem");
		assertTrue(twoOfThree);
		
		completions = smallDict.predictCompletions("he", 2);
		boolean allIn = completions.contains("he") && 
				(completions.contains("hem") || completions.contains("hey"));
		assertEquals(2, completions.size());
		assertTrue(allIn);
		
		completions = smallDict.predictCompletions("hel", 10);
		assertEquals(2, completions.size());
		allIn = completions.contains("hello") && completions.contains("help");
		assertTrue(allIn);
	
		completions = smallDict.predictCompletions("x", 5);
		assertEquals(0, completions.size());
	}
		
}