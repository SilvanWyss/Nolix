//package declaration
package ch.nolix.core.validator2;

//own imports
import ch.nolix.core.functionInterfaces.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.Argument;
import ch.nolix.core.invalidArgumentException.ArgumentName;
import ch.nolix.core.invalidArgumentException.ErrorPredicate;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;

//abstract class
/**
 * An argument container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-01
 * @lines 110
 * @param <A> The type of the arguments of an argument container mediator.
 */
public class ArgumentContainerMediator<A> {

	//attribute
	private final Iterable<A> arguments;
	
	//package-visible constructor
	/**
	 * Creates new argument container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given argument container is null.
	 */
	ArgumentContainerMediator(final Iterable<A> arguments) {

		//Checks if the given argument container is not null.
		if (arguments == null) {
			throw new NullArgumentException("argument container");
		}
		
		//Sets the arguments of this argument container mediator.
		this.arguments = arguments;
	}
	
	//package-visible constructor
	/**
	 * Creates new argument container mediator with the given arguments.
	 * 
	 * @param arguments
	 * @throws NullArgumentException if the given argument container is null.
	 */
	ArgumentContainerMediator(final A[] arguments) {

		//Calls other constructor.
		this(ArrayHelper.createIterable(arguments));		
	}
	
	//method
	/**
	 * @throws NullArgumentException if one of the arguments of this argument container mediator is null.
	 */
	public final void areNotNull() {
		
		//Iterates the arguments of this argument container mediator.
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
	 * @throws InvalidArgumentException if an argument of this argument container does not fulfil the given condition.
	 */
	public final void fulfil(final IElementTakerBooleanGetter<A> condition) {
		
		//Iterates the arguments of this argument container mediator.
		int index = 1;
		for (final A a : getRefArguments()) {
			
			//Checks if the current argument fulfils the given condition.
			if (!condition.getOutput(a)) {
				throw new InvalidArgumentException(
					new ArgumentName(index + "th argument"),
					new Argument(a),
					new ErrorPredicate("does not fulfil the given exception")
				);
			}
			
			//Increments the index.
			index++;
		}
	}
	
	//method
	/**
	 * @return the arguments of this argument container mediator.
	 */
	protected final Iterable<A> getRefArguments() {
		return arguments;
	}
}
