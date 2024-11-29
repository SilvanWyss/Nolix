package ch.nolix.core.container.containerview;

import java.util.function.Function;

import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;

public final class MultiContainerView<E> extends Container<E> {

  private final IContainer<IContainer<E>> containers;

  private MultiContainerView() {
    containers = ImmutableList.createEmpty();
  }

  private MultiContainerView(final E[] array, @SuppressWarnings("unchecked") final E[]... arrays) {

    final ILinkedList<IContainer<E>> localContainers = LinkedList.createEmpty();

    localContainers.addAtEnd(ArrayView.forArray(array));

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayView.forArray(a));
    }

    containers = localContainers;
  }

  private MultiContainerView(
    final Iterable<? extends E> iterable,
    @SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {

    final ILinkedList<IContainer<E>> localContainers = LinkedList.createEmpty();

    localContainers.addAtEnd(IterableView.forIterable(iterable));

    for (final var i : iterables) {
      localContainers.addAtEnd(IterableView.forIterable(i));
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
    return containers.getSumOfIntegers(IContainer::getCount).intValue();
  }

  @Override
  public E getStoredAt1BasedIndex(final int param1BasedIndex) {

    var i = 1;
    for (final var e : this) {

      if (i == param1BasedIndex) {
        return e;
      }

      i++;
    }

    throw //
    ArgumentIsOutOfRangeException.forArgumentNameAndArgumentAndRangeWithMinAndMax(
      "1-based index",
      param1BasedIndex,
      1,
      getCount());
  }

  @Override
  public boolean isMaterialized() {
    return false;
  }

  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return containers.toFromGroups(c -> c).toOrderedList(norm);
  }

  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
