//package declaration
package ch.nolix.common.test;

//own imports
import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.independenthelper.ArrayHelper;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.common.invalidargumentexception.NonBiggerArgumentException;
import ch.nolix.common.invalidargumentexception.NonPositiveArgumentException;

//class
/**
 * A multi long mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-11
 * @lines 420
 */
public final class MultiLongMediator extends Mediator {

	//attribute
	private final Iterable<Long> values;
	
	//constructor
	/**
	 * Creates a new multi long mediator that belongs to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	MultiLongMediator(final Test test, final int[] values) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Handles the case that the given values is null.
		if (values == null) {
			this.values = null;
		}
		
		//Handles the case that the given values is not null.
		else {
			this.values = ArrayHelper.createIterable(values);
		}
	}

	//constructor
	/**
	 * Creates a new multi long mediator that belongs to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	MultiLongMediator(final Test test, final Iterable<Long> values) {
		
		//Calls constructor of the base class.
		super(test);

		//Sets the values of this multi long mediator.
		this.values = values;
	}

	//constructor
	/**
	 * Creates a new multi long mediator that belongs to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	MultiLongMediator(final Test test, final long[] values) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Handles the case that the given values is null.
		if (values == null) {
			this.values = null;
		}
		
		//Handles the case that the given values is not null.
		else {
			this.values = ArrayHelper.createIterable(values);
		}
	}

	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not between the given min and max.
	 * 
	 * @param min
	 * @param max
	 * @throws NonBiggerArgumentException if the given max is not bigger than the given min.
	 */
	public void areBetween(final int min, final int max) {
		
		//Asserts that the given max is bigger than the given min.
		if (max <= min) {
			throw new NonBiggerArgumentException("max", max, min);
		}
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is between the given min and max.
				if (v < min || v > max) {
					addCurrentTestCaseError(
						"Values that are between " + min + " and " + max + " were expected, but the " + i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not bigger than the given value.
	 * 
	 * @param value
	 */
	public void areBiggerThan(final long value) {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is bigger than the given value.
				if (v <= value) {
					addCurrentTestCaseError(
						"Values that are bigger than " + value + " were expected, but the " + i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not dividable by the given value.
	 * 
	 * @param value
	 * @throws NonPositiveArgumentException if the given value is not positive.
	 */
	public void areDividableBy(final long value) {
		
		//Asserts that the given value is positive.
		if (value < 1) {
			throw new NonPositiveArgumentException(VariableNameCatalogue.VALUE, value);
		}
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is dividable by the given value.
				if (v % value != 0) {
					addCurrentTestCaseError(
						"Values that are dividable by " + value + " were expected, but the " + i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that do not equal the given value.
	 * 
	 * @param value
	 */
	public void areEqualTo(final long value) {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value equals the given value.
				if (v != value) {
					addCurrentTestCaseError(
						"Values that equal " + value + " were expected, but the " + i + "th value does not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not even.
	 */
	public void areEven() {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is even.
				if (v % 2 != 0) {
					addCurrentTestCaseError("Even values were expected, but the " + i + "th value is not.");
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not negative.
	 */
	public void areNegative() {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is negative.
				if (v >= 0) {
					addCurrentTestCaseError("Negative values were expected, but the " + i + "th value is not.");
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not odd.
	 */
	public void areOdd() {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is odd.
				if (v % 2 == 0) {
					addCurrentTestCaseError("Odd values were expected, but the " + i + "th value is not.");
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not positive.
	 */
	public void arePositive() {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is positive.
				if (v <= 0) {
					addCurrentTestCaseError("Positive values were expected, but the " + i + "th value is not.");
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that are not smaller than the given value.
	 * 
	 * @param value
	 */
	public void areSmallerThan(final long value) {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value is smaller than the given value.
				if (v >= value) {
					addCurrentTestCaseError(
						"Values that are smaller than " + value + " were expected, but the " + i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all values of this multi long mediator that equal the given value.
	 * 
	 * @param value
	 */
	public void areNotEqualTo(final long value) {
		
		//Handles the case that the given values are null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values are not null.
		else {
			
			//Iterates the values of this multi long mediator.
			int i = 1;
			for (final long v: values) {
				
				//Asserts that the current value does not equal the given value.
				if (v == value) {
					addCurrentTestCaseError(
						"Values that do not equal " + value + " were expected, but the " + i + "th value does."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Adds a null values error to the test this mediator belongs to.
	 */
	private void addNullValuesError() {
		this.addCurrentTestCaseError("Values were expected, but null was received.");
	}
}
