//package declaration
package ch.nolix.common.test;

import ch.nolix.common.constants.VariableNameCatalogue;
import ch.nolix.common.invalidArgumentExceptions.NullArgumentException;

//class
/**
* A {@link ExtendedThrownExceptionMediator} is not mutable.
* 
* @author Silvan Wyss
* @month 2018-12
* @lines 80
*/
public final class ExtendedThrownExceptionMediator extends ThrownExceptionMediator {
	
	//package-visible constructor
	/**
	 * Creates a new {@link ExtendedThrownExceptionMediator}
	 * that will belong to the given test.
	 * 
	 * @param test
	 * @throws NullArgumentException if the given test is null.
	 */
	ExtendedThrownExceptionMediator(final Test test) {

		//Calls constructor of the base class.
		super(test);
	}
	
	//package-visible constructor
	/**
	 * Creates a new {@link ExtendedThrownExceptionMediator}
	 * that will belong to the given test and is for the given exception.
	 * 
	 * @param test
	 * @param exception
	 * @throws NullArgumentException if the given test is null.
	 * @throws NullArgumentExcepiton if the given exception is null.
	 */
	ExtendedThrownExceptionMediator(final Test test, final Throwable exception) {
		
		//Calls constructor of the base class.
		super(test, exception);
	}
	
	//method
	/**
	 * Generates an error if the exception of the current {@link ThrownExceptionMediator}
	 * is not of the given type.
	 * 
	 * @return a new {@link ExtendedThrownExceptionMediator}
	 * that belongs to the test of the current {@link ThrownExceptionMediator}
	 * and is for the exception of the current {@link ThrownExceptionMediator}.
	 * @throws NullArgumentException if the given type is null.
	 */
	public <E extends Exception> ExtendedThrownExceptionMediator ofType(final Class<E> type) {
		
		//Checks if the given type is not null.
		if (type == null) {
			throw new NullArgumentException(VariableNameCatalogue.TYPE);
		}
		
		//Handles the case that the current extended thrown exception mediator
		//does not have an exception.
		if (!hasException()) {
			return new ExtendedThrownExceptionMediator(getRefTest());
		}
		
		//Handles the case that the current extended thrown exception mediator has an exception.
			//Checks if the exception of the current thrown exception mediator is of the given type.
			if (!type.isAssignableFrom(getException().getClass())) {
				addCurrentTestCaseError(
					"An exception of the type "
					+ type.getName()
					+ " was expected, but an exception of the type "
					+ getException().getClass().getName()
					+ " was thrown."
				);
			}
			
			return new ExtendedThrownExceptionMediator(getRefTest(), getException());
	}
}
