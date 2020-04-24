//package declaration
package ch.nolix.common.independentContainer;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNotNullException;
import ch.nolix.common.invalidArgumentExceptions.EmptyArgumentException;

//class
/**
 * A {@link SingleContainer} is empty or stores 1 element.
 * A {@link SingleContainer} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2020-03
 * @param <E> The type of the element of a {@link SingleContainer}.
 * @lines 70
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
			throw new ArgumentIsNotNullException(VariableNameCatalogue.ELEMENT);
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
