/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.linkedlist.linkedlist;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.linkedlist.LinkedList;
import ch.nolix.base.foundation.util.VoidObject;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.datastructure.list.ILinkedList;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentDoesNotContainElementException;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.basetest.datastructure.extendediterable.ExtendedIterableTest;

/**
 * @author Silvan Wyss
 */
final class LinkedListTest extends ExtendedIterableTest {
  @Test
  void testCase_addAtBegin_whenIsEmpty() {
    // setup
    final var elephant = "elephant";
    final var testUnit = LinkedList.createEmpty();

    // execute
    testUnit.addAtBegin(elephant);

    // verify
    expect(testUnit).containsExactlyInSameOrder(elephant);
  }

  @Test
  void testCase_addAtBegin_whenContainsSeveralElements() {
    // setup
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(lion, rhino, zebra);

    // execute
    testUnit.addAtBegin(elephant);

    // verify
    expect(testUnit).containsExactlyInSameOrder(elephant, lion, rhino, zebra);
  }

  @Test
  void testCase_addAtBegin_whenContainsSeveralElementsAndServeralElementsAreGiven() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(lion, rhino, zebra);

    // execute
    testUnit.addAtBegin(antelope, baboon, elephant);

    // verify
    expect(testUnit).containsExactlyInSameOrder(antelope, baboon, elephant, lion, rhino, zebra);
  }

  @Test
  void testCase_addAtBegin_whenTheGivenElementIsNull() {
    // setup
    final String element = null;
    final var testUnit = LinkedList.createEmpty();

    // execute & verify
    expectRunning(() -> testUnit.addAtBegin(element))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given element is null.");
  }

  @Test
  void testCase_addAtBegin_forArray_whenTheGivenArrayIsNull() {
    // setup
    final String[] array = null;
    final LinkedList<String> testUnit = LinkedList.createEmpty();

    // execute & verify
    expectRunning(() -> testUnit.addAtBegin(array)).throwsException();
  }

  @Test
  void testCase_addAtBegin_forArray_whenContainsSeveralElementsAndServeralElementsAreGiven() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var array = new String[] { antelope, baboon, elephant };
    final var testUnit = LinkedList.withElement(lion, rhino, zebra);

    // execute
    testUnit.addAtBegin(array);

    // verify
    expect(testUnit).containsExactlyInSameOrder(antelope, baboon, elephant, lion, rhino, zebra);
  }

  @Test
  void testCase_addAtBegin_forIterable() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(lion, rhino, zebra);
    final var list = LinkedList.withElement(antelope, baboon, elephant);

    // execute
    testUnit.addAtBegin(list);

    // verify
    expect(testUnit).containsExactlyInSameOrder(antelope, baboon, elephant, lion, rhino, zebra);
  }

  @Test
  void testCase_addAtBegin_forIterable_whenIsEmpty() {
    // setup
    final var elephant = "elephant";
    final var lion = "lion";
    final ILinkedList<String> testUnit = LinkedList.createEmpty();
    final var list = LinkedList.withElement(elephant, lion);

    // execute
    testUnit.addAtBegin(list);

    // verify
    expect(testUnit).containsExactlyInSameOrder(elephant, lion);
  }

  @Test
  void testCase_addAtBegin_forIterable_whenTheGivenListIsEmpty() {
    // setup
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var testUnit = LinkedList.withElement(rhino, zebra);
    final ILinkedList<String> list = LinkedList.createEmpty();

    // execute
    testUnit.addAtBegin(list);

    // verify
    expect(testUnit).containsExactlyInSameOrder(rhino, zebra);
  }

  @Test
  void testCase_addAtBegin_forIterable_whenTheGivenElementIsNull() {
    // setup
    final var testUnit = LinkedList.createEmpty();
    final Iterable<String> element = null;

    // execute & verify
    expectRunning(() -> testUnit.addAtBegin(element))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given elements is null.");
  }

  @Test
  void testCase_addAtEnd_whenTheGivenElementIsNull() {
    // setup
    final var testUnit = LinkedList.createEmpty();
    final String element = null;

    // execute & verify
    expectRunning(() -> testUnit.addAtEnd(element))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given element is null.");
  }

  @Test
  void testCase_clear_whenIsEmpty() {
    // setup
    final var testUnit = LinkedList.createEmpty();

    // execute
    testUnit.clear();

    // verify
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_clear_whenContainsAny() {
    // setup
    final var testUnit = LinkedList.withElement("x", "xx", "xxx", "xxxx", "xxxxx", "xxxxxx");

    // execute
    testUnit.clear();

    // verify
    expect(testUnit.isEmpty()).isTrue();
  }

  @Test
  void testCase_fromArray_whenTheGivenArrayIsNull() {
    // execute & verify
    expectRunning(() -> LinkedList.fromArray(null))
      .throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  @Test
  void testCase_fromArray_whenTheGivenArrayIsEmpty() {
    // setup
    final var array = new String[0];

    // execute
    final var result = LinkedList.fromArray(array);

    // verify
    expect(result.isEmpty()).isTrue();
  }

  @Test
  void testCase_fromArray_whenTheGivenArrayContains1Element() {
    // setup
    final var elephant = "elephant";
    final var array = new String[] { elephant };

    // execute
    final var result = LinkedList.fromArray(array);

    // verify
    expect(result).containsExactlyInSameOrder(elephant);
  }

  @Test
  void testCase_fromArray_whenTheGivenArrayContainsSeveralElements() {
    // setup
    final var antelope = "antelope";
    final var baboon = "baboon";
    final var elephant = "elephant";
    final var lion = "lion";
    final var rhino = "rhino";
    final var zebra = "zebra";
    final var array = new String[] { antelope, baboon, elephant, lion, rhino, zebra };

    // execute
    final var result = LinkedList.fromArray(array);

    // verify
    expect(result).containsExactlyInSameOrder(antelope, baboon, elephant, lion, rhino, zebra);
  }

  @Test
  void testCase_fromArray_whenTheGivenArrayContainsANullElement() {
    // setup
    final var array = new String[] { "lephant", "lion", null, "zebra" };

    // execute & verify
    expectRunning(() -> LinkedList.fromArray(array))
      .throwsException()
      .ofType(ArgumentIsNullException.class);
  }

  @Test
  void testCase_removeFirstOccurrenceOf_whenDoesNotContainTheGivenElement() {
    // setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    // execute
    testUnit.removeFirstOccurrenceOf(element5);

    // verify
    expect(testUnit).containsExactly(element1, element2, element3, element4);
  }

  @Test
  void testCase_removeFirstOccurrenceOf_whenContainsTheGivenElement() {
    // setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    // execute
    testUnit.removeFirstOccurrenceOf(element3);

    // verify
    expect(testUnit).containsExactly(element1, element2, element4);
  }

  @Test
  void testCase_removeStrictlyFirstOccurrenceOf_whenDoesNotContainTheGivenElement() {
    // setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var element5 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    // execute & verify
    expectRunning(() -> testUnit.removeStrictlyFirstOccurrenceOf(element5))
      .throwsException()
      .ofType(ArgumentDoesNotContainElementException.class);
  }

  @Test
  void testCase_removeStrictlyFirstOccurrenceOf_whenContainsTheGivenElement() {
    // setup
    final var element1 = new VoidObject();
    final var element2 = new VoidObject();
    final var element3 = new VoidObject();
    final var element4 = new VoidObject();
    final var testUnit = LinkedList.withElement(element1, element2, element3, element4);

    // execute
    testUnit.removeStrictlyFirstOccurrenceOf(element3);

    // verify
    expect(testUnit).containsExactly(element1, element2, element4);
  }

  @Test
  void testCase_toString() {
    // setup
    final var testUnit = LinkedList.withElement("elephant", "jaguar", "lion", "python", "shark", "zebra");

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("elephant,jaguar,lion,python,shark,zebra");
  }

  @Test
  void testCase_toString_whenIsEmpty() {
    // setup
    final var testUnit = LinkedList.createEmpty();

    // execute
    final var result = testUnit.toString();

    // verify
    expect(result).isEqualTo("");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return LinkedList.fromArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(final Class<E> type) {
    return LinkedList.createEmpty();
  }
}
