//package declaration
package ch.nolix.core.errorcontrol.validator;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.functionuniversalapi.IElementTakerBooleanGetter;
import ch.nolix.core.independent.independenthelper.CentralArrayHelper;

//class
/**
 * A multi argument mediator is an mediator for several arguments of the same type.
 * A multi argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-01-01
 * @param <A> is the type of the arguments of a multi argument mediator.
 */
public class MultiArgumentMediator<A> {

	//attribute
	private final Iterable<A> arguments;
	
	//constructor
	/**
	 * Creates a new multi argument mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given arguments is null..
	 */
	MultiArgumentMediator(final Iterable<A> arguments) {

		//Asserts that the given arguments is not null.
		if (arguments == null) {
			throw new ArgumentIsNullException("arguments");
		}
		
		//Sets the arguments of this multi argument mediator.
		this.arguments = arguments;
	}
	
	//constructor
	/**
	 * Creates a new multi argument mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws ArgumentIsNullException if the given arguments is null.
	 */
	MultiArgumentMediator(final A[] arguments) {

		//Calls other constructor.
		this(CentralArrayHelper.createIterable(arguments));
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if one of the arguments of this multi argument mediator is null.
	 */
	public final void areNotNull() {
		
		//Iterates the arguments of this multi argument mediator.
		int index = 1;
		for (final A a : getRefArguments()) {
			
			//Asserts that the current argument is not null.
			if (a == null) {
				throw new ArgumentIsNullException(index + "th argument");
			}
			
			//Increments the index.
			index++;
		}
	}
	
	//method
	/**
	 * @param condition
	 * @throws ArgumentIsNullException if the given condition is null.
	 * @throws InvalidArgumentException if an argument of this argument container does not fulfill the given condition.
	 */
	public final void fulfill(final IElementTakerBooleanGetter<A> condition) {
		
		//Iterates the arguments of this multi argument mediator.
		int index = 1;
		for (final A a : getRefArguments()) {
			
			//Asserts that the current argument fulfills the given condition.
			if (!condition.getOutput(a)) {
				throw new InvalidArgumentException(
					index + "th argument",
					a,
					"does not fulfil the given condition"
				);
			}
			
			//Increments the index.
			index++;
		}
	}
	
	//method
	/**
	 * @return the arguments of this multi argument mediator.
	 */
	protected final Iterable<A> getRefArguments() {
		return arguments;
	}
}
