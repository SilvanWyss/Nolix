//package declaration
package ch.nolix.common.independentContainer;

//Java import
import java.util.Iterator;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
public final class ArrayReadContainer<E> implements Iterable<E> {
	
	//attribute
	private final E[] array;
	
	//constructor
	public ArrayReadContainer(final E[] array) {
		
		//Asserts that the given array is not null.
		if (array == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.ARRAY);
		}
		
		//Sets the array of the current array read container.
		this.array = array;
	}
	
	//method
	public int getSize() {
		return array.length;
	}
	
	//method
	@Override
	public Iterator<E> iterator() {
		return new ArrayReadContainerIterator<>(array);
	}
}
