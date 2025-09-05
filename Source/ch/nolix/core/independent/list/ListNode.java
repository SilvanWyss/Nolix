package ch.nolix.core.independent.list;

import java.util.NoSuchElementException;

final class ListNode<E> {
  private final E element;

  private ListNode<E> nextNode;

  public ListNode(final E element) {
    if (element == null) {
      throw new IllegalArgumentException("The given element is null.");
    }

    this.element = element;
  }

  public boolean contains(final E element) {
    return (this.element == element);
  }

  public E getStoredElement() {
    return element;
  }

  public ListNode<E> getStoredNextNode() {
    assertHasNextNode();

    return nextNode;
  }

  public boolean hasNextNode() {
    return (nextNode != null);
  }

  public void removeNextNode() {
    nextNode = null;
  }

  public void setNextNode(final ListNode<E> nextNode) {
    if (nextNode == null) {
      throw new IllegalArgumentException("The given next node is null.");
    }

    this.nextNode = nextNode;
  }

  private void assertHasNextNode() {
    if (!hasNextNode()) {
      throw new NoSuchElementException("The current ListIterator does not have a next node.");
    }
  }
}
