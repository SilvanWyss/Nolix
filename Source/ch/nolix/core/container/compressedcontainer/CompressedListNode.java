//package declaration
package ch.nolix.core.container.compressedcontainer;

//own imports
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotHaveAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentHasAttributeException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.errorcontrol.invalidargumentexception.NonPositiveArgumentException;
import ch.nolix.coreapi.programatomapi.variableapi.LowerCaseVariableCatalogue;

//class
final class CompressedListNode<E> {

  //attribute
  private final E element;

  //attribute
  private int elementCount;

  //optional attribute
  private CompressedListNode<E> nextNode;

  //constructor
  //For a better performance, this implementation does not use all comfortable
  //methods.
  private CompressedListNode(final E element, final int elementCount) {

    if (element == null) {
      throw ArgumentIsNullException.forArgumentName(LowerCaseVariableCatalogue.ELEMENT);
    }

    if (elementCount < 1) {
      throw //
      NonPositiveArgumentException.forArgumentNameAndArgument(LowerCaseVariableCatalogue.ELEMENT_COUNT, elementCount);
    }

    this.element = element;
    this.elementCount = elementCount;
  }

  //static method
  public static <E2> CompressedListNode<E2> forElement(final E2 element) {
    return new CompressedListNode<>(element, 1);
  }

  //method
  public int getElementCount() {
    return elementCount;
  }

  //method
  public E getStoredElement() {
    return element;
  }

  //method
  public CompressedListNode<E> getStoredNextNode() {

    assertHasNextNode();

    return nextNode;
  }

  //method
  public boolean hasNextNode() {
    return (nextNode != null);
  }

  //method
  public void incrementElementCount() {
    elementCount++;
  }

  //method
  public void setNextNode(final CompressedListNode<E> nextNode) {

    assertDoesNotHaveNextNode();

    this.nextNode = nextNode;
  }

  //method
  private void assertDoesNotHaveNextNode() {
    if (hasNextNode()) {
      throw ArgumentHasAttributeException.forArgumentAndAttributeName(this, "next node");
    }
  }

  //method
  private void assertHasNextNode() {
    if (!hasNextNode()) {
      throw ArgumentDoesNotHaveAttributeException.forArgumentAndAttributeName(this, "next node");
    }
  }
}
