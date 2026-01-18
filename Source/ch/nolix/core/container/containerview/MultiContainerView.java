package ch.nolix.core.container.containerview;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.coreapi.commontypetool.charactertool.CharacterCatalog;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.container.list.IArrayList;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of a {@link MultiContainerView}.
 */
public final class MultiContainerView<E> extends AbstractExtendedContainer<E> {
  private final IContainer<IContainer<E>> containers;

  private MultiContainerView() {
    containers = ImmutableList.createEmpty();
  }

  private MultiContainerView(@SuppressWarnings("unchecked") final E[]... arrays) {
    final IArrayList<IContainer<E>> localContainers = ArrayList.createEmpty();

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayContainerView.forArray(a));
    }

    containers = localContainers;
  }

  private MultiContainerView(@SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {
    final IArrayList<IContainer<E>> localContainers = ArrayList.createEmpty();

    for (final var i : iterables) {
      localContainers.addAtEnd(IterableContainerView.forIterable(i));
    }

    containers = localContainers;
  }

  public static <T> MultiContainerView<T> forArrays(
    @SuppressWarnings("unchecked") final T[]... arrays) {
    return new MultiContainerView<>(arrays);
  }

  public static <T> MultiContainerView<T> forEmpty() {
    return new MultiContainerView<>();
  }

  @SafeVarargs
  public static <T> MultiContainerView<T> forIterables(final Iterable<? extends T>... iterables) {
    return new MultiContainerView<>(iterables);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> iterator() {
    return MultiContainerViewIterator.forContainers(containers);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getCount() {
    return containers.getSumOfInts(IContainer::getCount).intValue();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E getStoredAtOneBasedIndex(final int oneBasedIndex) {
    var i = 1;
    for (final var e : this) {
      if (i == oneBasedIndex) {
        return e;
      }

      i++;
    }

    throw //
    ArgumentIsOutOfRangeException.forArgumentAndArgumentNameAndRangeWithMinAndMax(
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
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
