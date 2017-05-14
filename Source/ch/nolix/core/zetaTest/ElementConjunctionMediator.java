//package declaration
package ch.nolix.core.zetaTest;

//abstract package-visible class
abstract class ElementConjunctionMediator<E> extends Mediator {

	//attributes
	private final E value;
	
	//constructor
	/**
	 * Creates new element conjunction mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param zetaTest
	 * @param value
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	public ElementConjunctionMediator(final ZetaTest zetaTest, final E value) {
		
		super(zetaTest);
		
		this.value = value;
	}
	
	protected final E getValue() {
		return value;
	}
}
