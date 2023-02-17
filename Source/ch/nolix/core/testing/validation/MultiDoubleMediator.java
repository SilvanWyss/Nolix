//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.independent.containerhelper.GlobalArrayHelper;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
/**
 * A {@link MultiDoubleMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public final class MultiDoubleMediator extends Mediator {

	//multi-attribute
	private final Iterable<Double> values;
	
	//constructor
	/**
	 * Creates a new {@link MultiDoubleMediator} that will belong to the given test and is for the given values.
	 * 
	 * @param expectationErrorTaker
	 * @param values
	 */
	public MultiDoubleMediator(final IElementTaker<String> expectationErrorTaker, final double[] values) {
		
		//Calls other constructor.
		this(expectationErrorTaker, GlobalArrayHelper.createIterable(values));
	}

	//constructor
	/**
	 * Creates a new {@link MultiDoubleMediator} that will belong to the given test and is for the given values.
	 * 
	 * @param expectationErrorTaker
	 * @param values
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	public MultiDoubleMediator(final IElementTaker<String> expectationErrorTaker, final Iterable<Double> values) {
		
		//Calls constructor of the base class.
		super(expectationErrorTaker);
		
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
			
		//Handles the case that the given values is not null.
		} else {
		
			//Iterates the values of the current MultiDoubleMediator.
			var i = 1;
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
			
		//Handles the case that the given values is not null.
		} else {
		
			//Iterates the values of the current MultiDoubleMediator.
			var i = 1;
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
			
		//Handles the case that the given values is not null.
		} else {
		
			//Iterates the values of the current MultiDoubleMediator.
			var i = 1;
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
			
		//Handles the case that the given values is not null.
		} else {
		
			//Iterates the values of the current MultiDoubleMediator.
			var i = 1;
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
