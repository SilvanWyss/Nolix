package ch.nolix.core.container.containerview;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * A {@link ContainerView} can read a given {@link Iterable} or array. A
 * {@link ContainerView} prevents that its accessed {@link Iterable} or array
 * can be mutated. A {@link ContainerView} does not prevent that the elements of
 * its {@link Iterable} or array can be mutated.
 * 
 * @author Silvan Wyss
 * @version 2017-07-01
 * @param <E> is the type of the elements of a {@link ContainerView}.
 */
public final class ContainerView<E> extends Container<E> {

  private final IContainer<E> internalContainer;

  /**
   * Creates a new {@link ContainerView} for the given container.
   * 
   * @param container
   * @throws ArgumentIsNullException if the given container is null.
   */
  private ContainerView(final IContainer<E> container) {

    //Asserts that the given container is not null.
    Validator.assertThat(container).thatIsNamed(LowerCaseVariableCatalog.CONTAINER).isNotNull();

    //Sets the container of the current ContainerView.
    internalContainer = container;
  }

  /**
   * @param array
   * @param arrays
   * @param <E2>   is the type of the elements of the given array and arrays.
   * @return a new {@link ContainerView} for the given array and arrays.
   * @throws ArgumentIsNullException if the given array is null.
   * @throws ArgumentIsNullException if the given arrays is null.
   * @throws ArgumentIsNullException if one array of the given arrays is null.
   */
  @SuppressWarnings("unchecked")
  public static <E2> ContainerView<E2> forArray(final E2[] array, final E2[]... arrays) {

    final var container = MultiContainerView.forArray(array, arrays);

    return new ContainerView<>(container);
  }

  /**
   * @param element
   * @param array
   * @param <E2>    is the type of the given element and elements of the given
   *                array.
   * @return a new {@link ContainerView} for the given element and array.
   * @throws ArgumentIsNullException if the given element is null.
   * @throws ArgumentIsNullException if the given array is null.
   * @throws ArgumentIsNullException if one element of the given arrays is null.
   */
  public static <E2> ContainerView<E2> forElementAndArray(final E2 element, final E2[] array) {

    @SuppressWarnings("unchecked")
    final var arrayWithElement = (E2[]) new Object[] { element };

    @SuppressWarnings("unchecked")
    final var container = MultiContainerView.forArray(arrayWithElement, array);

    return new ContainerView<>(container);
  }

  /**
   * @param <E2> is the type of the hypothetical elements of the created empty
   *             {@link ContainerView}.
   * @return a new empty {@link ContainerView}.
   */
  public static <E2> ContainerView<E2> forEmpty() {

    final IContainer<E2> container = ImmutableList.createEmpty();

    return new ContainerView<>(container);
  }

  /**
   * @param iterable
   * @param iterables
   * @param <E2>      is the type of the elements of the given iterable and
   *                  iterables.
   * @return a new {@link ContainerView} for the given iterable and iterables.
   * @throws ArgumentIsNullException if the given iterable is null.
   * @throws ArgumentIsNullException if the given iterables is null.
   * @throws ArgumentIsNullException if one of the given iterables is null.
   */
  @SafeVarargs
  public static <E2> ContainerView<E2> forIterable(
    final Iterable<? extends E2> iterable,
    final Iterable<? extends E2>... iterables) {

    final var container = MultiContainerView.forIterable(iterable, iterables);

    return new ContainerView<>(container);
  }

  /**
   * @param iterable
   * @param element
   * @param elements
   * @param <E2>     is the type of the elements of the given iterable and element
   *                 and elements.
   * @return a new {@link ContainerView} for the given iterable and element and
   *         elements.
   * @throws ArgumentIsNullException if the given iterable is null.
   * @throws ArgumentIsNullException if one of the given elements is null.
   */
  @SafeVarargs
  public static <E2> ContainerView<E2> forIterableAndElement(
    final Iterable<? extends E2> iterable,
    final E2 element,
    final E2... elements) {

    @SuppressWarnings("unchecked")
    final var container = //
    MultiContainerView.forIterable(iterable, ImmutableList.withElement(element), ArrayView.forArray(elements));

    return new ContainerView<>(container);
  }

  /**
   * An object equals a {@link ContainerView} when the object is a
   * {@link Iterable} that contains exactly the same elements in the same order
   * like the {@link ContainerView}.
   * 
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object object) {

    //Handles the case that the given object is a Iterable.
    if (object instanceof final Iterable<?> iterable) {
      return containsExactlyInSameOrder(iterable);
    }

    //Handles the case that the given object is not a Iterable.
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return internalContainer.getCount();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    return internalContainer.getStoredAtOneBasedIndex(oneBasedIndex);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    return internalContainer.hashCode();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return internalContainer.isMaterialized();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return internalContainer.iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return internalContainer.toOrderedList(norm);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return internalContainer.toString();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
