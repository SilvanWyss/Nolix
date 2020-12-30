//package declaration
package ch.nolix.common.test;

//own imports
import ch.nolix.common.independenthelper.ArrayHelper;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;

//class
/**
 * A {@link MultiDoubleMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @lines 180
 */
public final class MultiDoubleMediator extends Mediator {

	//multi-attribute
	private final Iterable<Double> values;
	
	//constructor
	/**
	 * Creates a new {@link MultiDoubleMediator} that will belong to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 */
	MultiDoubleMediator(final Test test, final double[] values) {
		
		//Calls other constructor.
		this(test, ArrayHelper.createIterable(values));
	}

	//constructor
	/**
	 * Creates a new {@link MultiDoubleMediator} that will belong to the given test and is for the given values.
	 * 
	 * @param test
	 * @param values
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	MultiDoubleMediator(final Test test, final Iterable<Double> values) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Sets the values of the current MultiDoubleMediator.
		this.values = values;
	}
	
	//method
	/**
	 * Generates an error for all of the values of the current {@link MultiDoubleMediator}
	 * that are not bigger than the given value.
	 * 
	 * @param value
	 */
	public void areBiggerThan(final double value) {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of the current MultiDoubleMediator.
			int i = 1;
			for (final double v : values) {
				
				//Asserts that the current value is positive.
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
	 * Generates an error for all of the values of the current {@link MultiDoubleMediator} that are not negative.
	 */
	public void areNegative() {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of the current MultiDoubleMediator.
			int i = 1;
			for (final double v : values) {
				
				//Asserts that the current value is negative.
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
	 * Generates an error for all of the values of the current {@link MultiDoubleMediator} that are not positive.
	 */
	public void arePositive() {
	
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of the current MultiDoubleMediator.
			int i = 1;
			for (final double v : values) {
				
				//Asserts that the current value is positive.
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
	 * Generates an error for all of the values of the current {@link MultiDoubleMediator}
	 * that are not smaller than the given value.
	 * 
	 * @param value
	 */
	public void areSmallerThan(final double value) {
		
		//Handles the case that the given values is null.
		if (values == null) {
			addNullValuesError();
		}
		
		//Handles the case that the given values is not null.
		else {
		
			//Iterates the values of the current MultiDoubleMediator.
			int i = 1;
			for (final double v : values) {
				
				//Asserts that the current value is positive.
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
