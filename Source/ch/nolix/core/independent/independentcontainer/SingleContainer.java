//package declaration
package ch.nolix.core.independent.independentcontainer;

import ch.nolix.core.constant.LowerCaseCatalogue;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNotNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;

//class
/**
 * A {@link SingleContainer} is empty or stores 1 element.
 * A {@link SingleContainer} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2020-03-09
 * @param <E> is the type of the element of a {@link SingleContainer}.
 */
public final class SingleContainer<E> {
	
	//optional attribute
	private final E element;
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer} that is empty.
	 */
	public SingleContainer() {
		element = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link SingleContainer} with the given element.
	 * 
	 * @param element
	 * @throws ArgumentIsNullException if the given element is null.
	 */
	public SingleContainer(final E element) {
		
		//Asserts that the given element is not null.
		if (element == null) {
			throw new ArgumentIsNotNullException(LowerCaseCatalogue.ELEMENT);
		}
		
		//Sets the element of the current SingleContainer.
		this.element = element;
	}
	
	//method
	/**
	 * @return the element of the current {@link SingleContainer}.
	 * @throws EmptyArgumentException if the current {@link SingleContainer} is empty.
	 */
	public E getRefElement() {
		
		//Asserts that the current SingleContainer is not empty.
		if (isEmpty()) {
			throw new EmptyArgumentException(this);
		}
		
		//Returns the element of the current SingleContainer.
		return element;
	}
	
	//method
	/**
	 * @return true if the current {@link SingleContainer} is empty.
	 */
	public boolean isEmpty() {
		return (element == null);
	}
}
