//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//class
public final class ListIterator<E> implements Iterator<E> {

  //optional attribute
  private ListNode<E> nextNode;

  //constructor
  private ListIterator() {
  }

  //constructor
  private ListIterator(final ListNode<E> startNode) {
    this.nextNode = startNode;
  }

  //static method
  public static <E2> ListIterator<E2> forEmptyList() {
    return new ListIterator<>();
  }

  //static method
  public static <E2> ListIterator<E2> forStartNode(final ListNode<E2> startNode) {
    return new ListIterator<>(startNode);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

  //method
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

  //method
  private void assertHasNext() throws NoSuchElementException {
    if (nextNode == null) {
      throw new NoSuchElementException("The current ListIterator does not have a next element.");
    }
  }
}
