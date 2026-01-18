/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.core.container.immutablelist;

import java.util.stream.Stream;

import ch.nolix.core.commontypetool.arraytool.ArrayIterator;
import ch.nolix.core.commontypetool.iteratortool.IterableTool;
import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.misc.variable.LowerCaseVariableCatalog;

/**
 * A {@link ImmutableList} is not mutable.
 * 
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> extends AbstractExtendedContainer<E> {
  private static final ImmutableList<Object> EMPTY = new ImmutableList<>(new Object[0]);

  private final E[] elements;

  /**
   * Creates a new {@link ImmutableList} with the given element.
   * 
   * @param element
   * @throws ArgumentIsNullException if the given element is null.
   */
  @SuppressWarnings("unchecked")
  private ImmutableList(final E element) {
    Validator.assertThat(element).thatIsNamed(LowerCaseVariableCatalog.ELEMENT).isNotNull();

    elements = (E[]) new Object[] { element };
  }

  /**
   * Creates a new {@link ImmutableList} with the given elements.
   * 
   * @param elements
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  private ImmutableList(final E[] elements) {
    Validator.assertThatTheElements(elements).areNotNull();

    this.elements = elements.clone();
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * Creates a new {@link ImmutableList} with the given elements.
   * 
   * @param elements
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  @SuppressWarnings("unchecked")
  private ImmutableList(final Iterable<E> elements) {
    final var elementCount = getCountOfIterable(elements);

    this.elements = (E[]) new Object[elementCount];

    var index = 0;

    for (final var e : elements) {
      if (e == null) {
        throw ArgumentIsNullException.forArgumentName((index + 1) + "th element");
      }

      this.elements[index] = e;

      index++;
    }
  }

  /**
   * @param <T>      is the type of the element of the given iterable.
   * @param iterable
   * @return the number of element of the given iterable.
   */
  private static <T> int getCountOfIterable(final Iterable<T> iterable) {
    int elementCount;
    if (iterable instanceof final IContainer<T> container) {
      elementCount = container.getCount();
    } else {
      elementCount = IterableTool.getCount(iterable);
    }
    return elementCount;
  }

  /**
   * @return a new empty {@link ImmutableList}.
   * @param <T> is the type of the elements the {@link ImmutableList} would have.
   */
  @SuppressWarnings("unchecked")
  public static <T> ImmutableList<T> createEmpty() {
    return (ImmutableList<T>) EMPTY;
  }

  public static <T> ImmutableList<T> fromArray(final T[] array) {
    return new ImmutableList<>(array);
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * @param <T>
   * @param iterable
   * @return a new {@link ImmutableList} with the elements of the given iterable.
   * @throws ArgumentIsNullException if the given iterable is null.
   * @throws ArgumentIsNullException if one of the elements of the given iterable
   *                                 is null.
   */
  public static <T> ImmutableList<T> fromIterable(final Iterable<T> iterable) {
    //This part is not mandatory, but provides a better performance.
    if (iterable instanceof final ImmutableList<T> immutableList) {
      return immutableList;
    }

    return new ImmutableList<>(iterable);
  }

  /**
   * @param stream
   * @param <T>    is the type of the elements of the given stream.
   * @return a new {@link ImmutableList} with the elements from the given stream.
   * @throws ArgumentIsNullException if the given stream is null.
   * @throws ArgumentIsNullException if one of the elements of the given stream is
   *                                 null.
   */
  public static <T> ImmutableList<T> fromStream(final Stream<T> stream) {
    Validator.assertThat(stream).thatIsNamed(Stream.class).isNotNull();

    return fromIterable(stream.toList());
  }

  /**
   * @param element
   * @param <T>     is the type of the given element.
   * @return a new {@link ImmutableList} with the given element.
   * @throws ArgumentIsNullException if the given element is null.
   */
  public static <T> ImmutableList<T> withElement(final T element) {
    return new ImmutableList<>(element);
  }

  /**
   * @param elements
   * @param <T>      is the type of the given elements.
   * @return a new {@link ImmutableList} with the given elements.
   * @throws ArgumentIsNullException if the given elements is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  @SafeVarargs
  public static <T> ImmutableList<T> withElements(final T... elements) {
    return new ImmutableList<>(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return elements.length;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed("1-based index").isBetween(1, getCount());

    return elements[oneBasedIndex - 1];
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
