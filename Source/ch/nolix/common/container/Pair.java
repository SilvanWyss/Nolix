/*
 * file:	Pair.java
 * author:	Silvan Wyss
 * month:	2015-12
 * lines:	80
 */

//package declaration
package ch.nolix.common.container;

//own import
import ch.nolix.common.util.Validator;

//class
/**
 * A pair contains two objects that each are of a certain type.
 */
public final class Pair<E1, E2> {

	//attributes
	private E1 firstElement;
	private E2 secondElement;
	
	//constructor
	/**
	 * Creates new pair with the given elements.
	 * 
	 * @param firstElement
	 * @param secondElement
	 * @throws Exception if the given first element is null or the given second element is null
	 */
	public Pair(E1 firstElement, E2 secondElement) {
		
		Validator.throwExceptionIfValueIsNull("first element", firstElement);
		Validator.throwExceptionIfValueIsNull("second element", secondElement);
		
		setFirstElement(firstElement);
		setSecondElement(secondElement);
	}
	
	//method
	/**
	 * @return the left element of this pair
	 */
	public final E1 getFirstElement() {
		return firstElement;
	}
	
	//method
	/**
	 * @return the second element of this pair
	 */
	public final E2 getSecondElement() {
		return secondElement;
	}
	
	//method
	/**
	 * Sets the first element of this pair.
	 * 
	 * @param firstElement
	 */
	public final void setFirstElement(E1 firstElement) {
		this.firstElement = firstElement;
	}
	
	//method
	/**
	 * Sets the second element of this pair.
	 * 
	 * @param secondElement
	 */
	public final void setSecondElement(E2 secondElement) {
		this.secondElement = secondElement;
	}
	
	//method
	/**
	 * @return a string representation of this pair
	 */
	public final String toString() {
		return getFirstElement().toString() + "," + getSecondElement().toString();
	}
}
