/*
 * file:	LongContainerMediator.java
 * author:	Silvan Wyss
 * month:	2016-11
 * lines:	240
 */

//package declaration
package ch.nolix.core.zetaTest;

import ch.nolix.core.test.Accessor;

//own import

//class
public final class LongContainerMediator extends Mediator {

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
		final ZetaTest zetaTest,
		final Iterable<Long> values) {
		
		//Calls constructor of the base class.
		super(zetaTest);

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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isBetween(min, max);
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isBiggerThan(value);
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isDividableBy(value);
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isEven();
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isNegative();
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isOdd();
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isPositive();
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).isSmallerThan(value);
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).equals(value);
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
			new Accessor(getZetaTest()).addCurrentTestMethodError("Values were expected, but null was received.");
		}
		
		//Handles the case if the given values are not null.
		else {
			for (Long v: values) {
				new LongMediator(getZetaTest(), v).equalsNot(value);
			}
		}
	}
}
