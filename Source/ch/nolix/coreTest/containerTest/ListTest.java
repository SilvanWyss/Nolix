//package declaration
package ch.nolix.coreTest.containerTest;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.SequencePattern;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.core.test2.Test;

//test class
/**
 * This class is a test class for the list class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 510
 */
public final class ListTest extends Test {
		
	//test method
	public void test_clear() {
		
		//setup
		final List<String> list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution
		list.clear();
		
		//verification
		expectThat(list.isEmpty());
	}
	
	//test method
	public void test_contains() {
		
		//setup
		final List<String> list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution and verification
			expectThat(
				list.contains(s -> s.equals("x")),
				list.contains(s -> s.equals("xx")),
				list.contains(s -> s.equals("xxx")),
				list.contains(s -> s.equals("xxxx")),
				list.contains(s -> s.equals("xxxxx")),
				list.contains(s -> s.equals("xxxxxx"))
			);
			
			expectThatNot(
				list.contains(s -> s.equals("xxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxxxxx"))
			);
	}
	
	//test method
	public void test_containsOne() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectThatNot(list.containsOne());
	}
	
	//test method
	public void test_containsOne_2() {
		
		//setup
		final List<String> list = new List<String>("x");
		
		//execution and verification
		expectThat(list.containsOne());
	}
	
	//test method
	public void test_containsOne_3() {
		
		//setup
		final List<String> list = new List<String>("x", "x");
		
		//executation and verification
		expectThatNot(list.containsOne());
	}
	
	//test method
	public void test_containsOne_4() {
		
		//setup
		final List<String> list
		= new List<String>("x",  "xx", "xx", "xx", "xx", "xx");
		
		//execution and verification
		expectThat(list.containsOne(s -> s.length() == 1));
		expectThatNot(list.containsOne(s -> s.length() == 2));
	}
	
	//test method
	public void test_forEach() {
		
		//setup
			final List<String> list1 = new List<String>(
				"x",
				"xx",
				"xxx",
				"xxxx",
				"xxxxx",
				"xxxxxx"
			);
			
			final List<String> list2 = new List<String>();
		
		//execution
		list1.forEach(s -> list2.addAtEnd(s));
		
		//verification
			expectThat(list2.getElementCount()).equals(list1.getElementCount());
			
			//Iterates list1.
			for (int i = 1; i <= list1.getElementCount(); i++) {
				expectThat(list2.getRefAt(i)).equals(list1.getRefAt(i));
			}
	}
	
	//test method
	public void test_getRefByMax() {
		
		//setup
		final List<String> list = new List<String>(
			"cake",
			"chocolate",
			"ice cream",
			"milk",
			"meat",
			"pizza",
			"sandwich",
			"toast"	
		);
		
		//execution and verification
		expectThat(list.getRefByMax(s -> s)).equals("toast");
	}
	
	//test method
	public void test_getRefByMaxInt() {
		
		//setup
		final List<String> list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution and verification
		expectThat(list.getRefByMaxInt(s -> s.length())).equals("xxxxxx");
	}
	
	//test method
	public void test_getRefByMin() {
		
		//setup
		final List<String> list = new List<String>(
			"cake",
			"chocolate",
			"ice cream",
			"milk",
			"meat",
			"pizza",
			"sandwich",
			"toast"
		);
		
		//execution and verification
		expectThat(list.getRefByMin(s -> s)).equals("cake");
	}
	
	//test method
	public void test_getRefByMinInt() {
		
		//setup
		final List<String> list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution and verification
		expectThat(list.getRefByMinInt(s -> s.length())).equals("x");
	}
	
	//test method
	public void test_getSequences() {
		
		//setup
			final List<String> list = new List<String>(
				"x",
				"a",
				"x",
				"b",
				"x",
				"c"
			);
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.addConditionForNext(s -> s.equals("x"))
			.addBlankForNext();
		
		//execution
		final List<List<String>> sequences = list.getSequences(sequencePattern);
		
		//verification
			expectThat(sequences.getElementCount()).equals(3);
			
			//Iterates the sequences.
			for (final List<String> s : sequences) {
				expectThat(s.getElementCount()).equals(2);
				expectThat(s.getRefAt(1)).equals("x");
			}
	}
	
	//test method
	public void test_getSequences_2() {
		
		//setup
			final List<String> list = new List<String>(
				"x",
				"x",
				"xxxx",
				"x",
				"x",
				"xxxx",
				"x",
				"x",
				"x",
				"xxxx",
				"x",
				"xxxx"
			);
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.addConditionForNext(s -> s.equals("x"))
			.addConditionForNext(s -> s.equals("xxxx"));
		
		//execution
		final List<List<String>> sequences = list.getSequences(sequencePattern);
		
		//verification
			expectThat(sequences.getElementCount()).equals(4);
			
			//Iterates the sequences.
			for (final List<String> s : sequences) {
				expectThat(s.getElementCount()).equals(2);
				expectThat(s.getRefAt(1)).equals("x");
				expectThat(s.getRefAt(2)).equals("xxxx");
			}
	}
	
	//test method
	public final void test_getVarianceByDouble() {
		
		//setup
		final List<Double> list = new List<Double>(
			0.0,
			0.0,
			0.5,
			1.0,
			1.0
		);
		
		//execution and verification
		expectThat(list.getVarianceByDouble(e -> e.doubleValue())).equals(0.2);
	}
	
	//test method
	public void test_isEmpty() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectThat(list.isEmpty());
	}
	
	//test method
	public void test_isEmpty_2() {
		
		//setup
		List<String> list = new List<String>("x");
		
		//execution and verification
		expectThatNot(list.isEmpty());
	}
	
	//test method
	public void test_matches() {
		
		//setup
			final List<String> list = new List<String>(
				"x",
				"xxxx",
				"x",
				"xxxx"
			);
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.addConditionForNext(s -> s.length() == 1)
			.addConditionForNext(s -> s.length() == 4)
			.addConditionForNext(s -> s.length() == 1)
			.addConditionForNext(s -> s.length() == 4);
		
		//execution and verification
		expectThat(list.matches(sequencePattern));
	}
	
	//test method
	public void test_matches_2() {
		
		//setup
			final List<String> list = new List<String>();
			list.addAtEnd(
				"x",
				"xxxx",
				"x",
				"xxxx"
			);
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.addConditionForNext(s -> s.length() == 1)
			.addConditionForNext(s -> s.length() == 4)
			.addBlankForNext()
			.addBlankForNext();
		
		//execution and verification
		expectThat(list.matches(sequencePattern));
	}
	
	//test method
	public void test_matches_3() {
		
		//setup
			final List<String> list = new List<String>();
			Sequencer.forCount(10).run(() -> list.addAtEnd("x"));
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.forNext(10).addBlank();
		
		//execution and verification
		expectThat(list.matches(sequencePattern));
	}
	
	//test method
	public void test_sort() {
		
		//setup
		final List<String> list = new List<String>(
			"xxxxxx",
			"xxxxx",
			"xxxx",
			"xxx",
			"xx",
			"x"
		);
		
		//execution
		list.sort(s -> s.length());
		
		//verification
			expectThat(list.getElementCount(), 6);
			
			expectThat(list.getRefAt(1)).equals("x");
			expectThat(list.getRefAt(2)).equals("xx");
			expectThat(list.getRefAt(3)).equals("xxx");
			expectThat(list.getRefAt(4)).equals("xxxx");
			expectThat(list.getRefAt(5)).equals("xxxxx");
			expectThat(list.getRefAt(6)).equals("xxxxxx");
	}
	
	//test method
	public void test_sort_2() {
		
		//setup
		final List<String> list = new List<String>(
			"python",
			"elephant",
			"zebra",
			"lion",
			"shark",
			"jaguar"
		);
		
		//execution
		list.sort(s -> s);
		
		//verification
			expectThat(list.getElementCount(), 6);
			
			expectThat(list.getRefAt(1)).equals("elephant");
			expectThat(list.getRefAt(2)).equals("jaguar");
			expectThat(list.getRefAt(3)).equals("lion");
			expectThat(list.getRefAt(4)).equals("python");
			expectThat(list.getRefAt(5)).equals("shark");
			expectThat(list.getRefAt(6)).equals("zebra");
	}
	
	//test method
	public void test_toArray() {
		
		//setup
		final List<String> list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution
		final Object[] array = list.toArray();
		
		//verification
			expectThat(array.length).equals(6);
			
			expectThat(array[0]).equals("x");
			expectThat(array[1]).equals("xx");
			expectThat(array[2]).equals("xxx");
			expectThat(array[3]).equals("xxxx");
			expectThat(array[4]).equals("xxxxx");
			expectThat(array[5]).equals("xxxxxx");
	}
	
	//test method
	public void test_toIntArray() {
		
		//setup
		final List<String> list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution
		final int[] array = list.toIntArray(s -> s.length());
		
		//verification
			expectThat(array.length).equals(6);
			
			expectThat(array[0]).equals(1);
			expectThat(array[1]).equals(2);
			expectThat(array[2]).equals(3);
			expectThat(array[3]).equals(4);
			expectThat(array[4]).equals(5);
			expectThat(array[5]).equals(6);
	}
	
	//test method
	public void test_toString() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectThat(list.toString()).isEmpty();
	}
	
	//test method
	public void test_toString_2() {
		
		//setup
		final List<String> list = new List<String>(
			"elephant",
			"jaguar",
			"lion",
			"python",
			"shark",
			"zebra"
		);
		
		//execution and verification
		expectThat(list.toString()).equals("elephant,jaguar,lion,python,shark,zebra");
	}
}
