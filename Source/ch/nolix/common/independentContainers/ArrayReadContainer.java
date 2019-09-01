//package declaration
package ch.nolix.common.independentContainers;

//Java import
import java.util.Iterator;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.NullArgumentException;

//class
public final class ArrayReadContainer<E> implements Iterable<E> {
	
	//attribute
	private final E[] array;
	
	//constructor
	public ArrayReadContainer(final E[] array) {
		
		//Checks if the given array is not null.
		if (array == null) {
			throw new NullArgumentException(VariableNameCatalogue.ARRAY);
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
