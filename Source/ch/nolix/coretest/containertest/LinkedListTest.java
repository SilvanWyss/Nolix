//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.SequencePattern;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.function.FunctionCatalogue;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

//class
public final class LinkedListTest extends ContainerTest {
	
	//method
	@TestCase
	public void testCase_addAtBegin() {
		
		//setup
		final var testUnit = LinkedList.withElements("a1", "a2", "a3", "a4");
		final var list = LinkedList.withElements("b1", "b2");
		
		//execution
		testUnit.addAtBegin(list);
		
		//verification
		expect(testUnit.getElementCount()).isEqualTo(6);
		expect(testUnit.getRefAt1BasedIndex(1)).isEqualTo("b1");
		expect(testUnit.getRefAt1BasedIndex(2)).isEqualTo("b2");
		expect(testUnit.getRefAt1BasedIndex(3)).isEqualTo("a1");
		expect(testUnit.getRefAt1BasedIndex(4)).isEqualTo("a2");
		expect(testUnit.getRefAt1BasedIndex(5)).isEqualTo("a3");
		expect(testUnit.getRefAt1BasedIndex(6)).isEqualTo("a4");
	}
	
	//method
	@TestCase
	public void testCase_addAtBegin_whenGivenListIsEmpty() {
		
		//setup
		final var testUnit = LinkedList.withElements("a1", "a2", "a3", "a4");
		final var list = new LinkedList<String>();
		
		//execution
		testUnit.addAtBegin(list);
		
		//verification
		expect(testUnit.getElementCount()).isEqualTo(4);
		expect(testUnit.getRefAt1BasedIndex(1)).isEqualTo("a1");
		expect(testUnit.getRefAt1BasedIndex(2)).isEqualTo("a2");
		expect(testUnit.getRefAt1BasedIndex(3)).isEqualTo("a3");
		expect(testUnit.getRefAt1BasedIndex(4)).isEqualTo("a4");
	}
	
	//method
	@TestCase
	public void testCase_addAtBegin_whenIsEmpty() {
		
		//setup
		final var testUnit = new LinkedList<String>();
		final var list = LinkedList.withElements("b1", "b2");
		
		//execution
		testUnit.addAtBegin(list);
		
		//verification
		expect(testUnit.getElementCount()).isEqualTo(2);
		expect(testUnit.getRefAt1BasedIndex(1)).isEqualTo("b1");
		expect(testUnit.getRefAt1BasedIndex(2)).isEqualTo("b2");
	}
	
	//method
	@TestCase
	public void testCase_addAtBegin_whenTheGivenElementIsNull() {
		
		//setup
		final var testUnit = new LinkedList<>();
		final Object nullElement = null;
		
		//execution & verification
		expectRunning(() -> testUnit.addAtBegin(nullElement))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given element is null.");
	}
	
	//method
	@TestCase
	public void testCase_addAtEnd_whenTheGivenElementIsNull() {
		
		//setup
		final var testUnit = LinkedList.withElements();
		final Object nullElement = null;
		
		//execution & verification
		expectRunning(() -> testUnit.addAtEnd(nullElement))
		.throwsException()
		.ofType(ArgumentIsNullException.class)
		.withMessage("The given element is null.");
	}
	
	//method
	@TestCase
	public void testCase_clear() {
		
		//setup
		final var testUnit = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		testUnit.clear();
		
		//verification
		expect(testUnit.isEmpty());
	}
	
	//method
	@TestCase
	public void testCase_forEach() {
		
		//setup
		final var testUnit = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		final var list = new LinkedList<String>();
		
		//execution
		testUnit.forEach(list::addAtEnd);
		
		//verification
		expect(list.getElementCount()).isEqualTo(6);
		for (var i = 1; i <= 6; i++) {
			expect(testUnit.getRefAt1BasedIndex(i)).isEqualTo(list.getRefAt1BasedIndex(i));
		}
	}
	
