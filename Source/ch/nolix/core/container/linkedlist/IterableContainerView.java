package ch.nolix.core.container.linkedlist;

import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.core.errorcontrol.validator.Validator;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.stringcatalogapi.CharacterCatalog;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalog;

/**
 * @author Silvan Wyss
 * @version 2017-11-26
 * @param <E> is the type of the elements of a {@link IterableContainerView}.
 */
public final class IterableContainerView<E> extends AbstractExtendedContainer<E> {

  private final Iterable<E> iterable;

  /**
   * Creates a new {@link IterableContainerView} for a new empty container.
   */
  public IterableContainerView() {

    //Calls other constructor.
    this(LinkedList.createEmpty());
  }

  /**
   * Creates a new {@link IterableContainerView} for the given container.
   * 
   * @param container
   * @param <E2>      is the type of the elements of the given container.
   * @throws ArgumentIsNullException if the given container is null.
   */
  @SuppressWarnings("unchecked")
  private <E2 extends E> IterableContainerView(final Iterable<E2> container) {

    //Asserts that the given container is not null.
    Validator
      .assertThat(container)
      .thatIsNamed(LowerCaseVariableCatalog.CONTAINER)
      .isNotNull();

    //Sets the container of the current IterableView.
    this.iterable = (Iterable<E>) container;
  }

  /**
   * @param iterable
   * @param <E2>     is the type of the elements of the given iterable.
   * @return a new {@link IterableContainerView} for the given iterable.
   * @throws ArgumentIsNullException if the given iterable is null.
   */
  public static <E2> IterableContainerView<E2> forIterable(final Iterable<? extends E2> iterable) {
    return new IterableContainerView<>(iterable);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {

    var size = 0;

    //Iterates the current IterableView.
    final var iterator = iterable.iterator();
    while (iterator.hasNext()) {
      size++;
      iterator.next();
    }

    return size;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {

    //Iterates the current IterableView.
    var i = 1;
    for (final var e : this) {

      //Asserts that the current index is the given index.
      if (i == oneBasedIndex) {
        return e;
      }

      i++;
    }

    throw ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
      oneBasedIndex,
      "1-based index",
      1,
      getCount());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMaterialized() {
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return IterableContainerViewIterator.forIterable(iterable);
  }

  /**
   * The time complexity of this implementation is O(n). if the current
   * {@link IterableContainerView} contains n elements.
   * 
   * @return a {@link String} representation of the current
   *         {@link IterableContainerView}.
   */
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
