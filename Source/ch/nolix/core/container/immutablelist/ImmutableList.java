//package declaration
package ch.nolix.core.container.immutablelist;

//Java imports
import java.util.function.Function;

import ch.nolix.core.commontypetool.commontypehelper.GlobalArrayHelper;
import ch.nolix.core.commontypetool.commontypehelper.GlobalIterableHelper;
import ch.nolix.core.container.arraycontrol.ArrayIterator;
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.ReadContainer;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.GlobalValidator;
import ch.nolix.coreapi.commontypetoolapi.stringutilapi.CharacterCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;

//class
/**
 * A {@link ImmutableList} is a {@link Container} that is not mutable.
 * 
 * @author Silvan Wyss
 * @date 2022-07-08
 * @param <E> is the type of the elements of a {@link ImmutableList}.
 */
public final class ImmutableList<E> extends Container<E> {

  //multi-attribute
  private final E[] elements;

  //constructor
  /**
   * Creates a new {@link ImmutableList} that is empty.
   */
  @SuppressWarnings("unchecked")
  public ImmutableList() {
    elements = (E[]) new Object[0];
  }

  //constructor
  /**
   * Creates a new {@link ImmutableList} with the given elements.
   * 
   * @param elements
   */
  private ImmutableList(final E[] elements) {
    this.elements = elements; //NOSONAR: A ImmutableList operates on the original instance.
  }

  //constructor
  /**
   * Creates a new {@link ImmutableList} with the given element and elements.
   * 
   * @param element
   * @param elements
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  private ImmutableList(final E element, final E[] elements) {

    this.elements = GlobalArrayHelper.createArrayWithElement(element, elements);

    GlobalValidator.assertThatTheElements(elements).areNotNull();
  }

  //static method
  public static <E2> ImmutableList<E2> forArray(final E2[] array) {
    return new ImmutableList<>(array.clone());
  }

  //static method
  //For a better performance, this implementation does not use all comfortable
  //methods.
  /**
   * @param <E2>
   * @param container
   * @return a new {@link ImmutableList} with the elements from the given
   *         container.
   * @throws ArgumentIsNullException if one of the elements of the given container
   *                                 is null.
   */
  @SuppressWarnings("unchecked")
  public static <E2> ImmutableList<E2> forIterable(final Iterable<E2> container) {

    if (container instanceof ImmutableList) {
      return (ImmutableList<E2>) container;
    }

    final var elementCount = GlobalIterableHelper.getElementCount(container);
    final var elements = new Object[elementCount];
    var index = 0;
    for (final var e : container) {

      if (e == null) {
        throw ArgumentIsNullException.forArgumentName((index + 1) + "th element");
      }

      elements[index] = e;

      index++;
    }

    return new ImmutableList<>((E2[]) elements);
  }

  //static method
  /**
   * @param element
   * @param elements
   * @param <E2>     is the type of the given element and of the given elements.
   * @return a new {@link ImmutableList} with the given element and elements.
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  public static <E2> ImmutableList<E2> withElement(
    final E2 element,
    final @SuppressWarnings("unchecked") E2... elements) {
    return new ImmutableList<>(element, elements);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public int getElementCount() {
    return elements.length;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAt1BasedIndex(final int p1BasedIndex) {

    GlobalValidator.assertThat(p1BasedIndex).thatIsNamed("1-based index").isBetween(1, getElementCount());

    return elements[p1BasedIndex - 1];
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredLast() {
    return elements[getElementCount() - 1];
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return true;
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return ArrayIterator.forArray(elements);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return ReadContainer.forIterable(this).toOrderedList(norm);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return new LinkedList<>();
  }
}
