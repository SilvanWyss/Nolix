//package declaration
package ch.nolix.commonTest.containersTest;

//Java import
import java.util.Iterator;

import ch.nolix.common.containers.List;
import ch.nolix.common.containers.SequencePattern;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.NullArgumentException;
import ch.nolix.common.pair.Pair;
import ch.nolix.common.sequencer.Sequencer;
import ch.nolix.common.test.Test;

//class
/**
 * A {@link ListTest} is a test for {@link List}.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 660
 */
public final class ListTest extends Test {
	
	//test case
	public void testCase_addAtBegin() {
		
		//setup
		final var list = new List<String>();
		
		//execution & verification
		final String string = null;
		expect(() -> list.addAtBegin(string))
		.throwsException()
		.ofType(NullArgumentException.class);
	}
	
	//test case
	public void testCase_addAtEnd() {
		
		//setup
		final var list = new List<String>();
		
		//execution & verification
		final String string = null;
		expect(() -> list.addAtEnd(string))
		.throwsException()
		.ofType(NullArgumentException.class);
	}
	
	//test case
	public void testCase_clear() {
		
		//setup
		final var list = new List<String>(
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
		expect(list.isEmpty());
	}
	
	//test case
	public void testCase_contains() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution & verification
			expect(
				list.contains(s -> s.equals("x")),
				list.contains(s -> s.equals("xx")),
				list.contains(s -> s.equals("xxx")),
				list.contains(s -> s.equals("xxxx")),
				list.contains(s -> s.equals("xxxxx")),
				list.contains(s -> s.equals("xxxxxx"))
			);
			
			expectNot(
				list.contains(s -> s.equals("xxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxxxx")),
				list.contains(s -> s.equals("xxxxxxxxxxxx"))
			);
	}
	
