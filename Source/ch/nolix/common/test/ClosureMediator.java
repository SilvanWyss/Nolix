//package declaration
package ch.nolix.common.test;

//own imports
import ch.nolix.common.functionapi.IAction;
import ch.nolix.common.invalidargumentexception.ArgumentIsNullException;

//class
/**
 * A {@link ClosureMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 * @lines 80
 */
public final class ClosureMediator extends Mediator {
	
	//attribute
	private final IAction closure;
	
	//visibility-reduced constructor
	/**
	 * Creates a new {@link ClosureMediator} that belongs to the given test and is for the given closure.
	 * 
	 * @param test
	 * @param closure
	 * @throws ArgumentIsNullException if the given test is null.
	 * @throws ArgumentIsNullException if the given closure is null.
	 */
	ClosureMediator(final Test test, final IAction closure) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Asserts that the given closure is not null.
		if (closure == null) {
			throw new ArgumentIsNullException("closure");
		}
		
		//Sets the closure of the current ClosureMediator.
		this.closure = closure;
	}
	
	//method
	/**
	 * Generates an error if the closure of the current {@link ClosureMediator} does not throw an exception.
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
	 * Generates an error if the closure of the current {@link ClosureMediator} throws an exception.
	 */
	public void doesNotThrowException() {
		try {
			closure.run();
		}
		catch (final Throwable exception) {
			
			final var message = exception.getMessage();
			
			if (message == null || message.isBlank()) {
				addCurrentTestCaseError(
					"An exception was not expected, but a "
					+ exception.getClass().getName()
					+ " was thrown."
				);
			}
			else {
				addCurrentTestCaseError(
					"An exception was not expected, but a "
					+ exception.getClass().getName()
					+ " was thrown with the message '"
					+ message
					+ "'"
				);
			}
		}
	}
}
