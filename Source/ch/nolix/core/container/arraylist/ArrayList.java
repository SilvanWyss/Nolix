//package declaration
package ch.nolix.core.container.arraylist;

//Java imports
import java.util.function.Function;

//own imports
import ch.nolix.core.commontypetool.GlobalArrayTool;
import ch.nolix.core.container.arraycontrol.ArrayIterator;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.SmallerArgumentException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.IArrayList;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * @author Silvan Wyss
 * @date 2024-01-30
 * @param <E> is the type of the elements of a {@link ArrayList}.
 */
public final class ArrayList<E> extends Container<E> implements IArrayList<E> {

  //constant
  private static final int BILLION = 1_000_000_000;

  //attribute
  private int elementCount;

  //multi-attribute
  private E[] elements;

  //constructor
  /**
   * Creates a new empty {@link ArrayList}.
   * 
   * The complexity of this implementation is O(1).
   */
  @SuppressWarnings("unchecked")
  public ArrayList() {
    elements = (E[]) new Object[0];
  }

  //constructor
  /**
   * Creates a new {@link ArrayList} with the given elements.
   * 
   * The complexity of this implementation is O(n) when n elements are given.
   * 
   * @param elements
   * @throws ArgumentIsNullException if the given elements is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  private ArrayList(final E[] elements) {

    GlobalValidator.assertThatTheElements(elements).areNotNull();

    this.elements = elements.clone();

    elementCount = this.elements.length;
  }

  //constructor
  /**
   * Creates a new {@link ImmutableList} with the given element and elements.
   * 
   * @param element
   * @param elements
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if the given elements is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  private ArrayList(final E element, final E[] elements) {

    this.elements = GlobalArrayTool.createArrayWithElement(element, elements);

    GlobalValidator.assertThatTheElements(this.elements).areNotNull();

    elementCount = this.elements.length;
  }

  //static method
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
    return new ArrayList<>(element, elements);
  }

  //static method
  /**
   * The complexity of this implementation is O(n) when n elements are given.
   * 
   * @param elements
   * @param <E2>     is the type of the given elements.
   * @return a new {@link ArrayList} with the given elements.
   * @throws ArgumentIsNullException if the given elements is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  public static <E2> ArrayList<E2> withElements(final E2[] elements) {
    return new ArrayList<>(elements);
  }

  //method
  /**
   * The complexity of this implementation is O(n+m) when the current
   * {@link ArrayList} contains n elements and m elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(final E element, @SuppressWarnings("unchecked") final E... elements) {

    GlobalValidator.assertThat(element).thatIsNamed(LowerCaseVariableCatalogue.ELEMENT).isNotNull();
    GlobalValidator.assertThatTheElements(elements).areNotNull();

    final var localElementCount = getElementCount();
    final var newElementCount = localElementCount + 1 + elements.length;

    growToMinimalCapacityIfRequired(newElementCount);
    this.elements[localElementCount] = element;
    System.arraycopy(elements, 0, this.elements, localElementCount + 1, elements.length);
    elementCount = newElementCount;
  }

  //method
  /**
   * The complexity of this implementation is O(n+m) when the current
   * {@link ArrayList} contains n elements and m elements are given.
   * 
   * {@inheritDoc}
   */
  @Override
  public void addAtEnd(E[] elements) {

    GlobalValidator.assertThatTheElements(elements).areNotNull();

    final var localElementCount = getElementCount();
    final var newElementCount = localElementCount + elements.length;

    growToMinimalCapacityIfRequired(newElementCount);
    System.arraycopy(elements, 0, this.elements, localElementCount, elements.length);
    elementCount = newElementCount;
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  public void clear() {
    elements = (E[]) new Object[0];
  }

  //method
  /**
   * The complexity of this implementation is O(n) when the current
   * {@link ArrayList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public IArrayList<E> getCopy() {
    return withElements(elements);
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public int getElementCount() {
    return elementCount;
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int param1BasedIndex) {

    GlobalValidator.assertThat(param1BasedIndex).thatIsNamed("1 based index").isBetween(1, getElementCount());

    return elements[param1BasedIndex - 1];
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public E getStoredLast() {
    return getStoredAt1BasedIndex(getElementCount());
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(elements);
  }

  //method
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

  //method
  /**
   * The complexity of this implementation is O(n) if the current
   * {@link ArrayList} contains n elements.
   * 
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return new LinkedList<>();
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return a newly calculated capacity for the current {@link ArrayList} and the
   *         given minimalCapacity.
   */
  private int calculateNewCapacityForMinimalCapacity(final int minimalCapacity) {

    final var capacity = getCapacity();

    if (minimalCapacity <= capacity) {
      return capacity;
    }

    if (minimalCapacity > BILLION) {
      return Integer.MAX_VALUE;
    }

    return (2 * capacity);
  }

  //method
  /**
   * The complexity of this implementation is O(1).
   * 
   * @return the capacity of the current {@link ArrayList}.
   */
  private int getCapacity() {
    return elements.length;
  }

  //method
  /**
   * Lets the current {@link ArrayList} grow to the given minimalCapacity.
   * 
   * @param minimalCapacity
   * @throws SmallerArgumentException if the given minimalCapacity is smaller than
   *                                  the capacity of the current
   *                                  {@link ArrayList}.
   */
  private void growToMinimalCapacity(final int minimalCapacity) {

    GlobalValidator.assertThat(minimalCapacity).thatIsNamed("minimal capacity").isBiggerThanOrEquals(getCapacity());

    final var newCapacity = calculateNewCapacityForMinimalCapacity(minimalCapacity);

    @SuppressWarnings("unchecked")
    final var newElements = (E[]) new Object[newCapacity];

    System.arraycopy(elements, 0, newElements, 0, getElementCount());

    elements = newElements;
  }

  //method
  /**
   * Lets the current {@link ArrayList} grow to the given minimalCapacity if
   * current {@ink ArrayList} is required to grow to the given minimalCapacity
   * 
   * @param minimalCapacity
   */
  private void growToMinimalCapacityIfRequired(final int minimalCapacity) {
    if (isRequiredToGrowToMinimalCapacity(minimalCapacity)) {
      growToMinimalCapacity(minimalCapacity);
    }
  }

  //method
  /**
   * @param minimalCapacity
   * @return true if the current {@ink ArrayList} is required to grow to the given
   *         minimalCapacity, false otherwise.
   */
  private boolean isRequiredToGrowToMinimalCapacity(final int minimalCapacity) {
    return (minimalCapacity > getCapacity());
  }
}
