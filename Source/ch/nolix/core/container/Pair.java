//package declaration
package ch.nolix.core.container;

import ch.nolix.primitive.validator2.Validator;

//class
/**
 * A pair contains two elements that can be of different types.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 100
 * @param <E1> - The type of the element 1 of a pair.
 * @param <E2> - The type of the element 2 of a pair.
 */
public class Pair<E1, E2> {

	//attributes
	private E1 element1;
	private E2 element2;
	
	//constructor
	/**
	 * Creates a new pair with the given elements.
	 * 
	 * @param element1
	 * @param element2
	 * @throws NullArgumentException if the given element 1 is not an instance.
	 * @throws NullArgumentException if the given element 2 is not an instance.
	 */
	public Pair(final E1 element1, final E2 element2) {
		setElements(element1, element2);
	}
	
	//method
	/**
	 * @return the element 1 of this pair.
	 */
	public E1 getRefElement1() {
		return element1;
	}
	
	//method
	/**
	 * @return the element 2 of this pair.
	 */
	public E2 getRefElement2() {
		return element2;
	}
	
	//method
	/**
	 * Sets the element 1 of this pair.
	 * 
	 * @param element1
	 * @throws NullArgumentException if the given element 1 is not an instance.
	 */
	public void setElement1(final E1 element1) {
		
		//Checks if the given element 1 is an instance.
		Validator.suppose(element1).thatIsNamed("element 1").isInstance();
		
		//Sets the element 1 of this pair.
		this.element1 = element1;
	}
	
	//method
	/**
	 * Sets the element 2 of this pair.
	 * 
	 * @param element2
	 * @throws NullArgumentException if the given element 2 is not an instance.
	 */
	public void setElement2(final E2 element2) {
		
		//Checks if the given element 21 is an instance.
		Validator.suppose(element2).thatIsNamed("element2").isInstance();
		
		//Sets the element 2 of this pair.
		this.element2 = element2;
	}
	
	//method
	/**
	 * Sets the elements of this pair.
	 * 
	 * @param element1
	 * @param element2
	 * @throws NullArgumentException if the given element 1 is not an instance.
	 * @throws NullArgumentException if the given element 2 is not an instance.
	 */
	public void setElements(final E1 element1, final E2 element2) {
		setElement1(element1);
		setElement2(element2);
	}
	
	//method
	/**
	 * @return a string representation of this pair.
	 */
	public String toString() {
		return ("(" + getRefElement1() + "," + getRefElement2() + ")");
	}
}
