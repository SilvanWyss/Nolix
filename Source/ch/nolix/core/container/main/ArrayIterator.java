//package declaration
package ch.nolix.core.container.main;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.validator.GlobalValidator;

//class
public final class ArrayIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> ArrayIterator<E2> forArray(final E2[] array) {
		return new ArrayIterator<>(array);
	}
	
	//attribute
	private final E[] parentArray;
	
	//attribute
	private int currentIndex;
	
	//constructor
	private ArrayIterator(final E[] parrentArray) {
		
		GlobalValidator.assertThat(parrentArray).thatIsNamed("parent array").isNotNull();
		
		this.parentArray = parrentArray; //NOSONAR: An ArrayIterator operates on the original instance.
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (currentIndex < parentArray.length);
	}
	
	//method
	@Override
	public E next() {
		
		assertHasNext();
		
		return nextWhenHasNext();
	}
	
	//method
	private void assertHasNext() throws NoSuchElementException {
		if (!hasNext()) {
			throw new NoSuchElementException("The current ArrayIterator does not have a next element.");
		}
	}
	
	//method
	private E nextWhenHasNext() {
		
		final var element = parentArray[currentIndex];
		
		currentIndex++;
		
		return element;
	}
}
