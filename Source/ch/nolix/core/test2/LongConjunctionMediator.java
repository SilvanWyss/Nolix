/*
 * file:	LongConjunctionMediator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	80
 */

//package declaration
package ch.nolix.core.test2;

//own import

//class
/**
 * A long conjunction mediator supports an additional expectation for the value of a long mediator.
 * A long mediator supports all expectations a long conjunction mediator supports.
 * A long conjunction mediator supports only the expectations that are meaningful to be supported as additional expectations.
 */
public final class LongConjunctionMediator extends Mediator {
	
	//attribute
	private final long value;
	
	//package-visible constructor
	LongConjunctionMediator(final Test test, final long value) {
		
		//Calls constructor of the base class.
		super(test);
		
		this.value = value;
	}
	
	//method
	public LongConjunctionMediator andIsBiggerThan(final long value) {
		
		
		
		new LongMediator(getZetaTest(), value).isBiggerThan(value);
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsDividableBy(final int divisor) {
		
		new LongMediator(getZetaTest(), value).isDividableBy(divisor);
		
		return this;
	}

	//method
	public LongConjunctionMediator andIsEven() {
		
		new LongMediator(getZetaTest(), value).isEven();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsNotOne() {
		
		new LongMediator(getZetaTest(), value).isNotOne();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsNotPrim() {
		
		new LongMediator(getZetaTest(), value).isNotPrime();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsNotZero() {
		
		new LongMediator(getZetaTest(), value).isNotZero();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsOdd() {
		
		new LongMediator(getZetaTest(), value).isOdd();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsPrime() {
		
		new LongMediator(getZetaTest(), value).isPrime();
		
		return this;
	}
}
