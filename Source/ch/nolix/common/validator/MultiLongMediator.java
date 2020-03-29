//package declaration
package ch.nolix.common.validator;

//own imports
import ch.nolix.common.independentHelpers.ArrayHelper;
import ch.nolix.common.invalidArgumentExceptions.NonPositiveArgumentException;
import ch.nolix.common.invalidArgumentExceptions.ArgumentIsNullException;

//class
/**
 * A long container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 40
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
		super(ArrayHelper.createIterable(arguments));
	}

	//method
	/**
	 * @throws ArgumentIsNullException if one of the arguments of this long container mediator is null.
	 * @throws NonPositiveArgumentException if one of the arguments of this long container mediator is not positive.
	 */
	public void arePositive() {
	
		//Checks if the arguments of this long container mediator are not null.
		areNotNull();
		
		//Iterates through the arguments of this long container mediator.
		int index = 1;
		for (long a: getRefArguments()) {
			
			//Checks if the current argument is positive.
			if (a <= 0) {
				throw new NonPositiveArgumentException(index + "th", a);
			}
			
			//Increments the index.
			index++;
		}
	}
}
