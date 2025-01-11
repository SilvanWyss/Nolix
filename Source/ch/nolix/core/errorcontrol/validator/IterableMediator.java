package ch.nolix.core.errorcontrol.validator;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.core.independent.arraytool.ArrayTool;
import ch.nolix.core.independent.iterabletool.IterableTool;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.PluralLowerCaseVariableCatalog;

/**
 * A named container mediator is an argument mediator for an iterable object
 * with a name. A named container mediator is not mutable.
 * 
 * @author Silvan Wyss
 * @version 2017-08-15
 * @param <E> is the type of the elements of the argument of a container
 *            mediator.
 */
public class IterableMediator<E> extends ArgumentMediator<Iterable<E>> {

  private static final ArrayTool ARRAY_TOOL = new ArrayTool();

  private static final IterableTool ITERABLE_TOOL = new IterableTool();

  /**
   * Creates a new container mediator for the given argument.
   * 
   * @param argument
   */
  public IterableMediator(final Iterable<E> argument) {

    //Calls constructor of the base class.
    super(argument);
  }

  /**
   * Creates a new container mediator for the given argument with the given
   * argument name.
   * 
   * @param argumentName
   * @param argument
   * @throws ArgumentIsNullException if the given argument name is null.
   * @throws EmptyArgumentException  if the given argument is empty.
   */
  IterableMediator(
    final String argumentName,
    final Iterable<E> argument) {

    //Calls constructor of the base class.
    super(argumentName, argument);
  }

  /**
   * @param element
   * @throws ArgumentDoesNotContainElementException if the argument of the current
   *                                                {@link IterableMediator} does
   *                                                not contain the given element.
   */
  public void contains(final Object element) {
    if (!ITERABLE_TOOL.containsElement(getStoredArgument(), element)) {
      throw ArgumentDoesNotContainElementException.forArgumentNameAndArgumentAndElement(
        getArgumentName(),
        getStoredArgument(),
        element);
    }
  }

  /**
   * @param condition
   * @throws ArgumentIsNullException  if the given condition is null.
   * @throws ArgumentIsNullException  if the argument of this container mediator
   *                                  is null.
   * @throws InvalidArgumentException if the argument of this container mediator
   *                                  does not contain an element that fulfills
   *                                  the given condition.
   */
  public void contains(final Predicate<E> condition) {

    //Asserts that the given condition is not null.
    if (condition == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.CONDITION);
    }

    //Iterates the elements of the argument of this container mediator.
    var found = false;
    for (final E e : getStoredArgument()) {

      //Handles the case that the current element fulfills the given condition.
      if (condition.test(e)) {
        found = true;
        break;
      }
    }

