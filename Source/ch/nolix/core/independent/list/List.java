package ch.nolix.core.independent.list;

import java.util.Iterator;
import java.util.function.Function;

/**
 * @author Silvan Wyss
 */
public final class List<E> implements Iterable<E> {
  private int elementCount;

  private ListNode<E> beginNode;

  private ListNode<E> endNode;

  public List() {
  }

  public List(final E element) {
    addAtBegin(element);
  }

  public List(final E[] elements) {
    for (final var e : elements) {
      addAtBegin(e);
    }
  }

  public static String[] createArrayFromList(final List<String> list) {
    final var array = new String[list.getElementCount()];

    var index = 0;
    for (final var e : list) {
      array[index] = e;
      index++;
    }

    return array;
  }

  public static <T> List<T> withElements(final Iterable<T> elements) {
    final var list = new List<T>();

    for (final var e : elements) {
      list.addAtEnd(e);
    }

    return list;
  }

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

  public void clear() {
    beginNode = null;
    endNode = null;
    elementCount = 0;
  }

  public List<E> getCopy() {
    final var list = new List<E>();

    for (final var e : this) {
      list.addAtEnd(e);
    }

    return list;
  }

  public int getElementCount() {
    return elementCount;
  }

  public E getStoredFirst() {
    assertIsNotEmpty();

    return beginNode.getStoredElement();
  }

  public boolean isEmpty() {
    return (beginNode == null);
  }

  @Override
  public Iterator<E> iterator() {
    if (isEmpty()) {
      return ListIterator.forEmptyList();
    }

    return ListIterator.forStartNode(beginNode);
  }

  public void removeFirst() {
    assertIsNotEmpty();

    if (!beginNode.hasNextNode()) {
      beginNode = null;
      endNode = null;
    } else {
      beginNode = beginNode.getStoredNextNode();
    }

    elementCount--;
  }

  public void removeFirstOccurrenceOf(final E element) {
    if (!isEmpty()) {
      removeFirstOccuranceOfWhenContainsAny(element);
    }
  }

  public byte[] toByteArray(Function<E, Byte> byteMapper) {
    if (byteMapper == null) {
      throw new IllegalArgumentException("The given byteMapper is null.");
    }

    final var array = new byte[getElementCount()];
    var index = 0;

    for (final var e : this) {
      if (e == null) {
        array[index] = 0;
      } else {
        array[index] = byteMapper.apply(e);
      }

      index++;
    }

    return array;
  }

  private void assertIsNotEmpty() {
    if (isEmpty()) {
      throw new IllegalStateException("The current List is empty.");
    }
  }

  private void removeFirstOccuranceOfWhenContainsAny(final E element) {
    if (beginNode.contains(element)) {
      removeFirst();
    } else {
      removeFirstOccuranceOfWhenIsNotFirst(element);
    }
  }

  private void removeFirstOccuranceOfWhenIsNotFirst(final E element) {
    var iteratorNode = beginNode;
    while (iteratorNode.hasNextNode()) {
      final var nextNode = iteratorNode.getStoredNextNode();

      if (nextNode.contains(element)) {
        if (!nextNode.hasNextNode()) {
          iteratorNode.removeNextNode();
          endNode = iteratorNode;
        } else {
          iteratorNode.setNextNode(nextNode.getStoredNextNode());
        }

        elementCount--;
        return;
      }

      iteratorNode = nextNode;
    }
  }
}
