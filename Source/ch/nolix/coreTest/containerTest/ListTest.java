/*
 * file:	ListTest.java
 * author:	Silvan Wyss
 * month:	2015
 * lines:	480
 */

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
 */
public final class ListTest extends Test {
		
	//test method
	public void testClear() {
		
		//setup
		final List<String> list = new List<String>();	
		Sequencer.forCount(10).run(() -> list.addAtEnd("x"));
		
		//execution
		list.clear();
		
		//verification
		expectThat(list.isEmpty());
	}
	
	//test method
	public void testContains() {
		
		//setup
		final List<String> list = new List<String>()
		.addAtEnd(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution and verification part 1
		expectThat(
			list.contains(s -> s.equals("x")),
			list.contains(s -> s.equals("xx")),
			list.contains(s -> s.equals("xxx")),
			list.contains(s -> s.equals("xxxx")),
			list.contains(s -> s.equals("xxxxx")),
			list.contains(s -> s.equals("xxxxxx"))
		);
		
		//execution and verification part 2
		expectThatNot(
			list.contains(s -> s.equals("xxxxxxx")),
			list.contains(s -> s.equals("xxxxxxxx")),
			list.contains(s -> s.equals("xxxxxxxxx")),
			list.contains(s -> s.equals("xxxxxxxxxx"))
		);
	}
	
	//test method
	public void testContainsOnce() {
		
		//setup
		final List<String> list = new List<String>();
		list.addAtEnd("x");
		Sequencer.forCount(10).run(() -> list.addAtEnd("y"));
		
		//execution and verification part 1
		expectThat(list.containsOne(s -> s.equals("x")));
		
		//execution and verification part 2
		expectThatNot(
			list.containsOne(s -> s.equals("y")),
			list.containsOne(s -> s.equals("z"))
		);
	}
	
	//test method
	public void testContainsOne1() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectThatNot(list.containsOne());
	}
	
	//test method
	public void testContainsOne2() {
		
		//setup
		final List<String> list = new List<String>().addAtEnd("x");
		
		//execution and verification
		expectThat(list.containsOne());
	}
	
	//test method
	public void testContainsOne3() {
		
		//setup
		final List<String> list = new List<String>();
		list.addAtEnd(
			"x",
			"x"
		);
		
		//executation and verification
		expectThatNot(list.containsOne());
	}
	
	//test method
	public void testForEach() {
		
		//setup
		final List<String> list1 = new List<String>();
		Sequencer.forCount(10).run(() -> list1.addAtEnd("x")); 
		
		//execution
		final List<String> list2 = new List<String>();
		list1.forEach(s -> list2.addAtEnd(s));
		
		//verification
		expectThat(list2.getSize()).equals(list1.getSize());
		for (int i = 1; i <= list1.getSize(); i++) {
			expectThat(list2.getRefAt(i)).equals(list2.getRefAt(i));
		}
	}
	
