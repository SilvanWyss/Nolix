//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.core.independent.containerhelper.CentralArrayHelper;

//class
/**
 * A long container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 */
public final class MultiLongMediator extends MultiArgumentMediator<Long> {

	//constructor
	/**
	 * Creates a new long container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given argument container is null.
	 */
	MultiLongMediator(final Iterable<Long> arguments) {
		
		//Calls constructor of the base class.
		super(arguments);
	}
	
	public MultiLongMediator(final long[] arguments) {
		
		//Calls constructor if the base class.
		super(CentralArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @throws ArgumentIsNullException if one of the arguments of this long container mediator is null.
	 * @throws NonPositiveArgumentException if one of the arguments of this long container mediator is not positive.
	 */
	public void arePositive() {
	
		//Asserts that the arguments of this long container mediator are not null.
		areNotNull();
		
		//Iterates through the arguments of this long container mediator.
		var index = 1;
		for (long a: getRefArguments()) {
			
			//Asserts that the current argument is positive.
			if (a <= 0) {
				throw NonPositiveArgumentException.forArgumentNameAndArgument(index + "th", a);
			}
			
			//Increments the index.
			index++;
		}
	}
}
