//package declaration
package ch.nolix.core.test2;

import ch.nolix.core.functionAPI.IFunction;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//class
/**
 * A closure mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public final class ClosureMediator extends Mediator {

	//attribute
	final IFunction closure;
	
	//package-visible constructor
	/**
	 * Creates a new closure mediator that belongs to the given test and is for the given closure.
	 * 
	 * @param test
	 * @param closure
	 * @throws NullArgumentException if the given test is not an instance.
	 */
	ClosureMediator(final Test test, final IFunction closure) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Sets the closure of this closure mediator.
		this.closure = closure;
	}
	
	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is not an instance.
	 * -The closure of this closure mediator throws no exception by running 1 time.
	 */
	public void throwsException() {
		
		//Handles the case that the closure of this closure mediator is not an instance.
		if (closure == null) {
			addCurrentTestCaseError("A closure that throws an exception was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is an instance.
		else {
			try {
				closure.run();
				addCurrentTestCaseError("An exception was expected, but no exception was thrown.");
			}
			catch (final Exception exception) {}
		}
	}
	
	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is not an instance.
	 * -The closure of this closure mediator throws no exception
	 *  whose type is of the given type or is a sub type of the given type.
	 * 
	 * @param type
	 * @throws NullArgumentException if the given type is not an instance.
	 */
	public void throwsExceptionOfType(final Class<?> type) {
		
		//Checks if the given type is an instance.
		if (type == null) {
			throw new NullArgumentException("type");
		}
		
		//Handles the case that the closure of this closure mediator is not an instance.
		if (closure == null) {
			addCurrentTestCaseError("A closure that throws an exception of the type " + type.getName() + " was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is an instance.
		else {
			try {
				closure.run();
				addCurrentTestCaseError("An exception of the type " + type.getName() + " was expected, but no exception was thrown.");
			}
			catch (final Exception exception) {
				if (!type.isAssignableFrom(exception.getClass())) {
					addCurrentTestCaseError(
						"An exception of the type " + type.getName() + " was expected, but an exception of the type " + exception.getClass().getName() + " was thrown."
					);
				}
			}
		}
	}

	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is not an instance.
	 * -The closure of this closure mediator throws an exception by running 1 time.
	 */
	public void throwsNoException() {
		
		//Handles the case that the closure of this closure mediator is not an instance.
		if (closure == null) {
			addCurrentTestCaseError("A closure that throws no exception was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is an instance.
		else {
			try {
				closure.run();
			}
			catch (final Exception exception) {
				addCurrentTestCaseError("No exception was expected, but a " + exception.getClass().getName() + " was thrown.");
			}
		}
	}
}
