//package declaration
package ch.nolix.core.container.main;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
final class SingleContainerIterator<E> implements Iterator<E> {
	
	//static method
	public static <E2> SingleContainerIterator<E2> forElementOrNull(final E2 element) {
		return new SingleContainerIterator<>(element);
	}
	
	//optional attribute
	private E element;
	
	//constructor
	private SingleContainerIterator(final E element) {
		this.element = element;
	}
	
	//method
	@Override
	public boolean hasNext() {
		return (element != null);
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
			throw
			ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
			.toNoSuchElementException();
		}
	}
	
	//method
	private E nextWhenHasNext() {
		
		final var localElement = element;
		
		element = null;
		
		return localElement;
	}
}
