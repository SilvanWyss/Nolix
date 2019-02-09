//package declaration
package ch.nolix.core.validator;

//Java import
import java.util.Iterator;

//own imports
import ch.nolix.core.constants.VariableNameCatalogue;
import ch.nolix.core.functionAPI.IElementTakerBooleanGetter;
import ch.nolix.core.invalidArgumentException.EmptyArgumentException;
import ch.nolix.core.invalidArgumentException.InvalidArgumentException;
import ch.nolix.core.invalidArgumentException.NegativeArgumentException;
import ch.nolix.core.invalidArgumentException.NonEmptyArgumentException;
import ch.nolix.core.invalidArgumentException.NullArgumentException;
import ch.nolix.core.primitiveContainer.ArrayReadContainer;

//class
/**
 * A named container mediator is an argument mediator for an iterable object with a name.
 * A named container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @month 2017-08
 * @lines 210
 * @param <E> The type of the elements of the argument of a container mediator.
 */
public class ContainerMediator<E> extends ArgumentMediator<Iterable<E>> {

	//package-visible constructor
	/**
	 * Creates a new container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final E[] argument) {
		
		//Calls constructor of the base class.
		super(new ArrayReadContainer<E>(argument));
	}
	
	//package-visible constructor
	/**
	 * Creates a new container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//package-visible constructor
	/**
	 * Creates a new container mediator
	 * for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument is empty.
	 */
	ContainerMediator(
		final String argumentName,
		final E[] argument) {
		
		//Calls constructor of the base class.
		super(argumentName, new ArrayReadContainer<E>(argument));
	}
	
	//package-visible constructor
	/**
	 * Creates a new container mediator
	 * for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws NullArgumentException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument is empty.
	 */
	ContainerMediator(
		final String argumentName,
		final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//method
	/**
	 * @param condition
	 * @throws NullArgumentException
	 * if the given condition is null.
	 * @throws NullArgmentException
	 * if the argument of this container mediator is null.
	 * @throws InvalidArgumentException
	 * if the argument of this container mediator
	 * does not contain an element that fulfills the given condition.
	 */
	public void contains(final IElementTakerBooleanGetter<E> condition) {
		
		//Checks if the given condition is not null.
		if (condition == null) {
			throw new NullArgumentException(VariableNameCatalogue.CONDITION);
		}
		
		//Iterates the elements of the argument of this container mediator.
		boolean found = false;
		for (final E e : getRefArgument()) {
			
			//Handles the case that the current element fulfills the given condition.
			if (condition.getOutput(e)) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw
			new InvalidArgumentException(
				getRefArgument(),
				"does not contain element that fulfils the given condition"
			);
		}
	}
	
	//method
	/**
	 * @param elementCount
	 * @throws NegativeArgumentException if the given element count is negative.
	 * @throws InvalidArgumentException if the argument of this container mediator
	 * contains less or more elements than the given element count says.
	 */
	public void hasElementCount(final int elementCount) {
		
		//Checks if the given element count is not negative.
		if (elementCount < 0) {
			throw new NegativeArgumentException(
				VariableNameCatalogue.ELEMENT_COUNT,
				elementCount
			);
		}
		
		//Checks if the argument of this container mediator is not null.
		isNotNull();
		
		int actualElementCount = 0;
		
		//Iterates the argument of this container mediator.
		Iterator<E> iterator = getRefArgument().iterator();
		while (iterator.hasNext()) {
			
			actualElementCount++;
			
			//Checks if the argument of this container mediator
			//contains not more elements than the given element count says.
			if (actualElementCount > elementCount) {
				throw
				new InvalidArgumentException(
					getArgumentName(),
					getRefArgument(),
					"contains more than " + elementCount + " elements"
				);
			}
			
			iterator.next();
		}
		
		//Checks if the argument of this container mediator
		//contains not less elements than the given element count says.
		if (actualElementCount < elementCount) {
			throw
			new InvalidArgumentException(
				getArgumentName(),
				getRefArgument(),
				"contains less than " + elementCount + " elements"
			);
		}
	}
	
	//method
	/**
	 * @throws NullArgumentException if the argument of this container mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isEmpty() {
		
		//Checks if the argument of this container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this container mediator is empty.
		if (IterableHelper.containsAny(getRefArgument())) {
			throw new NonEmptyArgumentException(getRefArgument());
		}
	}

	//method
	/**
	 * @throws NullArgumentException if the argument of this container mediator is null.
	 * @throws EmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Checks if the argument of this container mediator is not null.
		isNotNull();
		
		//Checks if the argument of this container mediator is not empty.
		if (IterableHelper.isEmpty(getRefArgument())) {
			throw new EmptyArgumentException(getRefArgument());
		}
	}
}
