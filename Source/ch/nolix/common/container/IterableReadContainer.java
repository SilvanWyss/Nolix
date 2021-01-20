//package declaration
package ch.nolix.common.container;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.common.constant.CharacterCatalogue;
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-26
 * @lines 120
 * @param <E> is the type of the elements of a {@link IterableReadContainer}.
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
		Validator
		.assertThat(container)
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
		Validator.assertThat(index).thatIsNamed(VariableNameCatalogue.INDEX).isPositive();
		
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
