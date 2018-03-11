//package declaration
package ch.nolix.primitive.test2;

//own imports
import ch.nolix.core.functionInterfaces.IFunction;
import ch.nolix.primitive.invalidArgumentException.NullArgumentException;

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
	 * @throws NullArgumentException if the given test is null.
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
	 * -The closure of this closure mediator is null.
	 * -The closure of this closure mediator throws no exception by running 1 time.
	 */
	public void throwsException() {
		
		//Handles the case that the closure of this closure mediator is null.
		if (closure == null) {
			addCurrentTestMethodError("A closure that throws an exception was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
				addCurrentTestMethodError("An exception was expected, but no exception was thrown.");
			}
			catch (final Exception exception) {}
		}
	}
	
	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is null.
	 * -The closure of this closure mediator throws no exception
	 *  whose type is of the given type or is a sub type of the given type.
	 * 
	 * @param type
	 * @throws NullArgumentException if the given type is null.
	 */
	public void throwsExceptionOfType(final Class<?> type) {
		
		//Checks if the given type is not null.
		if (type == null) {
			throw new NullArgumentException("type");
		}
		
		//Handles the case that the closure of this closure mediator is null.
		if (closure == null) {
			addCurrentTestMethodError("A closure that throws an exception of the type " + type.getName() + " was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
				addCurrentTestMethodError("An exception of the type " + type.getName() + " was expected, but no exception was thrown.");
			}
			catch (final Exception exception) {
				if (!type.isAssignableFrom(exception.getClass())) {
					addCurrentTestMethodError(
						"An exception of the type " + type.getName() + " was expected, but an exception of the type " + exception.getClass().getName() + " was thrown."
					);
				}
			}
		}
	}

	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is null.
	 * -The closure of this closure mediator throws an exception by running 1 time.
	 */
	public void throwsNoException() {
		
		//Handles the case that the closure of this closure mediator is null.
		if (closure == null) {
			addCurrentTestMethodError("A closure that throws no exception was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
			}
			catch (final Exception exception) {
				addCurrentTestMethodError("No exception was expected, but a " + exception.getClass().getName() + " was thrown.");
			}
		}
	}
}
