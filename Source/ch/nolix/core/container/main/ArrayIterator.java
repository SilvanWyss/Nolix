//package declaration
package ch.nolix.core.container.main;

//Java imports
import java.util.Iterator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
public final class ArrayIterator<E> implements Iterator<E> {
	
	//constructor
	public static <E2> ArrayIterator<E2> forArray(final E2[] array) {
		return new ArrayIterator<>(array);
	}
	
	//constructor
	private ArrayIterator(final E[] array) {
		
		GlobalValidator.assertThat(array).thatIsNamed(LowerCaseCatalogue.ARRAY).isNotNull();
		
		this.array = array;
	}
	
	//attribute
	private final E[] array;
	
	//attribute
	private int currentIndex;
	
	//method
	@Override
	public boolean hasNext() {
		return (currentIndex < array.length);
	}
	
	//method
	@Override
	public E next() {
		
		assertHasNext();
		
		final var element = array[currentIndex];
		currentIndex++;
		return element;
	}
	
	//method
	private void assertHasNext() {
		if (!hasNext()) {
			throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next element");
		}
	}
}
