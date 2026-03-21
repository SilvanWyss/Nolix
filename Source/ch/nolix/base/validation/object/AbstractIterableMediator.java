/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.validation.object;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

import ch.nolix.base.independent.arraytool.ArrayTool;
import ch.nolix.base.independent.iterabletool.IterableExaminer;
import ch.nolix.base.independent.iterabletool.IterableTool;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.EmptyArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.InvalidArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NonEmptyArgumentException;
import ch.nolix.baseapi.independent.arraytool.IArrayTool;
import ch.nolix.baseapi.independent.iterabletool.IIterableExaminer;
import ch.nolix.baseapi.misc.variable.LowerCaseVariableCatalog;
import ch.nolix.baseapi.misc.variable.PluralLowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the argument of a
 *            {@link AbstractIterableMediator}.
 */
public abstract class AbstractIterableMediator<E> extends ObjectMediator<Iterable<E>> {
  private static final IArrayTool ARRAY_TOOL = new ArrayTool();

  private static final IIterableExaminer ITERABLE_EXAMINER = new IterableExaminer();

  private static final IterableTool ITERABLE_TOOL = new IterableTool();

  protected AbstractIterableMediator(final Iterable<E> argument) {
    super(argument);
  }

  protected AbstractIterableMediator(final Iterable<E> argument, final String argumentName) {
    super(argumentName, argument);
  }

  public void contains(final Object element) {
    if (!ITERABLE_EXAMINER.containsElement(getStoredArgument(), element)) {
      throw ArgumentDoesNotContainElementException.forArgumentAndArgumentNameAndElement(
        getStoredArgument(),
        getArgumentName(),
        element);
    }
  }

  public void contains(final Predicate<E> condition) {
    if (condition == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.CONDITION);
    }

    var found = false;

    for (final E e : getStoredArgument()) {
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

  public void containsDistinctNonNullElemensOnly() {
    containsNonNullElementsOnly();

    final var argument = getStoredArgument();

    for (final var e : argument) {
      if (ITERABLE_EXAMINER.containsElementMultipleTimes(argument, e)) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          argument,
          getArgumentName(),
          "contains the element '" + e + "' multiple times.");
      }
    }
  }

  public void containsEqualing(final Object object) {
    isNotNull();

    final var argument = getStoredArgument();
    var found = false;

    for (final E e : argument) {
      if (Objects.equals(e, object)) {
        found = true;
        break;
      }
    }

    if (!found) {
      throw //
      InvalidArgumentException.forArgumentAndErrorPredicate(
        argument,
        "does not contain an element that equals '" + object + "'");
    }
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
        throw InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          e,
          (index + 1) + "th element",
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
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          e,
          (index + 1) + "th element",
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
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          e,
          index + "th element",
          "is not the same as the element '" + element + "'");
      }

      index++;
    }
  }

  /**
   * @param stringRepresentation
   * @throws RuntimeException if the argument of the current
   *                          {@link IterableMediator} does not contain an element
   *                          with the given stringRepresentation.
   */
  public void containsExactlyOneWithStringRepresentation(final String stringRepresentation) {
    if (!ITERABLE_EXAMINER.containsExactlyOneWithStringRepresentation(getStoredArgument(), stringRepresentation)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not contain an element with the String representation '" + stringRepresentation + "'");
    }
  }

  /**
   * @param element
   * @throws RuntimeException if the argument of the current
   *                          {@link IterableMediator} does not contain the given
   *                          element or contains the given element for several
   *                          times.
   */
  public void containsOnce(final Object element) {
    if (!ITERABLE_EXAMINER.containsElementOnce(getStoredArgument(),
      element)) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "does not contain the the given element once");
    }
  }

  /**
   * @param elementCount
   * @throws RuntimeException if the given element count is negative.
   * @throws RuntimeException if the argument of this container mediator contains
   *                          less or more elements than the given element count
   *                          says.
   */
  public void hasElementCount(final int elementCount) {
    if (elementCount < 0) {
      throw NegativeArgumentException.forArgumentAndArgumentName(elementCount, LowerCaseVariableCatalog.ELEMENT_COUNT);
    }

    isNotNull();

    var actualElementCount = 0;

    Iterator<E> iterator = getStoredArgument().iterator();
    while (iterator.hasNext()) {
      actualElementCount++;

      if (actualElementCount > elementCount) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          getStoredArgument(),
          getArgumentName(),
          "contains more than " + elementCount + " elements");
      }

      iterator.next();
    }

    if (actualElementCount < elementCount) {
      throw //
      InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
        getStoredArgument(),
        getArgumentName(),
        "contains less than " + elementCount + " elements");
    }
  }

  /**
   * @param array
   * @throws RuntimeException if the given array is null.
   * @throws RuntimeException if the argument of this container mediator contains
   *                          less or more elements than the given array.
   */
  public void hasSameSizeAs(final double[] array) {
    if (array == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalog.ARRAY);
    }

    hasElementCount(array.length);
  }

  /**
   * @throws RuntimeException          if the argument of this container mediator
   *                                   is null.
   * @throws NonEmptyArgumentException if the argument of this container mediator
   *                                   is empty.
   */
  public void isEmpty() {
    isNotNull();

    if (!ITERABLE_EXAMINER.isEmpty(getStoredArgument())) {
      throw NonEmptyArgumentException.forArgumentAndArgumentName(getStoredArgument(), getArgumentName());
    }
  }

  /**
   * @throws RuntimeException if the argument of this container mediator is null.
   * @throws RuntimeException if the argument of this container mediator is empty.
   */
  public void isNotEmpty() {
    isNotNull();

    if (ITERABLE_EXAMINER.isEmpty(getStoredArgument())) {
      throw EmptyArgumentException.forArgument(getStoredArgument());
    }
  }

  private void containsNonNullElementsOnly() {
    isNotNull();

    final var argument = getStoredArgument();
    var oneBasedndex = 1;

    for (final var e : argument) {
      if (e == null) {
        throw //
        InvalidArgumentException.forArgumentAndArgumentNameAndErrorPredicate(
          argument,
          getArgumentName(),
          "contains a null element at the " + oneBasedndex + "th one-based index.");
      }

      oneBasedndex++;
    }
  }
}
