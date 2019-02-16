//package declaration
package ch.nolix.core.pair;

//own import
import ch.nolix.core.validator.Validator;

//class
/**
 * A {@link MonoPair} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2019-02
 * @lines 70
 * @param <E> The type of a {@link MonoPair}.
 */
public final class MonoPair<E> {
	
	//attributes
	private final E element1;
	private final E element2;
	
	//constructor
	/**
	 * Creates a new {@link MonoPair} with the given elements.
	 * 
	 * @param element1
	 * @param element2
	 * @throws NullArgumentException if the given element1 is null.
	 * @throws NullArgumentException if the given element2 is null.
	 */
	public MonoPair(final E element1, final E element2) {
		
		//Checks if the given element1 is not null.
		Validator
		.suppose(element1)
		.thatIsNamed("element 1")
		.isNotNull();
		
		//Checks if the given element2 is not null.
		Validator
		.suppose(element2)
		.thatIsNamed("element 2")
		.isNotNull();
		
		//Sets the elements of the current MonoPair.
		this.element1 = element1;
		this.element2 = element2;
	}
	
	//method
	/**
	 * @return the element1 of the current {@link MonoPair}.
	 */
	public E getRefElement1() {
		return element1;
	}
	
	//method
	/**
	 * @return the element2 of the current {@link MonoPair}.
	 */
	public E getRefElement2() {
		return element2;
	}
	
	//method
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return ("(" + element1 + "," + element2 + ")");
	}
}