	//test case
	public void testCase_contains_2() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution & verification
		expect(list.contains((s1, s2) -> s1.length() == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 1 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 2 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 3 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 4 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 5 == s2.length()));
		expectNot(list.contains((s1, s2) -> s1.length() + 6 == s2.length()));
	}
	
	//test case
	public void testCase_containsOne() {
		
		//setup
		final var list = new List<String>();
		
		//execution & verification
		expectNot(list.containsOne());
	}
	
	//test case
	public void testCase_containsOne_2() {
		
		//setup
		final var list = new List<String>("x");
		
		//execution & verification
		expect(list.containsOne());
	}
	
	//test case
	public void testCase_containsOne_3() {
		
		//setup
		final var list = new List<String>("x", "x");
		
		//executation and verification
		expectNot(list.containsOne());
	}
	
	//test case
	public void testCase_containsOne_4() {
		
		//setup
		final var list
		= new List<String>("x", "xx", "xx", "xx", "xx", "xx");
		
		//execution & verification
		expect(list.containsOne(s -> s.length() == 1));
		expectNot(list.containsOne(s -> s.length() == 2));
	}
	
	//test case
	public void testCase_forEach() {
		
		//setup
			final var list1 = new List<String>(
				"x",
				"xx",
				"xxx",
				"xxxx",
				"xxxxx",
				"xxxxxx"
			);
			
			final var list2 = new List<String>();
		
		//execution
		list1.forEach(s -> list2.addAtEnd(s));
		
		//verification
			expect(list2.getSize()).isEqualTo(list1.getSize());
			
			//Iterates list1.
			for (int i = 1; i <= list1.getSize(); i++) {
				expect(list2.getRefAt(i)).isEqualTo(list1.getRefAt(i));
			}
	}
	
	//test cases
	public void testCase_getContainerFrom() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution
		final var subList = list.from(4);
		
		//verification
			expect(subList.getSize()).isEqualTo(3);
			
			final Iterator<String> iterator = subList.iterator();
			
			expect(iterator.hasNext());
			expect(iterator.next()).isEqualTo("xxxx");
			expect(iterator.hasNext());
			expect(iterator.next()).isEqualTo("xxxxx");
			expect(iterator.hasNext());
			expect(iterator.next()).isEqualTo("xxxxxx");
			expectNot(iterator.hasNext());
			
			expect(() -> iterator.next())
			.throwsException()
			.ofType(ArgumentDoesNotHaveAttributeException.class);
	}
	
	//test case
	public void testCase_getContainerWithoutFirst() {
	
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution
		final var subList = list.withoutFirst();
		
		//verification
		expect(subList.getSize()).isEqualTo(5);
		expect(!subList.contains("x"));
	}
	
	//test case
	public void testCase_getRefByMax() {
		
		//setup
		final var list = new List<String>(
			"cake",
			"chocolate",
			"ice cream",
			"milk",
			"meat",
			"pizza",
			"sandwich",
			"toast"	
		);
		
		//execution & verification
		expect(list.getRefByMax(s -> s)).isEqualTo("toast");
	}
	
	//test case
	public void testCase_getRefByMaxInt() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution & verification
		expect(list.getRefByMaxInt(s -> s.length())).isEqualTo("xxxxxx");
	}
	
	//test case
	public void testCase_getRefByMin() {
		
		//setup
		final var list = new List<String>(
			"cake",
			"chocolate",
			"ice cream",
			"milk",
			"meat",
			"pizza",
			"sandwich",
			"toast"
		);
		
		//execution & verification
		expect(list.getRefByMin(s -> s)).isEqualTo("cake");
	}
	
	//test case
	public void testCase_getRefByMinInt() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution & verification
		expect(list.getRefByMinInt(s -> s.length())).isEqualTo("x");
	}
	
	//method
	public void testCase_getRefFirst() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution
		final Pair<String, String> pair = list.getRefFirst((s1, s2) -> s1.length() + 5 == s2.length());
		
		//verification
		expect(pair.getRefElement1()).isEqualTo("x");
		expect(pair.getRefElement2()).isEqualTo("xxxxxx");
	}
	
	//test case
	public void testCase_getSequences() {
		
		//setup
			final var list = new List<String>(
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
			expect(sequences.getSize()).isEqualTo(3);
			
			//Iterates the sequences.
			for (final var s : sequences) {
				expect(s.getSize()).isEqualTo(2);
				expect(s.getRefAt(1)).isEqualTo("x");
			}
	}
	
	//test case
	public void testCase_getSequences_2() {
		
		//setup
			final var list = new List<String>(
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
			expect(sequences.getSize()).isEqualTo(4);
			
			//Iterates the sequences.
			for (final var s : sequences) {
				expect(s.getSize()).isEqualTo(2);
				expect(s.getRefAt(1)).isEqualTo("x");
				expect(s.getRefAt(2)).isEqualTo("xxxx");
			}
	}
	
	//test case
	public void testCase_getSize() {
		
		//setup
		final var list = new List<String>("x", "x", "x", "x", "x", "x");
		
		//execution & verification
		expect(list.getSize()).isEqualTo(6);
	}
	
	//test case
	public void testCase_getSize_2() {
		
		//setup
		final var list = new List<String>(
			"x",
			"xx",
			"xxx",
			"xxxx",
			"xxxxx",
			"xxxxxx"
		);
		
		//execution & verification
		expect(list.getCount(e -> e.length() > 0)).isEqualTo(6);
		expect(list.getCount(e -> e.length() > 3)).isEqualTo(3);
		expect(list.getCount(e -> e.length() > 6)).isZero();
	}
	
	//test case
	public final void testCase_getVarianceByDouble() {
		
		//setup
		final var list = new List<Double>(
			0.0,
			0.0,
			0.5,
			1.0,
			1.0
		);
		
		//execution & verification
		expect(list.getVarianceByDouble(e -> e.doubleValue())).isEqualTo(0.2);
	}
	
	//test case
	public void testCase_isEmpty() {
		
		//setup
		final var list = new List<String>();
		
		//execution & verification
		expect(list.isEmpty());
	}
	
	//test case
	public void testCase_isEmpty_2() {
		
		//setup
		final var list = new List<String>("x");
		
		//execution & verification
		expectNot(list.isEmpty());
	}
	
	//test case
	public void testCase_matches() {
		
		//setup
			final var list = new List<String>(
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
		
		//execution & verification
		expect(list.matches(sequencePattern));
	}
	
	//test case
	public void testCase_matches_2() {
		
		//setup
			final var list = new List<String>();
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
		
		//execution & verification
			expect(list.matches(sequencePattern));
	}
	
	//test case
	public void testCase_matches_3() {
		
		//setup
			final var list = new List<String>();
			Sequencer.forCount(10).run(() -> list.addAtEnd("x"));
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.forNext(10).addBlank();
		
		//execution & verification
			expect(list.matches(sequencePattern));
	}
	
	//test case
	public void testCase_sort() {
		
		//setup
		final var list = new List<String>(
			"xxxxxx",
			"xxxxx",
			"xxxx",
			"xxx",
			"xx",
			"x"
		);
		
		//execution
		list.order(s -> s.length());
		
		//verification
			expect(list.getSize()).isEqualTo(6);
			
			expect(list.getRefAt(1)).isEqualTo("x");
			expect(list.getRefAt(2)).isEqualTo("xx");
			expect(list.getRefAt(3)).isEqualTo("xxx");
			expect(list.getRefAt(4)).isEqualTo("xxxx");
			expect(list.getRefAt(5)).isEqualTo("xxxxx");
			expect(list.getRefAt(6)).isEqualTo("xxxxxx");
	}
	
	//test case
	public void testCase_sort_2() {
		
		//setup
		final var list = new List<String>(
			"python",
			"elephant",
			"zebra",
			"lion",
			"shark",
			"jaguar"
		);
		
		//execution
		list.order(s -> s);
		
		//verification
			expect(list.getSize()).isEqualTo(6);
			
			expect(list.getRefAt(1)).isEqualTo("elephant");
			expect(list.getRefAt(2)).isEqualTo("jaguar");
			expect(list.getRefAt(3)).isEqualTo("lion");
			expect(list.getRefAt(4)).isEqualTo("python");
			expect(list.getRefAt(5)).isEqualTo("shark");
			expect(list.getRefAt(6)).isEqualTo("zebra");
	}
	
	//test case
	public void testCase_toArray() {
		
		//setup
		final var list = new List<String>(
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
			expect(array.length).isEqualTo(6);
			
			expect(array[0]).isEqualTo("x");
			expect(array[1]).isEqualTo("xx");
			expect(array[2]).isEqualTo("xxx");
			expect(array[3]).isEqualTo("xxxx");
			expect(array[4]).isEqualTo("xxxxx");
			expect(array[5]).isEqualTo("xxxxxx");
	}
	
	//test case
	public void testCase_toIntArray() {
		
		//setup
		final var list = new List<String>(
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
			expect(array.length).isEqualTo(6);
			
			expect(array[0]).isEqualTo(1);
			expect(array[1]).isEqualTo(2);
			expect(array[2]).isEqualTo(3);
			expect(array[3]).isEqualTo(4);
			expect(array[4]).isEqualTo(5);
			expect(array[5]).isEqualTo(6);
	}
	
	//test case
	public void testCase_toString() {
		
		//setup
		final var list = new List<String>();
		
		//execution & verification
		expect(list.toString()).isEmpty();
	}
	
	//test case
	public void testCase_toString_2() {
		
		//setup
		final var list = new List<String>(
			"elephant",
			"jaguar",
			"lion",
			"python",
			"shark",
			"zebra"
		);
		
		//execution & verification
		expect(list.toString()).isEqualTo("elephant,jaguar,lion,python,shark,zebra");
	}
}
