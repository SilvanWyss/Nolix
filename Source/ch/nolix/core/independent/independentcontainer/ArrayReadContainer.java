//package declaration
package ch.nolix.core.independent.independentcontainer;

//own imports
import ch.nolix.core.container.arraycontrol.ArrayIterator;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;

//class
public final class ArrayReadContainer<E> implements Iterable<E> {
	
	//attribute
	private final E[] array;
	
	//constructor
	public ArrayReadContainer(final E[] array) {
		
		//Asserts that the given array is not null.
		if (array == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ARRAY);
		}
		
		//Sets the array of the current array read container.
		this.array = array; //NOSONAR: An ArrayReadContainer operates on the original instance.
	}
	
	//method
	public int getSize() {
		return array.length;
	}
	
	//method
	@Override
	public CopyableIterator<E> iterator() {
		return ArrayIterator.forArray(array);
	}
}
