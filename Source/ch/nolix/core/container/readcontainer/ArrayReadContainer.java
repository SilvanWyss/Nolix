//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.Iterator;

import ch.nolix.core.commontype.constant.CharacterCatalogue;
import ch.nolix.core.container.IContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.core.name.LowerCaseCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2017-11-26
 * @param <E> is the type of the elements of a {@link ArrayReadContainer}.
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
		
		//Asserts that the given array is not null.
		GlobalValidator
		.assertThat(array)
		.thatIsNamed(LowerCaseCatalogue.ARRAY)
		.isNotNull();
		
		//Sets the array of the current ArrayReadContainer.
		this.array = array;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int getElementCount() {
		return array.length;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public E getRefAt(final int index) {
		
		GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isPositive();
		GlobalValidator.assertThat(index).thatIsNamed(LowerCaseCatalogue.INDEX).isNotBiggerThan(getElementCount());
		
		return array[index - 1];
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
	 * The complexity of this implementation is O(n).
	 * if the current {@link ArrayReadContainer} contains n elements.
	 * 
	 * @return a {@link String} representation of the current {@link ArrayReadContainer}.
	 */
	@Override
	public String toString() {
		return toString(CharacterCatalogue.COMMA);
	}
}
