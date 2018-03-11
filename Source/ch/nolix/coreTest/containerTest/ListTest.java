//package declaration
package ch.nolix.coreTest.containerTest;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.container.List;
import ch.nolix.core.container.Pair;
import ch.nolix.core.container.SequencePattern;
import ch.nolix.core.container.SubContainer;
import ch.nolix.core.sequencer.Sequencer;
import ch.nolix.primitive.invalidStateException.UnexistingAttributeException;
import ch.nolix.primitive.test2.Test;

//test class
/**
 * This class is a test class for the List class.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 620
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
		expect(list.isEmpty());
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
	
	//test method
	public void test_contains_2() {
		
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
		expect(list.contains((s1, s2) -> s1.length() == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 1 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 2 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 3 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 4 == s2.length()));
		expect(list.contains((s1, s2) -> s1.length() + 5 == s2.length()));
		expectNot(list.contains((s1, s2) -> s1.length() + 6 == s2.length()));
	}
	
	//test method
	public void test_containsOne() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expectNot(list.containsOne());
	}
	
	//test method
	public void test_containsOne_2() {
		
		//setup
		final List<String> list = new List<String>("x");
		
		//execution and verification
		expect(list.containsOne());
	}
	
	//test method
	public void test_containsOne_3() {
		
		//setup
		final List<String> list = new List<String>("x", "x");
		
		//executation and verification
		expectNot(list.containsOne());
	}
	
	//test method
	public void test_containsOne_4() {
		
		//setup
		final List<String> list
		= new List<String>("x",  "xx", "xx", "xx", "xx", "xx");
		
		//execution and verification
		expect(list.containsOne(s -> s.length() == 1));
		expectNot(list.containsOne(s -> s.length() == 2));
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
			expect(list2.getElementCount()).isEqualTo(list1.getElementCount());
			
			//Iterates list1.
			for (int i = 1; i <= list1.getElementCount(); i++) {
				expect(list2.getRefAt(i)).isEqualTo(list1.getRefAt(i));
			}
	}
	
	//test method
	public void test_getElementCount() {
		
		//setup
		final List<String> list = new List<String>("x", "x", "x", "x", "x", "x");
		
		//execution and verification
		expect(list.getElementCount()).isEqualTo(6);
	}
	
	//test method
	public void test_getElementCount_2() {
		
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
		expect(list.getElementCount(e -> e.length() > 0)).isEqualTo(6);
		expect(list.getElementCount(e -> e.length() > 3)).isEqualTo(3);
		expect(list.getElementCount(e -> e.length() > 6)).isZero();
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
		expect(list.getRefByMax(s -> s)).isEqualTo("toast");
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
		expect(list.getRefByMaxInt(s -> s.length())).isEqualTo("xxxxxx");
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
		expect(list.getRefByMin(s -> s)).isEqualTo("cake");
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
		expect(list.getRefByMinInt(s -> s.length())).isEqualTo("x");
	}
	
	//method
	public void test_getRefFirst() {
		
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
		final Pair<String, String> pair = list.getRefFirst((s1, s2) -> s1.length() + 5 == s2.length());
		
		//verification
		expect(pair.getRefElement1()).isEqualTo("x");
		expect(pair.getRefElement2()).isEqualTo("xxxxxx");
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
			expect(sequences.getElementCount()).isEqualTo(3);
			
			//Iterates the sequences.
			for (final List<String> s : sequences) {
				expect(s.getElementCount()).isEqualTo(2);
				expect(s.getRefAt(1)).isEqualTo("x");
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
			expect(sequences.getElementCount()).isEqualTo(4);
			
			//Iterates the sequences.
			for (final List<String> s : sequences) {
				expect(s.getElementCount()).isEqualTo(2);
				expect(s.getRefAt(1)).isEqualTo("x");
				expect(s.getRefAt(2)).isEqualTo("xxxx");
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
		expect(list.getVarianceByDouble(e -> e.doubleValue())).isEqualTo(0.2);
	}
	
	//test method
	public void test_isEmpty() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expect(list.isEmpty());
	}
	
	//test method
	public void test_isEmpty_2() {
		
		//setup
		List<String> list = new List<String>("x");
		
		//execution and verification
		expectNot(list.isEmpty());
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
		expect(list.matches(sequencePattern));
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
			expect(list.matches(sequencePattern));
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
			expect(list.matches(sequencePattern));
	}
	
	//test methods
	public void test_skipFirstElements() {
		
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
		final SubContainer<String> subList = list.skipFirstElements(3);
		
		//verification
			expect(subList.getElementCount()).isEqualTo(3);
			
			final Iterator<String> iterator = subList.iterator();
			
			expect(iterator.hasNext());
			expect(iterator.next()).isEqualTo("xxxx");
			expect(iterator.hasNext());
			expect(iterator.next()).isEqualTo("xxxxx");
			expect(iterator.hasNext());
			expect(iterator.next()).isEqualTo("xxxxxx");
			expectNot(iterator.hasNext());
			
			expect(() -> iterator.next()).
			throwsExceptionOfType(UnexistingAttributeException.class);
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
			expect(list.getElementCount()).isEqualTo(6);
			
			expect(list.getRefAt(1)).isEqualTo("x");
			expect(list.getRefAt(2)).isEqualTo("xx");
			expect(list.getRefAt(3)).isEqualTo("xxx");
			expect(list.getRefAt(4)).isEqualTo("xxxx");
			expect(list.getRefAt(5)).isEqualTo("xxxxx");
			expect(list.getRefAt(6)).isEqualTo("xxxxxx");
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
			expect(list.getElementCount()).isEqualTo(6);
			
			expect(list.getRefAt(1)).isEqualTo("elephant");
			expect(list.getRefAt(2)).isEqualTo("jaguar");
			expect(list.getRefAt(3)).isEqualTo("lion");
			expect(list.getRefAt(4)).isEqualTo("python");
			expect(list.getRefAt(5)).isEqualTo("shark");
			expect(list.getRefAt(6)).isEqualTo("zebra");
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
			expect(array.length).isEqualTo(6);
			
			expect(array[0]).isEqualTo("x");
			expect(array[1]).isEqualTo("xx");
			expect(array[2]).isEqualTo("xxx");
			expect(array[3]).isEqualTo("xxxx");
			expect(array[4]).isEqualTo("xxxxx");
			expect(array[5]).isEqualTo("xxxxxx");
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
			expect(array.length).isEqualTo(6);
			
			expect(array[0]).isEqualTo(1);
			expect(array[1]).isEqualTo(2);
			expect(array[2]).isEqualTo(3);
			expect(array[3]).isEqualTo(4);
			expect(array[4]).isEqualTo(5);
			expect(array[5]).isEqualTo(6);
	}
	
	//test method
	public void test_toString() {
		
		//setup
		final List<String> list = new List<String>();
		
		//execution and verification
		expect(list.toString()).isEmpty();
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
		expect(list.toString()).isEqualTo("elephant,jaguar,lion,python,shark,zebra");
	}
}
