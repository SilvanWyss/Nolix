//package declaration
package ch.nolix.core.independent.container;

//Java imports
import java.util.Iterator;

//class
public final class List<E> implements Iterable<E> {

  //attribute
  private int elementCount;

  //optional attribute
  private ListNode<E> beginNode;

  //optional attribute
  private ListNode<E> endNode;

  //constructor
  public List() {
  }

  //constructor
  public List(final E element) {
    addAtBegin(element);
  }

  //constructor
  public List(final E[] elements) {
    for (final var e : elements) {
      addAtBegin(e);
    }
  }

  //static method
  public static String[] createArrayFromList(final List<String> list) {

    final var array = new String[list.getElementCount()];

    var index = 0;
    for (final var ail : list) {
      array[index] = ail;
      index++;
    }

    return array;
  }

  //static method
  public static <E2> List<E2> withElements(final Iterable<E2> elements) {

    final var list = new List<E2>();

    for (final var e : elements) {
      list.addAtEnd(e);
    }

    return list;
  }

  //method
  public void addAtBegin(final E element) {

    final ListNode<E> node = new ListNode<>(element);

    if (isEmpty()) {
      beginNode = node;
      endNode = node;
    } else {
      node.setNextNode(beginNode);
      beginNode = node;
    }

    elementCount++;
  }

  //method
  public void addAtEnd(final E element) {

    final var node = new ListNode<>(element);

    if (isEmpty()) {
      beginNode = node;
      endNode = node;
    } else {
      endNode.setNextNode(node);
      endNode = node;
    }

    elementCount++;
  }

  //method
  public void clear() {
    beginNode = null;
    endNode = null;
    elementCount = 0;
  }

  //method
  public List<E> getCopy() {

    final var list = new List<E>();

    for (final var e : this) {
      list.addAtEnd(e);
    }

    return list;
  }

  //method
  public int getElementCount() {
    return elementCount;
  }

  //method
  public E getStoredFirst() {

    assertIsNotEmpty();

    return beginNode.getStoredElement();
  }

  //method
  public boolean isEmpty() {
    return (beginNode == null);
  }

  //method
  @Override
  public Iterator<E> iterator() {

    if (isEmpty()) {
      return ListIterator.forEmptyList();
    }

    return ListIterator.forStartNode(beginNode);
  }

  //method
  public void removeFirst() {

    assertIsNotEmpty();

    if (!beginNode.hasNextNode()) {
      beginNode = null;
      endNode = null;
    } else {
      beginNode = beginNode.getStoredNextNodeOrNull();
    }

    elementCount--;
  }

  //method
  public void removeFirstOccurrenceOf(final E element) {
    if (!isEmpty()) {
      removeFirstOccuranceOfWhenContainsAny(element);
    }
  }

  //method
  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw new IllegalStateException("The current List is empty.");
    }
  }

  //method
  private void removeFirstOccuranceOfWhenContainsAny(final E element) {
    if (beginNode.contains(element)) {
      removeFirst();
    } else {
      removeFirstOccuranceOfWhenIsNotFirst(element);
    }
  }

  //method
  private void removeFirstOccuranceOfWhenIsNotFirst(final E element) {
    var iteratorNode = beginNode;
    while (iteratorNode.hasNextNode()) {

      final var nextNode = iteratorNode.getStoredNextNodeOrNull();

      if (nextNode.contains(element)) {

        if (!nextNode.hasNextNode()) {
          iteratorNode.removeNextNode();
          endNode = iteratorNode;
        } else {
          iteratorNode.setNextNode(nextNode.getStoredNextNodeOrNull());
        }

        elementCount--;
        return;
      }

      iteratorNode = nextNode;
    }
  }
}
