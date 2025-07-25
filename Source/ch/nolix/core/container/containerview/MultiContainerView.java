package ch.nolix.core.container.containerview;

import ch.nolix.core.container.arraylist.AbstractExtendedContainer;
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coreapi.container.iterator.CopyableIterator;
import ch.nolix.coreapi.container.list.IArrayList;
import ch.nolix.coreapi.programatom.stringcatalog.CharacterCatalog;

public final class MultiContainerView<E> extends AbstractExtendedContainer<E> {

  private final IContainer<IContainer<E>> containers;

  private MultiContainerView() {
    containers = ImmutableList.createEmpty();
  }

  private MultiContainerView(final E[] array, @SuppressWarnings("unchecked") final E[]... arrays) {

    final IArrayList<IContainer<E>> localContainers = ArrayList.createEmpty();

    localContainers.addAtEnd(ArrayContainerView.forArray(array));

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayContainerView.forArray(a));
    }

    containers = localContainers;
  }

  private MultiContainerView(
    final Iterable<? extends E> iterable,
    @SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {

    final IArrayList<IContainer<E>> localContainers = ArrayList.createEmpty();

    localContainers.addAtEnd(IterableContainerView.forIterable(iterable));

    for (final var i : iterables) {
      localContainers.addAtEnd(IterableContainerView.forIterable(i));
    }

    containers = localContainers;
  }

  public static <E2> MultiContainerView<E2> forArray(
    final E2[] array,
    @SuppressWarnings("unchecked") final E2[]... arrays) {
    return new MultiContainerView<>(array, arrays);
  }

  public static <E2> MultiContainerView<E2> forEmpty() {
    return new MultiContainerView<>();
  }

  @SafeVarargs
  public static <E2> MultiContainerView<E2> forIterable(
    final Iterable<? extends E2> iterable,
    final Iterable<? extends E2>... iterables) {
    return new MultiContainerView<>(iterable, iterables);
  }

  @Override
  public CopyableIterator<E> iterator() {
    return MultiContainerViewIterator.forContainers(containers);
  }

  @Override
  public int getCount() {
    return containers.getSumOfInts(IContainer::getCount).intValue();
  }

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

  @Override
  public boolean isMaterialized() {
    return false;
  }

  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalog.COMMA);
  }
}
