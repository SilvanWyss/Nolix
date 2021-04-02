//package declaration
package ch.nolix.common.container.pair;

import ch.nolix.common.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.errorcontrol.validator.Validator;

//class
/**
 * A {@link Pair} contains 2 elements.
 * A {@link Pair} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-01-01
 * @lines 90
 * @param <E1> is the type of the element 1 of a {@link Pair}.
 * @param <E2> is the type of the element 2 of a {@link Pair}.
 */
public final class Pair<E1, E2> {
	
	//attributes
	private final E1 element1;
	private final E2 element2;
	
	//constructor
	/**
	 * Creates a new {@link Pair} with the given elements.
	 * 
	 * @param element1
	 * @param element2
	 * @throws ArgumentIsNullException if the given element 1 is null.
	 * @throws ArgumentIsNullException if the given element 2 is null.
	 */
	public Pair(final E1 element1, final E2 element2) {
		
		//Asserts that the given element 1 is not null.
		Validator.assertThat(element1).thatIsNamed("element 1").isNotNull();
		
		//Asserts that the given element 2 is not null.
		Validator.assertThat(element2).thatIsNamed("element 2").isNotNull();
		
		//Sets the element 1 of the current {@link Pair}.
		this.element1 = element1;
		
		//Sets the element 2 of the current {@link Pair}.
		this.element2 = element2;
	}
	
	//method
	/**
	 * @return the element 1 of this {@link Pair}.
	 */
	public E1 getRefElement1() {
		return element1;
	}
	
	//method
	/**
	 * @return the element 2 of this {@link Pair}.
	 */
	public E2 getRefElement2() {
		return element2;
	}
	
	//method
	/**
	 * @param object
	 * @return true if the element 1 of the current {@link Pair} is the given object.
	 */
	public boolean hasElement1(final Object object) {
		return (element1 == object);
	}
	
	//method
	/**
	 * @param object
	 * @return true if the element 2 of the current {@link Pair} is the given object.
	 */
	public boolean hasElement2(final Object object) {
		return (element2 == object);
	}
	
	//method
	/**
	 * @return a string representation of this {@link Pair}.
	 */
	@Override
	public String toString() {
		return ("(" + getRefElement1() + "," + getRefElement2() + ")");
	}
}
