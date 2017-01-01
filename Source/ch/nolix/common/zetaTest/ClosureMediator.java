//package declaration
package ch.nolix.common.zetaTest;

//own imports
import ch.nolix.common.functional.IRunner;
import ch.nolix.common.zetaValidator.ZetaValidator;

//class
/**
 * @author Silvan Wyss
 * @month 2016-09
 * @lines 120
 */
public final class ClosureMediator {

	//attributes
	private final ZetaTest zetaTest;
	final IRunner closure;
	
	//package-visible constructor
	/**
	 * Creates new closure mediator that belongs to the given zeta test and has the given closure.
	 * 
	 * @param zetaTest
	 * @param closure
	 * @throws NullArgumentException if the given zeta test is null.
	 */
	ClosureMediator(final ZetaTest zetaTest, final IRunner closure) {
		
		//Checks if the given zeta test is not null.
		ZetaValidator.supposeThat(zetaTest).thatIsNamed("zeta test").isNotNull();

		this.zetaTest = zetaTest;
		this.closure = closure;
	}
	
	//method
	/**
	 * Generates an error if:
	 * -The closure of this closure mediator is null.
	 * -The closure of this closure mediator throws no exception by running 1 time.
	 */
	public void throwsException() {
		
		//Handles the case if the closure of this closure mediator is null.
		if (closure == null) {
			zetaTest.addCurrentTestMethodError("A closure that throws an exception was expected, but null was received.");
		}
		
		//Handles the case if the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
				zetaTest.addCurrentTestMethodError("An exception was expected, but no exception was received.");
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
		ZetaValidator.supposeThat(type).thatIsNamed("type").isNotNull();
		
		//Handles the case if the closure of this closure mediator is null.
		if (closure == null) {
			zetaTest.addCurrentTestMethodError("A closure that throws an exception of the type " + type.getName() + " was expected, but null was received.");
		}
		
		//Handles the case if the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
				zetaTest.addCurrentTestMethodError("An exception of the type " + type.getName() + " was expected, but no exception was received.");
			}
			catch (Exception e) {
				if (!e.getClass().isAssignableFrom(type)) {
					zetaTest.addCurrentTestMethodError("An exception of the type " + type.getName() + " was expected, but an exception of the type " + e.getClass().getName() + " was received.");
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
		
		//Handles the case if the closure of this closure mediator is null.
		if (closure == null) {
			zetaTest.addCurrentTestMethodError("A closure that throws no exception was expected, but null was received.");
		}
		
		//Handles the case if the closure of this closure mediator is not null.
		else {
			try {
				closure.run();
			}
			catch (Exception e) {
				zetaTest.addCurrentTestMethodError("No exception was expected, but a " + e.getClass().getName() + " was received.");
			}
		}
	}
}
