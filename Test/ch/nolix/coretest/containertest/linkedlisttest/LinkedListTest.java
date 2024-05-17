//package declaration
package ch.nolix.coretest.containertest.linkedlisttest;

//JUnit imports
import org.junit.jupiter.api.Test;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.core.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.core.programatom.voidobject.VoidObject;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
final class LinkedListTest extends ContainerTest {

  //method
  @Test
  void testCase_addAtBegin_whenIsEmpty() {

    //setup
    final var elephant = "elephant";
    final var testUnit = new LinkedList<String>();

    //execution
    testUnit.addAtBegin(elephant);

    //verification
    expect(testUnit).containsExactlyInSameOrder(elephant);
  }

  //method
  @Test
  void testCase_addAtBegin_whenContainsSeveralElements() {

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
  @Test
  void testCase_addAtBegin_whenContainsSeveralElementsAndServeralElementsAreGiven() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(monkey, rhino, zebra);

    //execution
    testUnit.addAtBegin(antelope, elephant, lion);

    //verification
    expect(testUnit).containsExactlyInSameOrder(antelope, elephant, lion, monkey, rhino, zebra);
  }

  //method
  @Test
  void testCase_addAtBegin_whenTheGivenElementIsNull() {

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
  @Test
  void testCase_addAtBegin_forArray_whenTheGivenArrayIsNull() {

    //setup
    final String[] array = null;
    final var testUnit = new LinkedList<String>();

    //execution & verification
    expectRunning(() -> testUnit.addAtBegin(array)).throwsException();
  }

  //method
  @Test
  void testCase_forArray_whenContainsSeveralElementsAndServeralElementsAreGiven() {

    //setup
    final var antelope = "antelope";
    final var elephant = "elephant";
    final var lion = "lion";
    final var monkey = "monkey";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var array = new String[] { antelope, elephant, lion };
    final var testUnit = LinkedList.withElement(monkey, rhino, zebra);

    //execution
    testUnit.addAtBegin(array);

    //verification
    expect(testUnit).containsExactlyInSameOrder(antelope, elephant, lion, monkey, rhino, zebra);
  }

  //method
  @Test
  void testCase_addAtBegin_forIterable() {

    //setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(rhino, zebra);
    final var list = LinkedList.withElement(elephant, lion);

    //execution
    testUnit.addAtBegin(list);

    //verification
    expect(testUnit).containsExactlyInSameOrder(elephant, lion, rhino, zebra);
  }

  //method
  @Test
  void testCase_addAtBegin_forIterable_whenIsEmpty() {

    //setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var testUnit = new LinkedList<String>();
    final var list = LinkedList.withElement(elephant, lion);

    //execution
    testUnit.addAtBegin(list);

    //verification
    expect(testUnit).containsExactlyInSameOrder(elephant, lion);
  }

  //method
  @Test
  void testCase_addAtBegin_forIterable_whenTheGivenListIsEmpty() {

    //setup
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(rhino, zebra);
    final var list = new LinkedList<String>();

    //execution
    testUnit.addAtBegin(list);

    //verification
    expect(testUnit).containsExactlyInSameOrder(rhino, zebra);
  }

  //method
  @Test
  void testCase_addAtBegin_forIterable_whenTheGivenElementIsNull() {

    //setup
    final var testUnit = new LinkedList<String>();
    final Iterable<String> element = null;

    //execution & verification
    expectRunning(() -> testUnit.addAtBegin(element))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given elements is null.");
  }

  //method
  @Test
  void testCase_addAtEnd_whenTheGivenElementIsNull() {

    //setup
    final var testUnit = new LinkedList<String>();
    final String element = null;

    //execution & verification
    expectRunning(() -> testUnit.addAtEnd(element))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given element is null.");
  }

  //method
  @Test
  void testCase_clear_whenIsEmpty() {

    //setup
    final var testUnit = new LinkedList<String>();

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @Test
  void testCase_clear_whenContainsAny() {

    //setup
    final var testUnit = LinkedList.withElement("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    //execution
    testUnit.clear();

    //verification
    expect(testUnit.isEmpty());
  }

  //method
  @Test
  void testCase_fromArray_whenTheGivenArrayIsNull() {

    //execution & verification
    expectRunning(() -> LinkedList.fromArray(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  //method
  @Test
  void testCase_fromArray_whenTheGivenArrayIsEmpty() {

    //setup
    final var array = new String[0];

    //execution
    final var result = LinkedList.fromArray(array);

    //verification
    expect(result.isEmpty());
  }

  //method
  @Test
  void testCase_fromArray_whenTheGivenArrayContains1Element() {

    //setup
    final var elephant = "elephant";
    final var array = new String[] { elephant };

    //execution
    final var result = LinkedList.fromArray(array);

    //verification
    expect(result).containsExactlyInSameOrder(elephant);
  }

  //method
  @Test
  void testCase_fromArray_whenTheGivenArrayContainsSeveralElements() {

    //setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var array = new String[] { elephant, lion, rhino, zebra };

    //execution
    final var result = LinkedList.fromArray(array);

    //verification
    expect(result).containsExactlyInSameOrder(elephant, lion, rhino, zebra);
  }

  //method
  @Test
  void testCase_fromArray_whenTheGivenArrayContainsANullElement() {

    //setup
    final var array = new String[] { "lephant", "lion", null, "zebra" };

    //execution & verification
    expectRunning(() -> LinkedList.fromArray(array))
      .throwsException()
      .ofType(ArgumentIsNullException.class);
  }

  //method
  @Test
  void testCase_removeFirstOccurrenceOf_whenDoesNotContainTheGivenElement() {

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
  @Test
  void testCase_removeFirstOccurrenceOf_whenContainsTheGivenElement() {

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
  @Test
  void testCase_removeStrictlyFirstOccurrenceOf_whenDoesNotContainTheGivenElement() {

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
  @Test
  void testCase_removeStrictlyFirstOccurrenceOf_whenContainsTheGivenElement() {

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
  @Test
  void testCase_toString() {

    //setup
    final var testUnit = LinkedList.withElement("elephant", "jaguar", "lion", "python", "shark", "zebra");

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("elephant,jaguar,lion,python,shark,zebra");
  }

  //method
  @Test
  void testCase_toString_whenIsEmpty() {

    //setup
    final var testUnit = new LinkedList<>();

    //execution
    final var result = testUnit.toString();

    //verification
    expect(result).isEqualTo("");
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
