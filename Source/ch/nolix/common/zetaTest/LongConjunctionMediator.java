/*
 * file:	LongConjunctionMediator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	80
 */

//package declaration
package ch.nolix.common.zetaTest;

import ch.nolix.common.test.Test;

//class
/**
 * A long conjunction mediator supports an additional expectation for the value of a long mediator.
 * A long mediator supports all expectations a long conjunction mediator supports.
 * A long conjunction mediator supports only the expectations that are meaningful to be supported as additional expectations.
 */
public final class LongConjunctionMediator {
	
	//attribute
	private final Test test;
	private final long value;
	
	//package-visible constructor
	LongConjunctionMediator(final Test test, final long value) {
		this.test = test;
		this.value = value;
	}
	
	//method
	public LongConjunctionMediator andIsBiggerThan(final long value) {
		
		
		
		new LongMediator(test, value).isBiggerThan(value);
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsDividableBy(final int divisor) {
		
		new LongMediator(test, value).isDividableBy(divisor);
		
		return this;
	}

	//method
	public LongConjunctionMediator andIsEven() {
		
		new LongMediator(test, value).isEven();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsNotOne() {
		
		new LongMediator(test, value).isNotOne();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsNotPrim() {
		
		new LongMediator(test, value).isNotPrime();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsNotZero() {
		
		new LongMediator(test, value).isNotZero();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsOdd() {
		
		new LongMediator(test, value).isOdd();
		
		return this;
	}
	
	//method
	public LongConjunctionMediator andIsPrime() {
		
		new LongMediator(test, value).isPrime();
		
		return this;
	}
}