	//method
	@TestCase
	public void testCase_getRefFirst_withI2ElementTakerBooleanGetter() {
		
		//setup
		final var testUnit = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.getRefFirst((e1, e2) -> e1.length() + 2 == e2.length());
		
		//verification
		expect(result.getRefElement1()).isEqualTo("x");
		expect(result.getRefElement2()).isEqualTo("xxx");
	}
	
	//method
	@TestCase
	public void testCase_getSequences_1A() {
		
		//setup
			final var testUnit = LinkedList.withElements("x", "a", "x", "b", "x", "c");
			
			final SequencePattern<String> sequencePattern =
			new SequencePattern<String>()
			.addConditionForNext(e -> e.equals("x"))
			.addBlankForNext();
		
		//execution
		final var result = testUnit.getSequences(sequencePattern);
		
		//verification
			expect(result.getElementCount()).isEqualTo(3);
			
			for (final var e : result) {
				expect(e.getElementCount()).isEqualTo(2);
				expect(e.getRefAt1BasedIndex(1)).isEqualTo("x");
			}
	}
		
	//method
	@TestCase
	public void testCase_getSequences_1B() {
		
		//setup
			final var testUnit = LinkedList.withElements(
				"x",
				"x",
				"xxxx",
				"x",
				"x",
				"xxxx",
				"x",
				"x",
				"x",
				"x",
				"xxxx",
				"x",
				"x",
				"x",
				"x",
				"xxxx"
			);
			
			final SequencePattern<String> sequencePattern =
			new SequencePattern<String>()
			.addConditionForNext(e -> e.equals("x"))
			.addConditionForNext(e -> e.equals("xxxx"));
		
		//execution
		final var result = testUnit.getSequences(sequencePattern);
		
		//verification
		expect(result.getElementCount()).isEqualTo(4);
		for (final var e : result) {
			expect(e.getElementCount()).isEqualTo(2);
			expect(e.getRefAt1BasedIndex(1)).isEqualTo("x");
			expect(e.getRefAt1BasedIndex(2)).isEqualTo("xxxx");
		}
	}
	
