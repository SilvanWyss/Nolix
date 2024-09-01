//package declaration
package ch.nolix.core.container.linkedlist;

//Java imports
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.coreapi.containerapi.iteratorapi.CopyableIterator;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
/**
 * @author Silvan Wyss
 * @version 2016-01-01
 * @param <E> is the type of the elements of a {@link LinkedListIterator}.
 */
final class LinkedListIterator<E> implements CopyableIterator<E> {

  //optional attribute
  private LinkedListNode<E> nextNode;

  //constructor
  /**
   * Creates a new {@link LinkedListIterator} with the given nullableFirstNode.
   * The given nullableFirstNode can be null.
   * 
   * @param nullableFirstNode
   */
  private LinkedListIterator(final LinkedListNode<E> nullableFirstNode) {
    nextNode = nullableFirstNode;
  }

  //static method
  public static <E2> LinkedListIterator<E2> withNullableFirstNode(final LinkedListNode<E2> nullableFirstNode) {
    return new LinkedListIterator<>(nullableFirstNode);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public CopyableIterator<E> getCopy() {
    return withNullableFirstNode(nextNode);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

  //method
  /**
   * {@inheritDoc}
   */
  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw //
      ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseVariableCatalogue.NEXT_ELEMENT)
        .toNoSuchElementException();
    }
  }

  //method
  private E nextWhenHasNext() {

    final var element = nextNode.getElement();

    if (nextNode.hasNextNode()) {
      nextNode = nextNode.getNextNode();
    } else {
      nextNode = null;
    }

    return element;
  }
}
