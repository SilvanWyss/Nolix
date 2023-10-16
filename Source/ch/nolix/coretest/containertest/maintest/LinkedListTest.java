//package declaration
package ch.nolix.coretest.containertest.maintest;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class LinkedListTest extends ContainerTest {

  //method
  @TestCase
  public void testCase_addAtBegin() {

    //setup
    final var testUnit = LinkedList.withElement("a1", "a2", "a3", "a4");
    final var list = LinkedList.withElement("b1", "b2");

    //execution
    testUnit.addAtBegin(list);

    //verification
    expect(testUnit.getElementCount()).isEqualTo(6);
    expect(testUnit.getStoredAt1BasedIndex(1)).isEqualTo("b1");
    expect(testUnit.getStoredAt1BasedIndex(2)).isEqualTo("b2");
    expect(testUnit.getStoredAt1BasedIndex(3)).isEqualTo("a1");
    expect(testUnit.getStoredAt1BasedIndex(4)).isEqualTo("a2");
    expect(testUnit.getStoredAt1BasedIndex(5)).isEqualTo("a3");
    expect(testUnit.getStoredAt1BasedIndex(6)).isEqualTo("a4");
  }

  //method
  @TestCase
  public void testCase_addAtBegin_whenGivenListIsEmpty() {

    //setup
    final var testUnit = LinkedList.withElement("a1", "a2", "a3", "a4");
    final var list = new LinkedList<String>();

    //execution
    testUnit.addAtBegin(list);

    //verification
    expect(testUnit.getElementCount()).isEqualTo(4);
    expect(testUnit.getStoredAt1BasedIndex(1)).isEqualTo("a1");
    expect(testUnit.getStoredAt1BasedIndex(2)).isEqualTo("a2");
    expect(testUnit.getStoredAt1BasedIndex(3)).isEqualTo("a3");
    expect(testUnit.getStoredAt1BasedIndex(4)).isEqualTo("a4");
  }

  //method
  @TestCase
  public void testCase_addAtBegin_whenIsEmpty() {

    //setup
    final var testUnit = new LinkedList<String>();
    final var list = LinkedList.withElement("b1", "b2");

    //execution
    testUnit.addAtBegin(list);

    //verification
    expect(testUnit.getElementCount()).isEqualTo(2);
    expect(testUnit.getStoredAt1BasedIndex(1)).isEqualTo("b1");
    expect(testUnit.getStoredAt1BasedIndex(2)).isEqualTo("b2");
  }

  //method
  @TestCase
  public void testCase_addAtBegin_whenTheGivenElementIsNull() {

    //setup
    final var testUnit = new LinkedList<>();
    final Object nullElement = null;

    //execution & verification
    expectRunning(() -> testUnit.addAtBegin(nullElement))
        .throwsException()
        .ofType(ArgumentIsNullException.class)
        .withMessage("The given element is null.");
  }

  //method
  @TestCase
  public void testCase_addAtEnd_whenTheGivenElementIsNull() {

    //setup
    final var testUnit = new LinkedList<>();
    final Object nullElement = null;

    //execution & verification
    expectRunning(() -> testUnit.addAtEnd(nullElement))
        .throwsException()
        .ofType(ArgumentIsNullException.class)
        .withMessage("The given element is null.");
  }

  //method
  @TestCase
  public void testCase_clear() {

    //setup
    final var testUnit = LinkedList.withElement("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @TestCase
  public void testCase_toString() {

    //setup
    final var testUnit = LinkedList.withElement("elephant", "jaguar", "lion", "python", "shark", "zebra");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("elephant,jaguar,lion,python,shark,zebra");
  }

  //method
  @TestCase
  public void testCase_toString_whenLikedListIsEmpty() {

    //setup
    final var testUnit = new LinkedList<>();

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEmpty();
  }

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
      final E element,
      final @SuppressWarnings("unchecked") E... elements) {
    return LinkedList.withElement(element, elements);
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return new LinkedList<>();
  }
}
