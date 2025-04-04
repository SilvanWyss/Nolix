package ch.nolix.core.container.arraylist;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.commoncontainerapi.ICountingContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2024-01-30
 * @param <E> is the type of the elements of a {@link ArrayList}.
 */
public final class ArrayList<E> extends Container<E> implements IArrayList<E> {

  private static final ArrayListCapacityCalculator ARRAY_LIST_CAPACITY_CALCULATOR = new ArrayListCapacityCalculator();

  private int elementCount;

  @SuppressWarnings("unchecked")
  private E[] elements = (E[]) new Object[0];

  /**
   * Creates a new empty {@link ArrayList}.
   */
  private ArrayList() {
  }

  /**
   * @return a new empty {@link ArrayList}.
   * @param <E2> is the type of the elements of the {@link ArrayList}.
   */
  public static <E2> ArrayList<E2> createEmpty() {
    return new ArrayList<>();
  }

  /**
   * @param element
   * @param elements
   * @param <E2>     is the type of the given element and the given elements.
   * @return a new {@link ArrayList} with the given element and elements.
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if the given elements is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  public static <E2> ArrayList<E2> withElement(final E2 element, final @SuppressWarnings("unchecked") E2... elements) {

    final var arrayList = new ArrayList<E2>();

    arrayList.addAtEnd(element, elements);

    return arrayList;
  }

  /**
   * The time complexity of this implementation is O(n) when n elements are given.
   * 
   * @param elements
   * @param <E2>     is the type of the given elements.
   * @return a new {@link ArrayList} with the given elements.
   * @throws ArgumentIsNullException if the given elements is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  public static <E2> ArrayList<E2> withElements(final E2[] elements) {

    final var arrayList = new ArrayList<E2>();

    arrayList.addAtEnd(elements);

    return arrayList;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @param initialCapacity
   * @param <E2>            is the type of the elements of the created
   *                        {@link ArrayList}.
   * @return a new {@link ArrayList} with the given initialCapacity
   * @throws NegativeArgumentException if the given initialCapacity is negative.
   */
  public static <E2> ArrayList<E2> withInitialCapacity(final int initialCapacity) {

    Validator
      .assertThat(initialCapacity)
      .thatIsNamed(LowerCaseVariableCatalog.INITIAL_CAPACITY)
      .isNotNegative();

    final var arrayList = new ArrayList<E2>();
    arrayList.growToCapacityWhenCapacityIsBiggerThanCurrentCapacity(initialCapacity);

    return arrayList;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @param container
   * @param <E2>      is the type of the elements of the created
   *                  {@link ArrayList}.
   * @return a new {@link ArrayList} with a initialCapacity that is the size of
   *         the given container.
   * @throws NullPointerException if the given container is null.
   */
  public static <E2> ArrayList<E2> withInitialCapacityFromSizeOfContainer(final ICountingContainer<?> container) {

    final var initialCapacity = container.getCount();

    return withInitialCapacity(initialCapacity);
  }

  /**
   * The time complexity of this implementation is O(n+m) when the current
   * {@link ArrayList} contains n elements and m elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final E element, @SuppressWarnings("unchecked") final E... elements) {

    Validator.assertThat(element).thatIsNamed(LowerCaseVariableCatalog.ELEMENT).isNotNull();
    Validator.assertThatTheElements(elements).areNotNull();

    final var localElementCount = getCount();
    final var newElementCount = localElementCount + 1 + elements.length;

    growAtLeastToRequiredCapacity(newElementCount);
    this.elements[localElementCount] = element;
    System.arraycopy(elements, 0, this.elements, localElementCount + 1, elements.length);
    elementCount = newElementCount;
  }

  /**
   * The time complexity of this implementation is O(n+m) when the current
   * {@link ArrayList} contains n elements and m elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(E[] elements) {

    Validator.assertThatTheElements(elements).areNotNull();

    final var localElementCount = getCount();
    final var newElementCount = localElementCount + elements.length;

    growAtLeastToRequiredCapacity(newElementCount);
    System.arraycopy(elements, 0, this.elements, localElementCount, elements.length);
    elementCount = newElementCount;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public void clear() {

    elements = (E[]) new Object[0];

    elementCount = 0;
  }

  /**
   * The time complexity of this implementation is O(n) when the current
   * {@link ArrayList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public IArrayList<E> createCopy() {
    return withElements(elements);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return elementCount;
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int param1BasedIndex) {

    Validator.assertThat(param1BasedIndex).thatIsNamed("1 based index").isBetween(1, getCount());

    return elements[param1BasedIndex - 1];
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
    return ArrayListIterator.forArrayAndMaxNextIndex(elements, getCount());
  }

  /**
   * This implementation uses the merge sort algorithm. The complexity of this
   * implementation is O(n*log(n)) if the current {@link ArrayList} contains n
   * elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return LinkedList.fromIterable(this).toOrderedList(norm);
  }

  /**
   * The time complexity of this implementation is O(n) if the current
   * {@link ArrayList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
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
    ARRAY_LIST_CAPACITY_CALCULATOR.calculateTargetCapacityForActualCapacityAndRequiredCapacity(
      getCapacity(),
      requiredCapacity);
  }

  /**
   * The time complexity of this implementation is O(1).
   * 
   * @return the capacity of the current {@link ArrayList}.
   */
  private int getCapacity() {
    return elements.length;
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

    System.arraycopy(elements, 0, newElements, 0, getCount());

    elements = newElements;
  }

  /**
   * @param requiredCapacity
   * @return true if the current {@ink ArrayList} needs to grow to reach the
   *         capacity the given requiredCapacity says, false otherwise.
   */
  private boolean needsToGrowForRequiredCapacity(final int requiredCapacity) {
    return ARRAY_LIST_CAPACITY_CALCULATOR.arrayListNeedsToGrowForRequiredCapacity(getCapacity(), requiredCapacity);
  }
}
