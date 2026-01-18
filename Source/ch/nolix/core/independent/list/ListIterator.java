package ch.nolix.core.independent.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Silvan Wyss
 * @param <E> is the type of the elements of the parent {@link List} of a
 *            {@link ListIterator}.
 */
public final class ListIterator<E> implements Iterator<E> {
  private ListNode<E> nextNode;

  private ListIterator() {
  }

  private ListIterator(final ListNode<E> startNode) {
    this.nextNode = startNode;
  }

  public static <T> ListIterator<T> forEmptyList() {
    return new ListIterator<>();
  }

  public static <T> ListIterator<T> forStartNode(final ListNode<T> startNode) {
    return new ListIterator<>(startNode);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public E next() throws NoSuchElementException {
    assertHasNext();

    final var element = nextNode.getStoredElement();

    if (nextNode.hasNextNode()) {
      nextNode = nextNode.getStoredNextNode();
    } else {
      nextNode = null;
    }

    return element;
  }

  private void assertHasNext() throws NoSuchElementException {
    if (nextNode == null) {
      throw new NoSuchElementException("The current ListIterator does not have a next element.");
    }
  }
}
