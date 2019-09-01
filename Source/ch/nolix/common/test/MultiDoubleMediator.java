//package declaration
package ch.nolix.common.test;

import ch.nolix.common.independentHelpers.ArrayHelper;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
 * A multi double mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-11
 * @lines 160
 */
public final class MultiDoubleMediator extends Mediator {

	//attribute
	final Iterable<Double> values;
	
	//package-visible constructor
	/**
	 * Creates a new multi double mediator
	 * that belongs to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 */
	MultiDoubleMediator(final Test test, final double[] values) {
		
		//Calls other constructor.
		this(test, ArrayHelper.createIterable(values));
	}

	//package-visible constructor
	/**
	 * Creates a new multi double mediator
	 * that belongs to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	MultiDoubleMediator(final Test test, final Iterable<Double> values) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Sets the values of this multi double mediator.
		this.values = values;
	}
	
	//method
	/**
	 * Generates an error for all of the values of this mutli double mediator that are not bigger than the given value.
	 */
	public void areBiggerThan(final double value) {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of this multi double mediator.
			int i = 1;
			for (final double v : values) {
				
				//Checks if the current value is positive.
				if (v <= value) {
					this.addCurrentTestCaseError(
						"Values that are bigger than "	+ value	+ " were expected, but the "+ i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all of the values of this mutli double mediator that are not negative.
	 */
	public void areNegative() {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of this multi double mediator.
			int i = 1;
			for (final double v : values) {
				
				//Checks if the current value is negative.
				if (v > 0) {
					this.addCurrentTestCaseError(
						"Negative values were expected, but the " + i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all of the values of this mutli double mediator that are not positive.
	 */
	public void arePositive() {
	
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of this multi double mediator.
			int i = 1;
			for (final double v : values) {
				
				//Checks if the current value is positive.
				if (v <= 0) {
					this.addCurrentTestCaseError(
						"Positive values were expected, but the " + i + "th value is not."
					);
				}
				
				//Increments the index.
				i++;
			}
		}
	}
	
	//method
	/**
	 * Generates an error for all of the values of this mutli double mediator that are not smaller than the given value.
	 */
	public void areSmallerThan(final double value) {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of this multi double mediator.
			int i = 1;
			for (final double v : values) {
				
				//Checks if the current value is positive.
				if (v >= value) {
					this.addCurrentTestCaseError(
						"Values that are smaller than "	+ value	+ " were expected, but the "+ i + "th value is."
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
