//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Iterator;
import java.util.NoSuchElementException;

//class
public final class ListIterator<E> implements Iterator<E> {

  //static method
  public static <E2> ListIterator<E2> forEmptyList() {
    return new ListIterator<>();
  }

  //static method
  public static <E2> ListIterator<E2> forStartNode(final ListNode<E2> startNode) {
    return new ListIterator<>(startNode);
  }

  //optional attribute
  private ListNode<E> nextNode;

  //constructor
  private ListIterator() {
  }

  //constructor
  private ListIterator(final ListNode<E> startNode) {
    this.nextNode = startNode;
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

  //method
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw new NoSuchElementException("The current ListIterator does not have a next element.");
    }
  }

  //method
  private E nextWhenHasNext() {

    final var element = nextNode.getStoredElement();

    nextNode = nextNode.getStoredNextNodeOrNull();

    return element;
  }
}