	//method
	@TestCase
	public void testCase_getVarianceByDouble() {
		
		//setup
		final var testUnit = LinkedList.withElements(0.0, 0.0, 0.5,	1.0, 1.0);
		
		//execution
		final var result = testUnit.getVarianceByDouble(FunctionCatalogue::getSelf);
		
		//verification
		expect(result).isEqualTo(0.2);
	}
	
	
	//method
	@TestCase
	public void testCase_matches_1A() {
		
		//setup
			final var testUnit = LinkedList.withElements("x", "xxxx", "x", "xxxx");
			
			final var sequencePattern =
			new SequencePattern<String>()
			.addConditionForNext(s -> s.length() == 1)
			.addConditionForNext(s -> s.length() == 4)
			.addConditionForNext(s -> s.length() == 1)
			.addConditionForNext(s -> s.length() == 4);
		
		//execution
		final var result = testUnit.matches(sequencePattern);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_matches_1B() {
		
		//setup
			final var testUnit = LinkedList.withElements("x", "xxxx", "x", "xxxx");
			
			final SequencePattern<String> sequencePattern
			= new SequencePattern<String>()
			.addConditionForNext(s -> s.length() == 1)
			.addConditionForNext(s -> s.length() == 4)
			.addBlankForNext()
			.addBlankForNext();
		
		//execution
		final var result = testUnit.matches(sequencePattern);
		
		//verification
		expect(result);
	}
	
	//method
	@TestCase
	public void testCase_toArray() {
		
		//setup
		final var testUnit = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.toArray();
		
		//verification
		expect(result.length).isEqualTo(6);
		expect(result[0]).isEqualTo("x");
		expect(result[1]).isEqualTo("xx");
		expect(result[2]).isEqualTo("xxx");
		expect(result[3]).isEqualTo("xxxx");
		expect(result[4]).isEqualTo("xxxxx");
		expect(result[5]).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public void testCase_toOrderedList_1A() {
		
		//setup
		final var testUnit = LinkedList.withElements("xxxxxx", "xxxxx", "xxxx","xxx", "xx", "x");
		
		//execution
		final var result = testUnit.toOrderedList(String::length);
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("x");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxx");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("xxxx");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("xxxxx");
		expect(result.getRefAt1BasedIndex(6)).isEqualTo("xxxxxx");
	}
	
	//method
	@TestCase
	public void testCase_toOrderedList_1B() {
		
		//setup
		final var testUnit = LinkedList.withElements("python", "elephant", "zebra", "lion", "shark", "jaguar");
		
		//execution
		final var result = testUnit.toOrderedList(FunctionCatalogue::getSelf);
		
		//verification
		expect(result.getElementCount()).isEqualTo(6);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("elephant");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("jaguar");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("lion");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("python");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("shark");
		expect(result.getRefAt1BasedIndex(6)).isEqualTo("zebra");
	}
	
	//method
	@TestCase
	public void testCase_toIntArray() {
		
		//setup
		final var testUnit = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.toIntArray(String::length);
		
		//verification
		expect(result.length).isEqualTo(6);
		expect(result[0]).isEqualTo(1);
		expect(result[1]).isEqualTo(2);
		expect(result[2]).isEqualTo(3);
		expect(result[3]).isEqualTo(4);
		expect(result[4]).isEqualTo(5);
		expect(result[5]).isEqualTo(6);
	}
	
	//method
	@TestCase
	public void testCase_toString() {
		
		//setup
		final var testUnit = LinkedList.withElements("elephant", "jaguar", "lion", "python", "shark", "zebra");
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEqualTo("elephant,jaguar,lion,python,shark,zebra");
	}
	
	//method
	@TestCase
	public void testCase_toString_whenLikedListIsEmpty() {
		
		//setup
		final var testUnit = new LinkedList<>();
		
		//execution
		final var result = testUnit.toString();
		
		//verification
		expect(result).isEmpty();
	}
	
	//method
	@TestCase
	public void testCase_withElements() {
		
		//execution
		final var result = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution & verification
			expect(
				result.containsAny(s -> s.equals("x")),
				result.containsAny(s -> s.equals("xx")),
				result.containsAny(s -> s.equals("xxx")),
				result.containsAny(s -> s.equals("xxxx")),
				result.containsAny(s -> s.equals("xxxxx")),
				result.containsAny(s -> s.equals("xxxxxx"))
			);
			
			expectNot(
				result.containsAny(s -> s.equals("xxxxxxx")),
				result.containsAny(s -> s.equals("xxxxxxxx")),
				result.containsAny(s -> s.equals("xxxxxxxxx")),
				result.containsAny(s -> s.equals("xxxxxxxxxx")),
				result.containsAny(s -> s.equals("xxxxxxxxxxx")),
				result.containsAny(s -> s.equals("xxxxxxxxxxxx"))
			);
	}
	
	//method
	@TestCase
	public void testCase_withoutFirst() {
	
		//setup
		final var testUnit = LinkedList.withElements("x", "xx",	"xxx", "xxxx", "xxxxx",	"xxxxxx");
		
		//execution
		final var result = testUnit.withoutFirst();
		
		//verification
		expect(result.getElementCount()).isEqualTo(5);
		expect(result.getRefAt1BasedIndex(1)).isEqualTo("xx");
		expect(result.getRefAt1BasedIndex(2)).isEqualTo("xxx");
		expect(result.getRefAt1BasedIndex(3)).isEqualTo("xxxx");
		expect(result.getRefAt1BasedIndex(4)).isEqualTo("xxxxx");
		expect(result.getRefAt1BasedIndex(5)).isEqualTo("xxxxxx");
	}
	
	//method
	@Override
	protected <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements) {
		return LinkedList.fromArray(elements);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerOfType(final Class<E> type) {
		return new LinkedList<>();
	}
}
