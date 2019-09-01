//package declaration
package ch.nolix.common.test;

import ch.nolix.common.functionAPI.IFunction;
import ch.nolix.common.invalidArgumentExceptions.NullArgumentException;

//class
/**
 * A {@link ClosureMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 70
 */
public final class ClosureMediator extends Mediator {

	//attribute
	final IFunction closure;
	
	//package-visible constructor
	/**
	 * Creates a new {@link ClosureMediator}
	 * that belongs to the given test and is for the given closure.
	 * 
	 * @param test
	 * @param closure
	 * @throws NullArgumentException if the given test is null.
	 * @throws NullArgumentException if the given closure is null.
	 */
	ClosureMediator(final Test test, final IFunction closure) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Checks if the given closure is not nul.
		if (closure == null) {
			throw new NullArgumentException("closure");
		}
		
		//Sets the closure of the current closure mediator.
		this.closure = closure;
	}
	
	//method
	/**
	 * Generates an error if the closure of the current {@link ClosureMediator}
	 * does not throw an exception.
	 */
	public ExtendedThrownExceptionMediator throwsException() {
		try {
			closure.run();
			addCurrentTestCaseError("An exception was expected, but any exception was thrown.");
			return new ExtendedThrownExceptionMediator(getRefTest());
		}
		catch (final Throwable exception) {
			return new ExtendedThrownExceptionMediator(getRefTest(), exception);
		}
	}
	
	//method
	/**
	 * Generates an error if the closure of the current {@link ClosureMediator}
	 * throws an exception.
	 */
	public void doesNotThrowException() {
		try {
			closure.run();
		}
		catch (final Throwable exception) {
			addCurrentTestCaseError(
				"An exception was not expected, but a "
				+ exception.getClass().getName()
				+ " was thrown."
			);
		}
	}
}
