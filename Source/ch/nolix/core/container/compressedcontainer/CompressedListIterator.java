//package declaration
package ch.nolix.core.container.compressedcontainer;

//Java imports
import java.util.NoSuchElementException;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.name.LowerCaseCatalogue;
import ch.nolix.coreapi.containerapi.baseapi.CopyableIterator;

//class
final class CompressedListIterator<E> implements CopyableIterator<E> {

  //optional attribute
  private CompressedListNode<E> nextNode;

  //optional attribute
  private int nextNodeIndex;

  //constructor
  private CompressedListIterator() {
    nextNode = null;
    nextNodeIndex = -1;
  }

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  private CompressedListIterator(final CompressedListNode<E> firstNode) {

    if (firstNode == null) {
      throw ArgumentIsNullException.forArgumentName("first node");
    }

    nextNode = firstNode;
    nextNodeIndex = 1;
  }

  //static method
  public static <E2> CompressedListIterator<E2> forCompressedListWithFirstNode(
      final CompressedListNode<E2> firstNode) {
    return new CompressedListIterator<>(firstNode);
  }

  //static method
  public static <E2> CompressedListIterator<E2> forEmptyCompressedList() {
    return new CompressedListIterator<>();
  }

  //method
  @Override
  public CopyableIterator<E> getCopy() {

    if (!hasNext()) {
      return forEmptyCompressedList();
    }

    return forCompressedListWithFirstNode(nextNode);
  }

  //method
  @Override
  public boolean hasNext() {
    return (nextNode != null);
  }

  @Override
  public E next() {

    assertHasNext();

    return nextWhenHasNext();
  }

  //method
  private void assertHasNext() throws NoSuchElementException {
    if (!hasNext()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, LowerCaseCatalogue.NEXT_ELEMENT)
          .toNoSuchElementException();
    }
  }

  //method
  private void moveForward() {
    if (nextNodeIndex < nextNode.getElementCount()) {
      nextNodeIndex++;
    } else {
      moveForwardCurrentNode();
    }
  }

  //method
  private void moveForwardCurrentNode() {
    if (nextNode.hasNextNode()) {

      nextNode = nextNode.getStoredNextNode();

      nextNodeIndex = 1;
    } else {

      nextNode = null;

      nextNodeIndex = -1;
    }
  }

  //method
  private E nextWhenHasNext() {

    final var element = nextNode.getStoredElement();

    moveForward();

    return element;
  }
}
