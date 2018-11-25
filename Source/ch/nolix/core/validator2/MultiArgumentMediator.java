//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.primitiveHelper.ArrayHelper;

//class
/**
 * A multi argument mediator is an mediator for several arguments of the same type.
 * A multi argument mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 110
 * @param <A> The type of the arguments of a multi argument mediator.
 */
public class MultiArgumentMediator<A> {

	//attribute
	private final Iterable<A> arguments;
	
	//package-visible constructor
	/**
	 * Creates a new multi argument mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null..
	 */
	MultiArgumentMediator(final Iterable<A> arguments) {

		//Checks if the given arguments is not null.
		if (arguments == null) {
			throw new NullArgumentException("arguments");
		}
		
		//Sets the arguments of this multi argument mediator.
		this.arguments = arguments;
	}
	
	//package-visible constructor
	/**
	 * Creates a new multi argument mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given arguments is null.
	 */
	MultiArgumentMediator(final A[] arguments) {

		//Calls other constructor.
		this(ArrayHelper.createIterable(arguments));		
	}
	
	//method
	/**
	 * @throws NullArgumentException if one of the arguments of this multi argument mediator is null.
	 */
	public final void areNotNull() {
		
		//Iterates the arguments of this multi argument mediator.
		int index = 1;
		for (final A a : getRefArguments()) {
			
			//Checks if the current argument is not null.
			if (a == null) {
				throw new NullArgumentException(index + "th argument");
			}
			
			//Increments the index.
			index++;
		}
	}
	
	//method
	/**
	 * @param condition
	 * @throws NullArgumentException if the given condition is null.
	 * @throws InvalidArgumentException if an argument of this argument container does not fulfill the given condition.
	 */
	public final void fulfill(final IElementTakerBooleanGetter<A> condition) {
		
		//Iterates the arguments of this multi argument mediator.
		int index = 1;
		for (final A a : getRefArguments()) {
			
			//Checks if the current argument fulfills the given condition.
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
