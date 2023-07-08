//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class ReadContainerTest extends ContainerTest {
	
	//method
	@TestCase
	public void testCase_containsEqualing() {
		
		//setup
		final String[] array1 = {"apple", "banana", "cerish"};
		final String[] array2 = {"elephant", "lion", "monkey"};
		final String[] array3 = {"flower", "tree", "palm"};
		
		//execution
		final var readContainer = ReadContainer.forArrays(array1, array2, array3);
		
		//execution
		expect(readContainer.containsEqualing("apple"));
		expect(readContainer.containsEqualing("banana"));
		expect(readContainer.containsEqualing("cerish"));
		expect(readContainer.containsEqualing("elephant"));
		expect(readContainer.containsEqualing("lion"));
		expect(readContainer.containsEqualing("monkey"));
		expect(readContainer.containsEqualing("flower"));
		expect(readContainer.containsEqualing("tree"));
		expect(readContainer.containsEqualing("palm"));
		expectNot(readContainer.containsEqualing("jupiter"));
		expectNot(readContainer.containsEqualing("saturn"));
		expectNot(readContainer.containsEqualing("uranus"));
	}
	
	//method
	@TestCase
	public void testCase_getOriAt1BasedIndex() {
		
		//setup
		final String[] array1 = {"apple", "banana", "cerish"};
		final String[] array2 = {"elephant", "lion", "monkey"};
		final String[] array3 = {"flower", "tree", "palm"};
		
		//execution
		final var readContainer = ReadContainer.forArrays(array1, array2, array3);
		
		//verification
		expect(readContainer.getOriAt1BasedIndex(1)).isEqualTo("apple");
		expect(readContainer.getOriAt1BasedIndex(2)).isEqualTo("banana");
		expect(readContainer.getOriAt1BasedIndex(3)).isEqualTo("cerish");
		expect(readContainer.getOriAt1BasedIndex(4)).isEqualTo("elephant");
		expect(readContainer.getOriAt1BasedIndex(5)).isEqualTo("lion");
		expect(readContainer.getOriAt1BasedIndex(6)).isEqualTo("monkey");
		expect(readContainer.getOriAt1BasedIndex(7)).isEqualTo("flower");
		expect(readContainer.getOriAt1BasedIndex(8)).isEqualTo("tree");
		expect(readContainer.getOriAt1BasedIndex(9)).isEqualTo("palm");
	}
	
	//method
	@TestCase
	public void testCase_getOriSelected() {
		
		//setup
		final String[] array1 = { "A", "AA", "AAA" };
		final String[] array2 = { "B", "BB", "BBB" };
		final String[] array3 = { "C", "CC", "CCC" };
		
		//execution
		final var readContainer = ReadContainer.forArrays(array1, array2, array3);
		
		//verification
		expect(readContainer.getOriSelected(s -> s.length() == 1).toString()).isEqualTo("A,B,C");
		expect(readContainer.getOriSelected(s -> s.length() == 2).toString()).isEqualTo("AA,BB,CC");
		expect(readContainer.getOriSelected(s -> s.length() == 3).toString()).isEqualTo("AAA,BBB,CCC");
	}
	
	//method
	@Override
	protected <E> IContainer<E> createContainerWithElements(
		final E element,
		final @SuppressWarnings("unchecked")E... elements
	) {
		return ReadContainer.withElements(element, elements);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
		return new ReadContainer<>();
	}
}
