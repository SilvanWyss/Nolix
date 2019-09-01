//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//package-visible class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 90
 * @param <E> The type of the elements of a {@link IterableReadContainer}.
 */
final class IterableReadContainer<E> implements IContainer<E> {
	
	//attribute
	private final Iterable<E> container;
	
	//constructor
	/**
	 * Creates a new {@link IterableReadContainer} for a new empty container.
	 */
	public IterableReadContainer() {
		
		//Calls other constructor.
		this(new List<E>());
	}
	
	//constructor
	/**
	 * Creates a new {@link IterableReadContainer} for the given container.
	 * 
	 * @param container
	 * @throws NullArgumentException if the given container is null.
	 */
	@SuppressWarnings("unchecked")
	public <E2 extends E> IterableReadContainer(final Iterable<E2> container) {
		
		//Checks if the given container is not null.
		Validator
		.suppose(container)
		.thatIsNamed(VariableNameCatalogue.CONTAINER)
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
	public int getSize() {
		
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
	 * The complexity of this method is O(n).
	 * if the current {@link IterableReadContainer} contains n elements.
	 * 
	 * @return a String representation of the current {@link IterableReadContainer}.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
