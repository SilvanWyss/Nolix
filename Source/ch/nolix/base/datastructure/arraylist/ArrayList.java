/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.base.datastructure.arraylist;

import ch.nolix.base.commontype.iterablesearcher.IterableSearcher;
import ch.nolix.base.datastructure.extendediterable.AbstractExtendedIterable;
import ch.nolix.base.validation.validator.Validator;
import ch.nolix.baseapi.datastructure.copyableiterator.CopyableIterator;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.IArrayList;
import ch.nolix.baseapi.datastructure.set.CountRequestable;
import ch.nolix.baseapi.foundation.marker.Marker;
import ch.nolix.baseapi.generalcatalog.textcatalog.CharacterCatalog;
import ch.nolix.baseapi.generalcatalog.variablenamecatalog.LowerCaseVariableNameCatalog;

/**
 * @author Silvan Wyss
 * @param <E> the type of the elements of a {@link ArrayList}
 */
public final class ArrayList<E> extends AbstractExtendedIterable<E> implements IArrayList<E> {
  private static final IterableSearcher ITERABLE_SEARCHER = new IterableSearcher();

  private int memberElementCount;

  @SuppressWarnings("unchecked")
  private E[] memberElements = (E[]) new Object[0];

  /**
   * Creates a new empty {@link ArrayList}
   */
  private ArrayList() {
  }

  /**
   * @return a new empty {@link ArrayList}
   * @param <T> the type of the elements of the {@link ArrayList}
   */
  public static <T> ArrayList<T> createEmpty() {
    return new ArrayList<>();
  }

