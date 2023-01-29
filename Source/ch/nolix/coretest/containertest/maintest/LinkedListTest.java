//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.container.main.LinkedList;
import ch.nolix.core.container.main.SequencePattern;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

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
		final var testUnit = new LinkedList<>();
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
	@Override
	protected <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements) {
		return LinkedList.fromArray(elements);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
		return new LinkedList<>();
	}
}
