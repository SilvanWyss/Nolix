//package declaration
package ch.nolix.core.errorcontrol.validator;

//Java imports
import java.util.Iterator;
import java.util.Objects;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.independent.containerhelper.GlobalArrayHelper;
import ch.nolix.core.independent.containerhelper.GlobalIterableHelper;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.core.programatom.name.PluralLowerCaseCatalogue;
import ch.nolix.coreapi.functionapi.genericfunctionapi.IElementTakerBooleanGetter;

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
	public ContainerMediator(final Iterable<E> argument) {
		
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
		final Iterable<E> argument) {
		
		//Calls constructor of the base class.
		super(argumentName, argument);
	}
	
	//method
	/**
	 * @param element
	 * @throws ArgumentDoesNotContainElementException if
	 * the argument of the current {@link ContainerMediator} does not contain the given element.
	 */
	public void contains(final Object element) {
		if (!GlobalIterableHelper.containsElement(getStoredArgument(), element)) {
			throw
			ArgumentDoesNotContainElementException.forArgumentNameAndArgumentAndElement(
				getArgumentName(),
				getStoredArgument(),
				element
			);
		}
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
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.CONDITION);
		}
		
		//Iterates the elements of the argument of this container mediator.
		var found = false;
		for (final E e : getStoredArgument()) {
			
			//Handles the case that the current element fulfills the given condition.
			if (condition.getOutput(e)) {
				found = true;
				break;
			}
		}
		
		if (!found) {
			throw
			InvalidArgumentException.forArgumentAndErrorPredicate(
				getStoredArgument(),
				"does not contain an element that fulfils the given condition"
			);
		}
	}
	
	//method
	public void containsAll(final Object[] elements) {
		
		if (elements == null) {
			throw ArgumentIsNullException.forArgumentName(PluralLowerCaseCatalogue.ELEMENTS);
		}
		
		for (final var e : elements) {
			contains(e);
		}
	}
	
	//method
	public void containsAll(final Iterable<Object> elements) {
		
		if (elements == null) {
			throw ArgumentIsNullException.forArgumentName(PluralLowerCaseCatalogue.ELEMENTS);
		}
		
		elements.forEach(this::contains);
	}
	
	//method
	public void containsAsManyElementsAs(final Object[] array) {
		
		if (array == null) {
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ARRAY);
		}
		
		hasElementCount(array.length);
	}
	
	//method
	public void containsExactly(final Object firstElement, final Object... elements) {
		
		final var allElements = GlobalArrayHelper.createArrayWithElement(firstElement, elements);
		
		hasElementCount(allElements.length);
		
		containsAll(allElements);
	}
	
	//method
	public void containsExactlyEqualing(final E firstElement, final @SuppressWarnings("unchecked")E... elements) {
		
		final var localElements = GlobalArrayHelper.createArrayWithElement(firstElement, elements);
		
		containsExactlyEqualing(localElements);
	}
	
	//method
	public void containsExactlyEqualing(final E[] elements) {
		
		containsAsManyElementsAs(elements);
		
		var index = 0;
		for (final var e : getStoredArgument()) {
			
			if (!Objects.equals(e, elements[index])) {
				throw
				InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
					(index + 1) + "th element",
					e,
					"does not equal the element '" + elements[index] + "'"
				);
			}
			
			index++;
		}
	}
	
	//method
	public void containsExactlyInSameOrder(final E element, final @SuppressWarnings("unchecked")E... elements) {
		
		final var localElements = GlobalArrayHelper.createArrayWithElement(element, elements);
		
		containsAsManyElementsAs(localElements);
		
		var index = 0;
		for (final var e : getStoredArgument()) {
			
			if (e != localElements[index]) {
				throw
				InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
					(index + 1) + "th element",
					e,
					"is not the same as the element '" + elements[index] + "'"
				);
			}
			
			index++;
		}
	}
	
	//method
	/**
	 * @param element
	 * @throws InvalidArgumentException if
	 * the argument of the current {@link ContainerMediator} does not contain the given element or
	 * contains the given element for several times.
	 */
	public void containsOnce(final Object element) {
		if (!GlobalIterableHelper.containsElementOnce(getStoredArgument(), element)) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getStoredArgument(),
				"does not contain the the given element once"
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
			throw NegativeArgumentException.forArgumentNameAndArgument(
				LowerCaseCatalogue.ELEMENT_COUNT,
				elementCount
			);
		}
		
		//Asserts that the argument of this container mediator is not null.
		isNotNull();
		
		var actualElementCount = 0;
		
		//Iterates the argument of this container mediator.
		Iterator<E> iterator = getStoredArgument().iterator();
		while (iterator.hasNext()) {
			
			actualElementCount++;
			
			//Asserts that the argument of this container mediator
			//contains not more elements than the given element count says.
			if (actualElementCount > elementCount) {
				throw
				InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
					getArgumentName(),
					getStoredArgument(),
					"contains more than " + elementCount + " elements"
				);
			}
			
			iterator.next();
		}
		
		//Asserts that the argument of this container mediator
		//contains not less elements than the given element count says.
		if (actualElementCount < elementCount) {
			throw
			InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
				getArgumentName(),
				getStoredArgument(),
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
			throw ArgumentIsNullException.forArgumentName(LowerCaseCatalogue.ARRAY);
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
		if (IterableHelper.containsAny(getStoredArgument())) {
			throw NonEmptyArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
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
		if (IterableHelper.isEmpty(getStoredArgument())) {
			throw EmptyArgumentException.forArgument(getStoredArgument());
		}
	}
}
