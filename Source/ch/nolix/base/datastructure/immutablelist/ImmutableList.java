/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.immutablelist;

import java.util.stream.Stream;

import ch.nolix.base.commontype.arraytool.ArrayIterator;
import ch.nolix.base.commontype.iterablesearcher.IterableSearcher;
import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * A {@link ImmutableList} is not mutable.
 * 
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> extends AbstractExtendedIterable<E> {
  private static final IterableSearcher ITERABLE_SEARCHER = new IterableSearcher();

  private static final ImmutableList<Object> EMPTY = new ImmutableList<>(new Object[0]);

  private final E[] elements;

  /**
   * Creates a new {@link ImmutableList} with the given element.
   * 
   * @param element
   * @throws RuntimeException if the given element is null
   */
  @SuppressWarnings("unchecked")
  private ImmutableList(final E element) {
    Validator.assertThat(element).thatIsNamed(LowerCaseVariableNameCatalog.ELEMENT).isNotNull();

    elements = (E[]) new Object[] { element };
  }

  /**
   * Creates a new {@link ImmutableList} with the given elements.
   * 
   * @param elements
   * @throws RuntimeException if the given element is null
   * @throws RuntimeException if one of the given elements is null
   */
  private ImmutableList(final E[] elements) {
    Validator.assertThatTheElements(elements).areNotNull();

    this.elements = elements.clone();
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * Creates a new {@link ImmutableList} with the given elements.
   * 
   * @param elements
   * @throws RuntimeException if the given element is null
   * @throws RuntimeException if one of the given elements is null
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
   * @param <T>      the type of the element of the given iterable
   * @param iterable
   * @return the number of element of the given iterable.
   */
  private static <T> int getCountOfIterable(final Iterable<T> iterable) {
    int elementCount;
    if (iterable instanceof final ExtendedIterable<T> container) {
      elementCount = container.getCount();
    } else {
      elementCount = ITERABLE_SEARCHER.getCount(iterable);
    }
    return elementCount;
  }

  /**
   * @return a new empty {@link ImmutableList}
   * @param <T> the type of the elements the {@link ImmutableList} would have.
   */
  @SuppressWarnings("unchecked")
  public static <T> ImmutableList<T> createEmpty() {
    return (ImmutableList<T>) EMPTY;
  }

  public static <T> ImmutableList<T> fromArray(final T[] array) {
    return new ImmutableList<>(array);
  }

  // For a better performance, this implementation does not use all available comfort methods.
  /**
   * @param <T>
   * @param iterable
   * @return a new {@link ImmutableList} with the elements of the given iterable
   * @throws RuntimeException if the given iterable is null
   * @throws RuntimeException if one of the elements of the given iterable is
   *                          null.
   */
  public static <T> ImmutableList<T> fromIterable(final Iterable<T> iterable) {
    // This part is not mandatory, but provides a better performance.
    if (iterable instanceof final ImmutableList<T> immutableList) {
      return immutableList;
    }

    return new ImmutableList<>(iterable);
  }

  /**
   * @param stream
   * @param <T>    the type of the elements of the given stream
   * @return a new {@link ImmutableList} with the elements from the given stream
   * @throws RuntimeException if the given stream is null
   * @throws RuntimeException if one of the elements of the given stream is null
   */
  public static <T> ImmutableList<T> fromStream(final Stream<T> stream) {
    Validator.assertThat(stream).thatIsNamed(Stream.class).isNotNull();

    return fromIterable(stream.toList());
  }

  /**
   * @param element
   * @param <T>     the type of the given element
   * @return a new {@link ImmutableList} with the given element
   * @throws RuntimeException if the given element is null
   */
  public static <T> ImmutableList<T> withElement(final T element) {
    return new ImmutableList<>(element);
  }

  /**
   * @param elements
   * @param <T>      the type of the given elements
   * @return a new {@link ImmutableList} with the given elements
   * @throws RuntimeException if the given elements is null
   * @throws RuntimeException if one of the given elements is null
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

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] toArray() {
    final var count = elements.length;
    final var array = new Object[count];

    System.arraycopy(elements, 0, array, 0, count);

    return array;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithDelimiter(CharacterCatalog.COMMA);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <T> IArrayList<T> createEmptyArrayListFromMarkerWithInitialCapacity(
    final Marker<T> marker,
    final int initialCapacity) {
    return ArrayList.withInitialCapacity(initialCapacity);
  }
}
