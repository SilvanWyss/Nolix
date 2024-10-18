package ch.nolix.core.independent.container;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class ListIterator<E> implements Iterator<E> {

  private ListNode<E> nextNode;

  private ListIterator() {
  }

  private ListIterator(final ListNode<E> startNode) {
    this.nextNode = startNode;
  }

  public static <E2> ListIterator<E2> forEmptyList() {
    return new ListIterator<>();
  }

  public static <E2> ListIterator<E2> forStartNode(final ListNode<E2> startNode) {
    return new ListIterator<>(startNode);
  }

  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

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
