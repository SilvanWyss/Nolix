//package declaration
package ch.nolix.core.testing.validation;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IAction;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTaker;

//class
/**
 * A {@link ClosureMediator} is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2016-10-01
 */
public final class ClosureMediator extends Mediator {
	
	//attribute
	private final IAction closure;
	
	//constructor
	/**
	 * Creates a new {@link ClosureMediator} that belongs to the given test and is for the given closure.
	 * 
	 * @param expectationErrorTaker
	 * @param closure
	 * @throws ArgumentIsNullException if the given test is null.
	 * @throws ArgumentIsNullException if the given closure is null.
	 */
	public ClosureMediator(final IElementTaker<String> expectationErrorTaker, final IAction closure) {
		
		//Calls constructor of the base class.
		super(expectationErrorTaker);
		
		//Asserts that the given closure is not null.
		if (closure == null) {
			throw ArgumentIsNullException.forArgumentName("closure");
		}
		
		//Sets the closure of the current ClosureMediator.
		this.closure = closure;
	}
	
	//method
	/**
	 * Generates an error if the closure of the current {@link ClosureMediator} does not throw an exception.
	 * 
	 * @return a new {@link ExtendedThrownExceptionMediator}.
	 */
	public ExtendedThrownExceptionMediator throwsException() {
		try {
			closure.run();
			addCurrentTestCaseError("An exception was expected, but no exception was thrown.");
			return new ExtendedThrownExceptionMediator(getRefExpectationErrorTaker());
		} catch (final Throwable exception) {
			return new ExtendedThrownExceptionMediator(getRefExpectationErrorTaker(), exception);
		}
	}
	
	//method
	/**
	 * Generates an error if the closure of the current {@link ClosureMediator} throws an exception.
	 */
	public void doesNotThrowException() {
		try {
			closure.run();
		} catch (final Throwable exception) {
			
			final var message = exception.getMessage();
			
			if (message == null || message.isBlank()) {
				addCurrentTestCaseError(
					"An exception was not expected, but a "
					+ exception.getClass().getName()
					+ " was thrown."
				);
			} else {
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
