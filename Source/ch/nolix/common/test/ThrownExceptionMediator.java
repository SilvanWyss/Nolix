//package declaration
package ch.nolix.common.test;

import ch.nolix.common.constant.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.ArgumentDoesNotHaveAttributeException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
 * A {@link ThrownExceptionMediator} is not mutable.
 * 
 * A {@link ThrownExceptionMediator} does not need to have an exception.
 * In the case an exception was expected, but not thrown,
 * a {@link ThrownExceptionMediator} must be created, but an exception cannot be given to it.
 * 
 * @author Silvan Wyss
 * @month 2018-12
 * @lines 200
 */
public class ThrownExceptionMediator extends Mediator {
	
	//optional attribute
	private final Throwable exception;
	
	//constructor
	/**
	 * Creates a new {@link ThrownExceptionMediator} that will belong to the given test.
	 * 
	 * @param test
	 * @throws ArgumentIsNullException if the given test is null.
	 */
	ThrownExceptionMediator(final Test test) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Clears the exception of the current thrown exception mediator.
		exception = null;
	}
	
	//constructor
	/**
	 * Creates a new {@link ThrownExceptionMediator}
	 * that will belong to the given test and is for the given exception.
	 * 
	 * @param test
	 * @param exception
	 * @throws ArgumentIsNullException if the given test is null.
	 * @throws NullArgumentExcepiton if the given exception is null.
	 */
	ThrownExceptionMediator(final Test test, final Throwable exception) {
		
		//Calls constructor of the base class.
		super(test);
		
		//Asserts that the given exception is not null.
		if (exception == null) {
			throw new ArgumentIsNullException(Exception.class);
		}
		
		//Sets the exception of the current thrown exception mediator.
		this.exception = exception;
	}
	
	//method
	/**
	 * Generates an error if the exception of the current {@link ThrownExceptionMediator}
	 * does not have a message.
	 */
	public final void withMessage() {
		
		//Handles the case that the current thrown exception mediator has an exception.
			/*
			 * For a better performance, this implementation does not use all comfortable methods.
			 * 
			 * shorter implementation:
			 * 
			 * if (hasException()) {
			 * ...
			 * }
			 */
			if (exception != null) {
			
				//Asserts that the exception of the current thrown exception mediator has a message.
				if (exception.getMessage() == null) {
					addCurrentTestCaseError(
						"An exception with a message was expected,"
						+ "but an exception without messag was received."
					);
				}
			}
	}
	
	//method
	/**
	 * Generates an error if the exception of the current {@link ThrownExceptionMediator}
	 * does not have the given message.
	 * 
	 * @throws ArgumentIsNullException if the given message is null.
	 */
	public final void withMessage(final String message) {
		
		//Asserts that the given message is not null.
		if (message == null) {
			throw new ArgumentIsNullException(VariableNameCatalogue.MESSAGE);
		}
		
		//Handles the case that the current thrown exception mediator has an exception.
			/*
			 * For a better performance, this implementation does not use all comfortable methods.
			 * 
			 * shorter implementation:
			 * 
			 * if (hasException()) {
			 * ...
			 * }
			 */
			if (exception != null) {
				
				//Asserts that the exception of the current thrown exception mediator has a message.
				if (exception.getMessage() == null) {
					addCurrentTestCaseError(
						"An exception with the message '"
						+ message
						+ "' was expected, but an exception without messag was received."
					);
				}
				
				//Asserts that the exception of the current thrown exception mediator has the given message.
				if (!exception.getMessage().equals(message)) {
					addCurrentTestCaseError(
						"An exception with the message '"
						+ message
						+ "' was expected, but an exception with the message '"
						+ exception.getMessage()
						+ "' was thrown."
					);
				}
			}
	}
	
	//method
	/**
	 * Generates an error if the exception of the current {@link ThrownExceptionMediator}
	 * has a message.
	 */
	public final void withoutMessage() {
		
		//Handles the case that the current thrown exception mediator has an exception.
			/*
			 * For a better performance, this implementation does not use all comfortable methods.
			 * 
			 * shorter implementation:
			 * 
			 * if (hasException()) {
			 * ...
			 * }
			 */
			if (exception != null) {
				
				//Asserts that the exception of the current thrown exception mediator does not have a message.
				if (exception.getMessage() != null) {
					addCurrentTestCaseError(
						"An exception without message was expected, but an exception with the message '"
						+ exception.getMessage()
						+ "' was received."
					);
				}
			}
	}
	
	//method
	/**
	 * @return the exception of the current {@link ThrownExceptionMediator}.
	 * 
	 */
	final Throwable getException() {
		
		//Asserts that the current thrown exception mediator has an exception.
			/*
			 * For a better performance, this implementation does not use all comfortable methods.
			 * 
			 * shorter implementation:
			 * 
			 * if (!hasException()) {
			 * ...
			 * }
			 */
			if (exception == null) {
				throw new ArgumentDoesNotHaveAttributeException(this, VariableNameCatalogue.EXCEPTION);
			}
		
		return exception;
	}
	
	//method
	/**
	 * @return true if the current {@link ThrownExceptionMediator} has an exception.
	 */
	final boolean hasException() {
		return (exception != null);
	}
}
