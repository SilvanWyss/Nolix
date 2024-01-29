//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.NoSuchElementException;

//class
final class ListNode<E> {

  //attribute
  private final E element;

  //optional attribute
  private ListNode<E> nextNode;

  //constructor
  public ListNode(final E element) {

    if (element == null) {
      throw new IllegalArgumentException("The given element is null.");
    }

    this.element = element;
  }

  //method
  public boolean contains(final E element) {
    return (this.element == element);
  }

  //method
  public E getStoredElement() {
    return element;
  }

  //method
  public ListNode<E> getStoredNextNode() {

    assertHasNextNode();

    return nextNode;
  }

  //method
  public boolean hasNextNode() {
    return (nextNode != null);
  }

  //method
  public void removeNextNode() {
    nextNode = null;
  }

  //method
  public void setNextNode(final ListNode<E> nextNode) {

    if (nextNode == null) {
      throw new IllegalArgumentException("The given next node is null.");
    }

    this.nextNode = nextNode;
  }

  //method
  private void assertHasNextNode() {
    if (!hasNextNode()) {
      throw new NoSuchElementException("The current ListIterator does not have a next node.");
    }
  }
}
