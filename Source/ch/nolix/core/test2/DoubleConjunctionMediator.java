//package declaration
package ch.nolix.core.test2;

//class
/**
 * A double conjunction mediator supports expectations for a double that are meaningful to be supported as additional expectations.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 80
 */
public final class DoubleConjunctionMediator extends Mediator {
	
	//attribute
	private final double value;

	//package-visible constructor
	/**
	 * Creates new double conjunction mediator that belongs to the given zeta test and has the given value.
	 * 
	 * @param zetaTest
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	DoubleConjunctionMediator(final ZetaTest zetaTest, final double value) {
		
		//Calls constructor of the base class.
		super(zetaTest);
		
		this.value = value;
	}
	
	//method
	/**
	 * Generates an error if the value of this double conjunction mediator equals the given value. 
	 * 
	 * @param value
	 * @return this double conjunction mediator.
	 */
	public DoubleConjunctionMediator andEqualsNot(final double value) {
		
		new DoubleMediator(getZetaTest(), this.value).equalsNot(value);
		
		return this;
	}
	
	//method
	/**
	 * Generates an error if the value of this double conjunction mediator is between the given values.
	 * 
	 * @param value1
	 * @param value2
	 * @return this double conjunction mediator.
	 * @throws RuntimeException if the given value2 is smaller than the given value1.
	 */
	public DoubleConjunctionMediator andIsNotBetween(final double value1, final double value2) {
		
		new DoubleMediator(getZetaTest(), value).isNotBetween(value1, value2);
		
		return this;
	}
	
	//method
	/**
	 * Generates an error if the value of this double conjunction mediator is 1.0.
	 * 
	 * @return this doublec conjunction mediator.
	 */
	public DoubleConjunctionMediator andIsNotOne() {
		
		new DoubleMediator(getZetaTest(), value).isNotOne();
		
		return this;
	}
	
	//method
	/**
	 * Generates an error if the value of this double conjunction mediator is 0.0.
	 * 
	 * @return this double conjunction mediator.
	 */
	public DoubleConjunctionMediator andIsNotZero() {
		
		new DoubleMediator(getZetaTest(), value).isNotZero();
		
		return this;
	}
}