    if (!found) {
      throw InvalidArgumentException.forArgumentAndErrorPredicate(
        getStoredArgument(),
        "does not contain an element that fulfils the given condition");
    }
  }

  public void containsAll(final Object[] elements) {

    if (elements == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalog.ELEMENTS);
    }

    for (final var e : elements) {
      contains(e);
    }
  }

  public void containsAll(final Iterable<Object> elements) {

    if (elements == null) {
      throw ArgumentIsNullException.forArgumentName(PluralLowerCaseVariableCatalog.ELEMENTS);
    }

    elements.forEach(this::contains);
  }

  public void containsAsManyElementsAs(final Object[] array) {

    if (array == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ARRAY);
    }

    hasElementCount(array.length);
  }

  public void containsAsManyElementsAs(final Iterable<?> iterable) {

    final var elementCountOfIterable = ITERABLE_TOOL.getElementCount(iterable);

    hasElementCount(elementCountOfIterable);
  }

  public void containsExactly(final Object firstElement, final Object... elements) {

    final var allElements = ARRAY_TOOL.createArrayWithElement(firstElement, elements);

    hasElementCount(allElements.length);

    containsAll(allElements);
  }

  public void containsExactlyEqualing(final Object firstElement, final Object... elements) {

    final var localElements = ARRAY_TOOL.createArrayWithElement(firstElement, elements);

    containsExactlyEqualing(localElements);
  }

  public void containsExactlyEqualing(final Object[] elements) {

    containsAsManyElementsAs(elements);

    var index = 0;
    for (final var e : getStoredArgument()) {

      if (!Objects.equals(e, elements[index])) {
        throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          (index + 1) + "th element",
          e,
          "does not equal the element '" + elements[index] + "'");
      }

      index++;
    }
  }

  public void containsExactlyInSameOrder(final E element, final @SuppressWarnings("unchecked") E... elements) {

    final var localElements = ARRAY_TOOL.createArrayWithElement(element, elements);

    containsExactlyInSameOrder(localElements);
  }

  public void containsExactlyInSameOrder(final E[] elements) {

    containsAsManyElementsAs(elements);

    var index = 0;
    for (final var e : getStoredArgument()) {

      if (e != elements[index]) {
        throw //
        InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          (index + 1) + "th element",
          e,
          "is not the same as the element '" + elements[index] + "'");
      }

      index++;
    }
  }

  public void containsExactlyInSameOrder(final Iterable<E> elements) {

    containsAsManyElementsAs(elements);

    final var iterator = elements.iterator();
    var index = 1;
    for (final var e : getStoredArgument()) {

      final var element = iterator.next();

      if (e != element) {
        throw //
        InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          index + "th element",
          e,
          "is not the same as the element '" + element + "'");
      }

      index++;
    }
  }

  /**
   * @param stringRepresentation
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link IterableMediator} does not contain an
   *                                  element with the given stringRepresentation.
   */
  public void containsExactlyOneWithStringRepresentation(final String stringRepresentation) {
    if (!ITERABLE_TOOL.containsExactlyOneWithStringRepresentation(getStoredArgument(), stringRepresentation)) {
      throw //
      InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not contain an element with the String representation '" + stringRepresentation + "'");
    }
  }

  /**
   * @param element
   * @throws InvalidArgumentException if the argument of the current
   *                                  {@link IterableMediator} does not contain
   *                                  the given element or contains the given
   *                                  element for several times.
   */
  public void containsOnce(final Object element) {
    if (!ITERABLE_TOOL.containsElementOnce(getStoredArgument(),
      element)) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "does not contain the the given element once");
    }
  }

  /**
   * @param elementCount
   * @throws NegativeArgumentException if the given element count is negative.
   * @throws InvalidArgumentException  if the argument of this container mediator
   *                                   contains less or more elements than the
   *                                   given element count says.
   */
  public void hasElementCount(final int elementCount) {

    //Asserts that the given element count is not negative.
    if (elementCount < 0) {
      throw NegativeArgumentException.forArgumentNameAndArgument(
        LowerCaseVariableCatalog.ELEMENT_COUNT,
        elementCount);
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
        throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
          getArgumentName(),
          getStoredArgument(),
          "contains more than " + elementCount + " elements");
      }

      iterator.next();
    }

    //Asserts that the argument of this container mediator
    //contains not less elements than the given element count says.
    if (actualElementCount < elementCount) {
      throw InvalidArgumentException.forArgumentNameAndArgumentAndErrorPredicate(
        getArgumentName(),
        getStoredArgument(),
        "contains less than " + elementCount + " elements");
    }
  }

  /**
   * @param array
   * @throws ArgumentIsNullException  if the given array is null.
   * @throws InvalidArgumentException if the argument of this container mediator
   *                                  contains less or more elements than the
   *                                  given array.
   */
  public void hasSameSizeAs(final double[] array) {

    //Asserts that the given array is not null.
    if (array == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ARRAY);
    }

    //Calls other method.
    hasElementCount(array.length);
  }

  /**
   * @throws ArgumentIsNullException   if the argument of this container mediator
   *                                   is null.
   * @throws NonEmptyArgumentException if the argument of this container mediator
   *                                   is empty.
   */
  public void isEmpty() {

    //Asserts that the argument of this container mediator is not null.
    isNotNull();

    //Asserts that the argument of this container mediator is empty.
    if (!ITERABLE_TOOL.isEmpty(getStoredArgument())) {
      throw NonEmptyArgumentException.forArgumentNameAndArgument(getArgumentName(), getStoredArgument());
    }
  }

  /**
   * @throws ArgumentIsNullException if the argument of this container mediator is
   *                                 null.
   * @throws EmptyArgumentException  if the argument of this container mediator is
   *                                 empty.
   */
  public void isNotEmpty() {

    //Asserts that the argument of this container mediator is not null.
    isNotNull();

    //Asserts that the argument of this container mediator is not empty.
    if (ITERABLE_TOOL.isEmpty(getStoredArgument())) {
      throw EmptyArgumentException.forArgument(getStoredArgument());
    }
  }
}
