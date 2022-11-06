//package declaration
package ch.nolix.coretest.containertest;

//own imports
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.mainapi.IContainer;

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
		expect(readContainer.containsAnyEqualing("apple"));
		expect(readContainer.containsAnyEqualing("banana"));
		expect(readContainer.containsAnyEqualing("cerish"));
		expect(readContainer.containsAnyEqualing("elephant"));
		expect(readContainer.containsAnyEqualing("lion"));
		expect(readContainer.containsAnyEqualing("monkey"));
		expect(readContainer.containsAnyEqualing("flower"));
		expect(readContainer.containsAnyEqualing("tree"));
		expect(readContainer.containsAnyEqualing("palm"));
		expectNot(readContainer.containsAnyEqualing("jupiter"));
		expectNot(readContainer.containsAnyEqualing("saturn"));
		expectNot(readContainer.containsAnyEqualing("uranus"));
	}
	
	//method
	@TestCase
	public void testCase_getRefAt() {
		
		//setup
		final String[] array1 = {"apple", "banana", "cerish"};
		final String[] array2 = {"elephant", "lion", "monkey"};
		final String[] array3 = {"flower", "tree", "palm"};
		
		//execution
		final var readContainer = ReadContainer.forArrays(array1, array2, array3);
		
		//verification
		expect(readContainer.getRefAt1BasedIndex(1)).isEqualTo("apple");
		expect(readContainer.getRefAt1BasedIndex(2)).isEqualTo("banana");
		expect(readContainer.getRefAt1BasedIndex(3)).isEqualTo("cerish");
		expect(readContainer.getRefAt1BasedIndex(4)).isEqualTo("elephant");
		expect(readContainer.getRefAt1BasedIndex(5)).isEqualTo("lion");
		expect(readContainer.getRefAt1BasedIndex(6)).isEqualTo("monkey");
		expect(readContainer.getRefAt1BasedIndex(7)).isEqualTo("flower");
		expect(readContainer.getRefAt1BasedIndex(8)).isEqualTo("tree");
		expect(readContainer.getRefAt1BasedIndex(9)).isEqualTo("palm");
	}
	
	//method
	@TestCase
	public void testCase_getRefSelected() {
		
		//setup
		final String[] array1 = { "A", "AA", "AAA" };
		final String[] array2 = { "B", "BB", "BBB" };
		final String[] array3 = { "C", "CC", "CCC" };
		
		//execution
		final var readContainer = ReadContainer.forArrays(array1, array2, array3);
		
		//verification
		expect(readContainer.getRefSelected(s -> s.length() == 1).toString()).isEqualTo("A,B,C");
		expect(readContainer.getRefSelected(s -> s.length() == 2).toString()).isEqualTo("AA,BB,CC");
		expect(readContainer.getRefSelected(s -> s.length() == 3).toString()).isEqualTo("AAA,BBB,CCC");
	}
	
	//method
	@Override
	protected <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked")E... elements) {
		return ReadContainer.forArray(elements);
	}
	
	//method
	@Override
	protected <E> IContainer<E> createEmptyContainer() {
		return new ReadContainer<>();
	}
}
