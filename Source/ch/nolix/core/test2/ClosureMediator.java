//package declaration
package ch.nolix.core.test2;

//own imports
import ch.nolix.core.functionInterfaces.IRunner;
import ch.nolix.core.testoid.TestAccessor;
import ch.nolix.core.validator2.Validator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 110
 */
public final class ClosureMediator extends Mediator {

	//attribute
	final IRunner closure;
	
	//package-visible constructor
	/**
	 * Creates new closure mediator that belongs to the given zeta test and has the given closure.
	 * 
	 * @param test
	 * @param closure
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	ClosureMediator(final Test test, final IRunner closure) {
		
		//Calls constructor of the base class.
		super(test);
		
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
			new TestAccessor(getTest()).addCurrentTestMethodError("A closure that throws an exception was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
				new TestAccessor(getTest()).addCurrentTestMethodError("An exception was expected, but no exception was received.");
			}
			catch (Exception e) {}
		}
	}
	
	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is null.
	 * -The closure of this closure mediator throws no exception whose type is the given type or a sub type of the given type.
	 * 
	 * @param type
	 */
	public void throwsExceptionOfType(final Class<?> type) {
		
		//Checks if the given type is not null.
		Validator.suppose(type).thatIsNamed("type").isNotNull();
		
		//Handles the case that the closure of this closure mediator is null.
		if (closure == null) {
			new TestAccessor(getTest()).addCurrentTestMethodError("A closure that throws an exception of the type " + type.getName() + " was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
				new TestAccessor(getTest()).addCurrentTestMethodError("An exception of the type " + type.getName() + " was expected, but no exception was received.");
			}
			catch (Exception e) {
				if (!e.getClass().isAssignableFrom(type)) {
					new TestAccessor(getTest()).addCurrentTestMethodError("An exception of the type " + type.getName() + " was expected, but an exception of the type " + e.getClass().getName() + " was received.");
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
			new TestAccessor(getTest()).addCurrentTestMethodError("A closure that throws no exception was expected, but null was received.");
		}
		
		//Handles the case that the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
			}
			catch (Exception e) {
				new TestAccessor(getTest()).addCurrentTestMethodError("No exception was expected, but a " + e.getClass().getName() + " was received.");
			}
		}
	}
}
