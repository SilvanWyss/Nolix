//package declaration
package ch.nolix.coretest.containertest.linkedlisttest;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.core.testing.basetest.TestCase;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class LinkedListTest extends ContainerTest {

  //method
  @TestCase
  public void testCase_addAtBegin_whenIsEmpty() {

    //setup
    final var elephant = "elephant";
    final var testUnit = new LinkedList<String>();

    //execution
    testUnit.addAtBegin(elephant);

    //verification
    expect(testUnit).containsExactlyInSameOrder(elephant);
  }

  //method
  @TestCase
  public void testCase_addAtBegin_whenContainsSeveralElements() {

    //setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(lion, rhino, zebra);

    //execution
    testUnit.addAtBegin(elephant);

    //verification
    expect(testUnit).containsExactlyInSameOrder(elephant, lion, rhino, zebra);
  }

  //method
  @TestCase
  public void testCase_addAtBegin_whenTheGivenElementIsNull() {

    //setup
    final String element = null;
    final var testUnit = new LinkedList<String>();

    //execution & verification
    expectRunning(() -> testUnit.addAtBegin(element))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given element is null.");
  }

  //method
  @TestCase
  public void testCase_addAtBegin_forIterable() {

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
  public void testCase_addAtBegin_forIterable_whenGivenListIsEmpty() {

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
  public void testCase_addAtBegin_forIterable_whenIsEmpty() {

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
  public void testCase_addAtBegin_forIterable_whenTheGivenElementIsNull() {

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
  public void testCase_clear_whenIsEmpty() {

    //setup
    final var testUnit = new LinkedList<String>();

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @TestCase
  public void testCase_clear_whenContainsAny() {

    //setup
    final var testUnit = LinkedList.withElement("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @TestCase
  public void testCase_fromArray_whenTheGivenArrayIsNull() {

    //execution & verification
    expectRunning(() -> LinkedList.fromArray(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  //method
  @TestCase
  public void testCase_fromArray_whenTheGivenArrayIsEmpty() {

    //setup
    final var array = new String[0];

    //execution
    final var result = LinkedList.fromArray(array);

    //verification
    expect(result.isEmpty());
  }

  //method
  @TestCase
  public void testCase_fromArray_whenTheGivenArrayContains1Element() {

    //setup
    final var elephant = "elephant";
    final var array = new String[] { elephant };

    //execution
    final var result = LinkedList.fromArray(array);

    //verification
    expect(result).containsExactlyInSameOrder(elephant);
  }

  //method
  @TestCase
  public void testCase_fromArray_whenTheGivenArrayContainsSeveralElements() {

    //setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var zebra = "zebra";
    final var array = new String[] { elephant, lion, zebra };

    //execution
    final var result = LinkedList.fromArray(array);

    //verification
    expect(result).containsExactlyInSameOrder(elephant, lion, zebra);
  }

  //method
  @TestCase
  public void testCase_fromArray_whenTheGivenArrayContainsANullElement() {

    //setup
    final var array = new String[] { "lephant", "lion", null, "zebra" };

    //execution & verification
    expectRunning(() -> LinkedList.fromArray(array))
      .throwsException()
      .ofType(ArgumentIsNullException.class);
  }

  //method
  @TestCase
  public void testCase_removeFirstOccurrenceOf_whenDoesNotContainTheGivenElement() {

    //setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    //execution
    testUnit.removeFirstOccurrenceOf(element5);

    //verification
    expect(testUnit).containsExactly(element1, element2, element3, element4);
  }

  //method
  @TestCase
  public void testCase_removeFirstOccurrenceOf_whenContainsTheGivenElement() {

    //setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    //execution
    testUnit.removeFirstOccurrenceOf(element3);

    //verification
    expect(testUnit).containsExactly(element1, element2, element4);
  }

  //method
  @TestCase
  public void testCase_removeStrictlyFirstOccurrenceOf_whenDoesNotContainTheGivenElement() {

    //setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    //execution & verification
    expectRunning(() -> testUnit.removeStrictlyFirstOccurrenceOf(element5))
      .throwsException()
      .ofType(ArgumentDoesNotContainElementException.class);
  }

  //method
  @TestCase
  public void testCase_removeStrictlyFirstOccurrenceOf_whenContainsTheGivenElement() {

    //setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    //execution
    testUnit.removeStrictlyFirstOccurrenceOf(element3);

    //verification
    expect(testUnit).containsExactly(element1, element2, element4);
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
