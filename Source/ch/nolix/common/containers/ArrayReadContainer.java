//package declaration
package ch.nolix.common.containers;

//Java import
import java.util.Iterator;

import ch.nolix.common.constants.CharacterCatalogue;
import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.validator.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 80
 * @param <E> The type of the elements of a {@link ArrayReadContainer}.
 */
final class ArrayReadContainer<E> implements IContainer<E> {
	
	//attribute
	private final E[] array;
	
	//constructor
	/**
	 * Creates a new {@link ArrayReadContainer} for a new empty array.
	 */
	@SuppressWarnings("unchecked")
	public ArrayReadContainer() {
		
		//Calls other constructor.
		this((E[])new Object[0]);
	}
	
	//constructor
	/**
	 * Creates a new {@link ArrayReadContainer} for the given array.
	 * 
	 * @param array
	 * @throws ArgumentIsNullException if the given array is null.
	 */
	public ArrayReadContainer(final E[] array) {
		
		//Checks if the given array is not null.
		Validator
		.suppose(array)
		.thatIsNamed(VariableNameCatalogue.ARRAY)
		.isNotNull();
		
		//Sets the array of the current ArrayReadContainer.
		this.array = array;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getSize() {
		return array.length;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterator<E> iterator() {
		return new ArrayReadContainerIterator<>(array);
	}
	
	//method
	/**
	 * The complexity of this method is O(n).
	 * if the current {@link ArrayReadContainer} contains n elements.
	 * 
	 * @return a String representation of the current {@link ArrayReadContainer}.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