  /**
   * The time complexity of this implementation is O(n) when n elements are given.
   * 
   * @param elements
   * @param <T>      the type of the given elements
   * @return a new {@link ArrayList} with the given elements
   * @throws RuntimeException if the given elements is null
   * @throws RuntimeException if one of the given elements is null
   */
  @SafeVarargs
  public static <T> ArrayList<T> withElements(T... elements) {
    final var arrayList = new ArrayList<T>();

    arrayList.addAtEnd(elements);

    return arrayList;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @param initialCapacity
   * @param <T>             the type of the elements of the created
   *                        {@link ArrayList}
   * @return a new {@link ArrayList} with the given initialCapacity
   * @throws RuntimeException if the given initialCapacity is negative
   */
  public static <T> ArrayList<T> withInitialCapacity(final int initialCapacity) {
    Validator
      .assertThat(initialCapacity)
      .thatIsNamed(LowerCaseVariableNameCatalog.INITIAL_CAPACITY)
      .isNotNegative();

    final var arrayList = new ArrayList<T>();
    arrayList.growToCapacityWhenCapacityIsBiggerThanCurrentCapacity(initialCapacity);

    return arrayList;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @param container
   * @param <T>       the type of the elements of the created {@link ArrayList}
   * @return a new {@link ArrayList} with a initialCapacity that is the size of
   *         the given container
   * @throws NullPointerException if the given container is null
   */
  public static <T> ArrayList<T> withInitialCapacityFromSizeOfContainer(final CountRequestable<?> container) {
    final var initialCapacity = container.getCount();

    return withInitialCapacity(initialCapacity);
  }

  private static int getCountOfIterable(final Iterable<?> iterable) {
    if (iterable instanceof final ExtendedIterable<?> container) {
      return container.getCount();
    }

    return ITERABLE_SEARCHER.getCount(iterable);
  }

  /**
   * The time complexity of this implementation is O(n) when the current
   * {@link ArrayList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final E element) {
    Validator.assertThat(element).thatIsNamed(LowerCaseVariableNameCatalog.ELEMENT).isNotNull();

    final var localElementCount = getCount();
    final var newElementCount = localElementCount + 1;

    growAtLeastToRequiredCapacity(newElementCount);
    memberElements[localElementCount] = element;
    memberElementCount = newElementCount;
  }

  /**
   * The time complexity of this implementation is O(n+m) when the current
   * {@link ArrayList} contains n elements and m elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final Iterable<? extends E> elements) {
    Validator.assertThatTheElements(elements).areNotNull();

    final var newElementCount = getCount() + getCountOfIterable(elements);

    growAtLeastToRequiredCapacity(newElementCount);

    var index = getCount();

    for (final var e : elements) {
      memberElements[index] = e;

      index++;
    }

    memberElementCount = newElementCount;
  }

  /**
   * The time complexity of this implementation is O(n+m) when the current
   * {@link ArrayList} contains n elements and m elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public <T extends E> void addAtEnd(@SuppressWarnings("unchecked") final T... elements) {
    Validator.assertThatTheElements(elements).areNotNull();

    final var elementCount = getCount();
    final var newElementCount = elementCount + elements.length;

    growAtLeastToRequiredCapacity(newElementCount);
    System.arraycopy(elements, 0, memberElements, elementCount, elements.length);
    memberElementCount = newElementCount;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    memberElements = (E[]) new Object[0];

    memberElementCount = 0;
  }

  /**
   * The time complexity of this implementation is O(n) when the current
   * {@link ArrayList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public IArrayList<E> getCopy() {
    return withElements(memberElements);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return memberElementCount;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    Validator.assertThat(oneBasedIndex).thatIsNamed("one-based index").isBetween(1, getCount());

    return memberElements[oneBasedIndex - 1];
  }

  /**
   * The time complexity of this implementation is O(n) if the given oneBasedIndex
   * is bigger than the number of the elements of the current {@link ArrayList}
   * and the current {@link ArrayList} contains n elements.
   * 
   * The time complexity of this implementation is O(1) if the given oneBasedIndex
   * is not bigger than the number of the elements of the current
   * {@link ArrayList}
   * 
   * {@inheritDoc}
   */
  @Override
  public void insertAtOneBasedIndex(final int oneBasedIndex, final E element) {
    Validator.assertThat(oneBasedIndex).thatIsNamed("one-based index").isBetween(1, getCount() + 1);

    if (oneBasedIndex <= getCount()) {
      Validator.assertThat(element).thatIsNamed(LowerCaseVariableNameCatalog.ELEMENT).isNotNull();

      memberElements[oneBasedIndex - 1] = element;
    } else {
      addAtEnd(element);
    }
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayListIterator.forArrayAndMaxNextIndex(memberElements, getCount());
  }

  //For a better performance, this implementation does not use all available comfort methods.
  /**
   * {@inheritDoc}
   */
  @Override
  public Object[] toArray() {
    final var array = new Object[memberElementCount];

    System.arraycopy(memberElements, 0, array, 0, memberElementCount);

    return array;
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link ArrayList} contains n elements.
   * 
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
    return withInitialCapacity(initialCapacity);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @param requiredCapacity
   * @return the target capacity for the current {@link ArrayList} and the given
   *         requiredCapacity for the case when the current {@link ArrayList}
   *         needs to grow for the given requiredCapacity.
   */
  private int calculateTargetCapacityForRequiredCapacityWhenNeedsToGrowForIt(final int requiredCapacity) {
    return //
    ArrayListCapacityCalculator.calculateTargetCapacityForActualCapacityAndRequiredCapacity(
      getCapacity(),
      requiredCapacity);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return the capacity of the current {@link ArrayList}
   */
  private int getCapacity() {
    return memberElements.length;
  }

  /**
   * Lets the current {@link ArrayList} grow at least to the given
   * requiredCapacity.
   * 
   * @param requiredCapacity
   */
  private void growAtLeastToRequiredCapacity(final int requiredCapacity) {
    if (needsToGrowForRequiredCapacity(requiredCapacity)) {
      growAtLeastToRequiredCapacityWhenNeedsToGrowForIt(requiredCapacity);
    }
  }

  /**
   * Lets the current {@link ArrayList} grow at least to the given
   * requiredCapacity for the case that the current {@link ArrayList} needs to
   * grow for the requiredCapacity.
   * 
   * @param requiredCapacity
   */
  private void growAtLeastToRequiredCapacityWhenNeedsToGrowForIt(final int requiredCapacity) {
    final var targetCapacity = calculateTargetCapacityForRequiredCapacityWhenNeedsToGrowForIt(requiredCapacity);

    growToCapacityWhenCapacityIsBiggerThanCurrentCapacity(targetCapacity);
  }

  /**
   * Lets the current {@link ArrayList} grow to the given capacity for the case
   * when the given capacity is bigger than the capacity of the current
   * {@link ArrayList}.
   * 
   * @param capacity
   */
  private void growToCapacityWhenCapacityIsBiggerThanCurrentCapacity(final int capacity) {
    @SuppressWarnings("unchecked")
    final var newElements = (E[]) new Object[capacity];

    System.arraycopy(memberElements, 0, newElements, 0, getCount());

    memberElements = newElements;
  }

  /**
   * @param requiredCapacity
   * @return true if the current {@ink ArrayList} needs to grow to reach the
   *         capacity the given requiredCapacity says, false otherwise
   */
  private boolean needsToGrowForRequiredCapacity(final int requiredCapacity) {
    return ArrayListCapacityCalculator.arrayListNeedsToGrowForRequiredCapacity(getCapacity(), requiredCapacity);
  }
}
