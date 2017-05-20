//package declaration
package ch.nolix.core.test2;

//abstract package-visible class
abstract class ElementConjunctionMediator<E> extends Mediator {

	//attributes
	private final E value;
	
	//constructor
	/**
	 * Creates new element conjunction mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param test
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ElementConjunctionMediator(final Test test, final E value) {
		
		super(test);
		
		this.value = value;
	}
	
	protected final E getValue() {
		return value;
	}
}
