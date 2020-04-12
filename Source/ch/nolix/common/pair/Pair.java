//package declaration
package ch.nolix.common.pair;

//own imports
import ch.nolix.common.validator.Validator;

//class
/**
 * A {@link Pair} contains 2 elements.
 * A {@link Pair} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2015-12
 * @lines 70
 * @param <E1> The type of the element 1 of a {@link Pair}.
 * @param <E2> The type of the element 2 of a {@link Pair}.
 */
public class Pair<E1, E2> {

	//attributes
	private final E1 element1;
	private final E2 element2;
	
	//constructor
	/**
	 * Creates a new pair with the given elements.
	 * 
	 * @param element1
	 * @param element2
	 * @throws ArgumentIsNullException if the given element 1 is null.
	 * @throws ArgumentIsNullException if the given element 2 is null.
	 */
	public Pair(final E1 element1, final E2 element2) {
		
		//Checks if the given element 1 is not null.
		Validator.assertThat(element1).thatIsNamed("element 1").isNotNull();
		
		//Checks if the given element 2 is not null.
		Validator.assertThat(element2).thatIsNamed("element 2").isNotNull();
		
		//Sets the element 1 of the current Pair.
		this.element1 = element1;
		
		//Sets the element 2 of the current Pair.
		this.element2 = element2;
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
	 * @return a string representation of this pair.
	 */
	@Override
	public String toString() {
		return ("(" + getRefElement1() + "," + getRefElement2() + ")");
	}
}
