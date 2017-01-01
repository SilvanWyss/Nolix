/*
 * file:	LongContainerMediator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	240
 */

//package declaration
package ch.nolix.common.zetaTest;

import ch.nolix.common.test.Test;
//own import
import ch.nolix.common.util.Validator;

//class
public final class LongContainerMediator {

	//attribute
	/**
	 * Is the test this long container mediator belongs to.
	 */
	private final Test test;
	
	//attribute
	private final Iterable<Long> values;
	
	//constructor
	/**
	 * Creates new long container mediator that belongs to the given test and has the given values.
	 * 
	 * @param test - the test this long container mediator belongs to
	 * @param values
	 * @throws Excepion if the given test is null
	 */
	protected LongContainerMediator(
		final Test test,
		final Iterable<Long> values) {
		
		//Checks the given test.
		Validator.throwExceptionIfValueIsNull("test", test);
		
		this.test = test;
		this.values = values;
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @throws Exception if the given min is bigger than the given max
	 */
	public void areBetween(final int min, final int max) {
		
		//Checks the given min and max.
		if (min > max) {
			throw new RuntimeException("A value cannot not be between " + min + " and " + max + ".");
		}
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isBetween(min, max);
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not bigger than the given value.
	 * 
	 * @param value
	 */
	public void areBiggerThan(final long value) {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isBiggerThan(value);
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not dividable by the given value.
	 * 
	 * @param value
	 */
	public void areDividableThan(final long value) {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isDividableBy(value);
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not even.
	 */
	public void areEven() {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isEven();
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not negative.
	 */
	public void areNegative() {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isNegative();
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not odd.
	 */
	public void areOdd() {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isOdd();
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not positive.
	 */
	public void arePositive() {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isPositive();
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not smaller than the given value.
	 * 
	 * @param value
	 */
	public void areSmallerThan(final long value) {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).isSmallerThan(value);
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are not equal to the given value.
	 */
	public void equalsNot(final long value) {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).equals(value);
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this long container mediator that are equal to the given value.
	 */
	public void equalNot(final int value) {
		
		//Handles the case if the given values are null.
		if (values == null) {
			test.addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(test, v).equalsNot(value);
			}
		}
	}
}