	//test method
	public void testGetRefByMax() {
		
		//setup
		final List<String> list = new List<String>();
		list.addAtEnd(
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
	public void testGetRefByMaxInt() {
		
		//setup
		final List<String> list = new List<String>();
		list.addAtEnd(
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
	public void testGetRefByMin() {
		
		//setup
		final List<String> list = new List<String>();
		list.addAtEnd(
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
	public void testGetRefByMinInt() {
		
		//setup
		final List<String> list = new List<String>()
		.addAtEnd(
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
	public void testGetSequences1() {
		
		//setup part 1
		final List<String> list = new List<String>()
		.addAtEnd(
			"x",
			"x",
			"xxxxx",
			"x",
			"x",
			"xxxxx",
			"x",
			"x",
			"x",
			"xxxxx",
			"x",
			"xxxxx"
		);
		
		//setup part 2
		final SequencePattern<String> sequencePattern = new SequencePattern<String>()
		.addConditionForNext(s -> s.equals("x"))
		.addConditionForNext(s -> s.equals("xxxxx"));
		
		//execution
		final List<List<String>> sequences = list.getSequences(sequencePattern);
		
		//verification
		expectThat(sequences.getSize()).equals(4);
		for (List<String> s: sequences) {
			expectThat(s.getSize()).equals(2);
			expectThat(s.getRefAt(1)).equals("x");
			expectThat(s.getRefAt(2)).equals("xxxxx");
		}
	}
	
	//test method
	public void testGetSequences2() {
		
		//setup part 1
		final List<String> list = new List<String>()
		.addAtEnd(
			"x",
			"a",
			"x",
			"b",
			"x",
			"c"
		);
		
		//setup part 2
		final SequencePattern<String> sequencePattern = new SequencePattern<String>()
		.addConditionForNext(s -> s.equals("x"))
		.addBlankForNext();
		
		//execution
		final List<List<String>> sequences = list.getSequences(sequencePattern);
		
		//verification
		expectThat(sequences.getSize()).equals(3);;
		for (List<String> s: sequences) {
			expectThat(s.getSize()).equals(2);
			expectThat(s.getRefAt(1)).equals("x");
		}
	}
	
	//test method
	public final void testGetVarianceByDouble() {
		
		//setup
		final List<Double> list = new List<Double>()
		.addAtEnd(
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
	public void testIsEmpty() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectThat(list.isEmpty());
	}
	
	//test method
	public void testIsEmpty2() {
		
		//setup
		List<String> list = new List<String>().addAtEnd("x");
		
		//execution and verification
		expectThatNot(list.isEmpty());
	}
	
	//test method
	public void testMatches1() {
		
		//setup part 1
		final List<String> list = new List<String>()
		.addAtEnd(
			"x",
			"xxxxx",
			"x",
			"xxxxx"
		);
		
		//setup part 2
		final SequencePattern<String> sequencePattern = new SequencePattern<String>()
		.addConditionForNext(s -> s.length() == 1)
		.addConditionForNext(s -> s.length() == 5)
		.addConditionForNext(s -> s.length() == 1)
		.addConditionForNext(s -> s.length() == 5);
		
		//execution and verification
		expectThat(sequencePattern.matches(list));
	}
	
	//test method
	public void testMatches2() {
		
		//setup part 1
		final List<String> list = new List<String>();
		list.addAtEnd(
			"x",
			"xxxxx",
			"x",
			"xxxxx"
		);
		
		//setup part 2
		final SequencePattern<String> sequencePattern = new SequencePattern<String>()
		.addConditionForNext(s -> s.length() == 1)
		.addConditionForNext(s -> s.length() == 5)
		.addBlankForNext()
		.addBlankForNext();
		
		//execution and verification
		expectThat(sequencePattern.matches(list));
	}
	
	//test method
	public void testMatches3() {
		
		//setup part 1
		final List<String> list = new List<String>();
		Sequencer.forCount(10).run(() -> list.addAtEnd("x"));
		
		//setup part 2
		final SequencePattern<String> sequencePattern = new SequencePattern<String>()
		.forNext(10).addBlank();
		
		//execution and verification
		expectThat(sequencePattern.matches(list));
	}
	
	//test method
	public void testSort() {
		
		//setup
		final List<String> list = new List<String>()
		.addAtEnd(
			"xxxxxx",
			"xxxxx",
			"xxxx",
			"x",
			"xx",
			"xxx"
		);
		
		//execution
		list.sort(s -> s.length());
		
		//verification
		expectThat(list.getRefAt(1)).equals("x");
		expectThat(list.getRefAt(2)).equals("xx");
		expectThat(list.getRefAt(3)).equals("xxx");
		expectThat(list.getRefAt(4)).equals("xxxx");
		expectThat(list.getRefAt(5)).equals("xxxxx");
		expectThat(list.getRefAt(6)).equals("xxxxxx");
	}
	
	//test method
	public void testToArray() {
		
		//setup
		final List<String> list = new List<String>()
		.addAtEnd(
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
	public void testToIntArray() {
		
		//setup
		final List<String> list = new List<String>()
		.addAtEnd(
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
	public void testToString1() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectThat(list.toString()).isEmpty();
	}
	
	//test method
	public void testToString2() {
		
		//setup
		final List<String> list = new List<String>();
		list.addAtEnd(
			"one",
			"two",
			"three",
			"four",
			"five",
			"six"
		);
		
		//execution and verification
		expectThat(list.toString()).equals("one,two,three,four,five,six");
	}
}
