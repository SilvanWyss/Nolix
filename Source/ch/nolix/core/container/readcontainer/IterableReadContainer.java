//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.container.Container;
import ch.nolix.core.container.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-26
 * @param <E> is the type of the elements of a {@link IterableReadContainer}.
 */
final class IterableReadContainer<E> extends Container<E> {
	
	//attribute
	private final Iterable<E> container;
	
	//constructor
	/**
	 * Creates a new {@link IterableReadContainer} for a new empty container.
	 */
	public IterableReadContainer() {
		
		//Calls other constructor.
		this(new LinkedList<E>());
	}
	
	//constructor
	/**
	 * Creates a new {@link IterableReadContainer} for the given container.
	 * 
	 * @param container
	 * @param <E2> is the type of the elements of the given container.
	 * @throws ArgumentIsNullException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> IterableReadContainer(final Iterable<E2> container) {
		
		//Asserts that the given container is not null.
		GlobalValidator
		.assertThat(container)
		.thatIsNamed(LowerCaseCatalogue.CONTAINER)
		.isNotNull();
		
		//Sets the container of the current IterableReadContainer.
		this.container = (Iterable<E>)container;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return container.iterator();
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		
		var size = 0;
		
		//Iterates the current IterableReadContainer.
		final var iterator = container.iterator();
		while (iterator.hasNext()) {
			size++;
			iterator.next();
		}
		
		return size;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt(final int index) {
		
		//Asserts that the given index is positive.
		GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		
		//Iterates the current IterableReadContainer.
		var i = 1;
		for (final var e : this) {
			
			//Asserts that the current index is the given index.
			if (i == index) {
				return e;
			}
			
			i++;
		}
		
		throw new ArgumentDoesNotHaveAttributeException(this, "element at " + index);
	}
	
	//method
	/**
	 * The complexity of this implementation is O(n).
	 * if the current {@link IterableReadContainer} contains n elements.
	 * 
	 * @return a {@link String} representation of the current {@link IterableReadContainer}.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
