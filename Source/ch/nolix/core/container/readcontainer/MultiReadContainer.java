//package declaration
package ch.nolix.core.container.readcontainer;

//Java imports
import java.util.function.Function;

//own imports
import ch.nolix.core.container.base.Container;
import ch.nolix.core.container.base.Marker;
import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsOutOfRangeException;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coreapi.containerapi.listapi.ILinkedList;
import ch.nolix.coreapi.programatomapi.stringcatalogueapi.CharacterCatalogue;

//class
public final class MultiReadContainer<E> extends Container<E> {

  //attribute
  private final IContainer<IContainer<E>> containers;

  //constructor 
  private MultiReadContainer() {
    containers = ImmutableList.createEmpty();
  }

  //constructor
  private MultiReadContainer(final E[] array, @SuppressWarnings("unchecked") final E[]... arrays) {

    final ILinkedList<IContainer<E>> localContainers = LinkedList.createEmpty();

    localContainers.addAtEnd(ArrayReadContainer.forArray(array));

    for (final var a : arrays) {
      localContainers.addAtEnd(ArrayReadContainer.forArray(a));
    }

    containers = localContainers;
  }

  //constructor
  private MultiReadContainer(
    final Iterable<? extends E> iterable,
    @SuppressWarnings("unchecked") final Iterable<? extends E>... iterables) {

    final ILinkedList<IContainer<E>> localContainers = LinkedList.createEmpty();

    localContainers.addAtEnd(IterableReadContainer.forIterable(iterable));

    for (final var i : iterables) {
      localContainers.addAtEnd(IterableReadContainer.forIterable(i));
    }

    containers = localContainers;
  }

  //static method
  public static <E2> MultiReadContainer<E2> forArray(
    final E2[] array,
    @SuppressWarnings("unchecked") final E2[]... arrays) {
    return new MultiReadContainer<>(array, arrays);
  }

  //static method
  public static <E2> MultiReadContainer<E2> forEmpty() {
    return new MultiReadContainer<>();
  }

  //static method
  @SafeVarargs
  public static <E2> MultiReadContainer<E2> forIterable(
    final Iterable<? extends E2> iterable,
    final Iterable<? extends E2>... iterables) {
    return new MultiReadContainer<>(iterable, iterables);
  }

  //method
  @Override
  public CopyableIterator<E> iterator() {
    return MultiReadContainerIterator.forContainers(containers);
  }

  //method
  @Override
  public int getCount() {
    return containers.getSumOfIntegers(IContainer::getCount).intValue();
  }

  //method
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

  //method
  @Override
  public E getStoredLast() {
    return containers.getStoredLast().getStoredLast();
  }

  //method
  @Override
  public boolean isMaterialized() {
    return false;
  }

  //method
  @Override
  public <C extends Comparable<C>> IContainer<E> toOrderedList(final Function<E, C> norm) {
    return containers.toFromGroups(c -> c).toOrderedList(norm);
  }

  //method
  @Override
  public String toString() {
    return toStringWithSeparator(CharacterCatalogue.COMMA);
  }

  //method
  @Override
  protected <E2> ILinkedList<E2> createEmptyMutableList(final Marker<E2> marker) {
    return LinkedList.createEmpty();
  }
}
