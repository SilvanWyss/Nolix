//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.util.Iterator;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.functionuniversalapi.IElementTakerBooleanGetter;
import ch.nolix.core.independent.independentcontainer.ArrayReadContainer;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;

//class
/**
 * A named container mediator is an argument mediator for an iterable object with a name.
 * A named container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2017-08-15
 * @param <E> is the type of the elements of the argument of a container mediator.
 */
public class ContainerMediator<E> extends ArgumentMediator<Iterable<E>> {

	//constructor
	/**
	 * Creates a new container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final E[] argument) {
		
		//Calls constructor of the base class.
		super(new ArrayReadContainer<E>(argument));
	}
	
	//constructor
	/**
	 * Creates a new container mediator for the given argument.
	 * 
	 * @param argument
	 */
	ContainerMediator(final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argument);
	}
	
	//constructor
	/**
	 * Creates a new container mediator
	 * for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argument name is null.
	 * @throws EmptyArgumentException if the given argument is empty.
	 */
	ContainerMediator(
		final String argumentName,
		final E[] argument) {
		
		//Calls constructor of the base class.
		super(argumentName, new ArrayReadContainer<E>(argument));
	}
	
	//constructor
	/**
	 * Creates a new container mediator
	 * for the given argument with the given argument name.
	 * 
	 * @param argumentName
	 * @param argument
	 * @throws ArgumentIsNullException if the given argument name is null.
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
	 * @throws ArgumentIsNullException
	 * if the given condition is null.
	 * @throws ArgumentIsNullException
	 * if the argument of this container mediator is null.
	 * @throws InvalidArgumentException
	 * if the argument of this container mediator
	 * does not contain an element that fulfills the given condition.
	 */
	public void contains(final IElementTakerBooleanGetter<E> condition) {
		
		//Asserts that the given condition is not null.
		if (condition == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.CONDITION);
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
		
		//Asserts that the given element count is not negative.
		if (elementCount < 0) {
			throw new NegativeArgumentException(
				LowerCaseCatalogue.ELEMENT_COUNT,
				elementCount
			);
		}
		
		//Asserts that the argument of this container mediator is not null.
		isNotNull();
		
		int actualElementCount = 0;
		
		//Iterates the argument of this container mediator.
		Iterator<E> iterator = getRefArgument().iterator();
		while (iterator.hasNext()) {
			
			actualElementCount++;
			
			//Asserts that the argument of this container mediator
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
		
		//Asserts that the argument of this container mediator
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
	 * @param array
	 * @throws ArgumentIsNullException if the given array is null.
	 * @throws InvalidArgumentException if the argument of this container mediator
	 * contains less or more elements than the given array.
	 */
	public void hasSameSizeAs(final double[] array) {
		
		//Asserts that the given array is not null.
		if (array == null) {
			throw new ArgumentIsNullException(LowerCaseCatalogue.ARRAY);
		}
		
		//Calls other method.
		hasElementCount(array.length);
	}
	
	//method
	/**
	 * @throws ArgumentIsNullException if the argument of this container mediator is null.
	 * @throws NonEmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isEmpty() {
		
		//Asserts that the argument of this container mediator is not null.
		isNotNull();
		
		//Asserts that the argument of this container mediator is empty.
		if (IterableHelper.containsAny(getRefArgument())) {
			throw new NonEmptyArgumentException(getRefArgument());
		}
	}

	//method
	/**
	 * @throws ArgumentIsNullException if the argument of this container mediator is null.
	 * @throws EmptyArgumentException if the argument of this container mediator is empty.
	 */
	public void isNotEmpty() {
		
		//Asserts that the argument of this container mediator is not null.
		isNotNull();
		
		//Asserts that the argument of this container mediator is not empty.
		if (IterableHelper.isEmpty(getRefArgument())) {
			throw new EmptyArgumentException(getRefArgument());
		}
	}
}
