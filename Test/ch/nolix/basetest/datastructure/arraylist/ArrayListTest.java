/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.arraylist;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.NegativeArgumentException;
import ch.nolix.basetest.datastructure.extendediterable.ExtendedIterableTest;

/**
 * @author Silvan Wyss
 */
final class ArrayListTest extends ExtendedIterableTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(@SuppressWarnings("unchecked") final E... elements) {
    return ArrayList.withElements(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(final Class<E> type) {
    return ArrayList.createEmpty();
  }

  @Test
  void testCase_addAtEnd_whenHasAvailableCapacity() {
    // setup
    final var elements = new String[] {};
    final var testUnit = ArrayList.withInitialCapacity(10);

   // execute
    testUnit.addAtEnd(elements);

   // verify
    expect(testUnit).containsAll(elements);
  }

  @Test
  void testCase_addAtEnd_whenDoesNotHaveAvailableCapacity() {
    // setup
    final var elements = new String[] { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };
    final var testUnit = ArrayList.withInitialCapacity(5);

   // execute
    testUnit.addAtEnd(elements);

   // verify
    expect(testUnit).containsAll(elements);
  }

  @Test
  void testCase_clear_whenIsEmpty() {
    // setup
    final ArrayList<String> testUnit = ArrayList.createEmpty();

   // execute
    testUnit.clear();

   // verify
    expect(testUnit).isEmpty();
  }

  @Test
  void testCase_clear_whenContainsAny() {
    // setup
    final var testUnit = ArrayList.withElements("antelope", "baboon", "elephant", "lion", "rhino", "zebra");

   // execute
    testUnit.clear();

   // verify
    expect(testUnit).isEmpty();
  }

  @Test
  void testCase_getCopy() {
    // setup
    final var testUnit = ArrayList.withElements("antelope", "baboon", "elephant", "lion", "rhino", "zebra");

   // execute
    final var result = testUnit.getCopy();

   // verify
    expect(result).containsExactlyInSameOrder(testUnit);
  }

  @Test
  void testCase_isMaterialized() {
    // setup
    final var testUnit = ArrayList.createEmpty();

   // execute
    final var result = testUnit.isMaterialized();

   // verify
    expect(result).isTrue();
  }

  @Test
  void testCase_withElemens() {
    // setup
    final var elements = new String[] { "antelope", "baboon", "elephant", "lion", "rhino", "zebra" };

   // execute
    final var result = ArrayList.withElements(elements);

   // verify
    expect(result).containsExactlyInSameOrder(elements);
  }

  @Test
  void testCase_withInitialCapacity() {
   // execute
    final var result = ArrayList.withInitialCapacity(10);

   // verify
    expect(result).isEmpty();
  }

  @Test
  void testCase_withInitialCapacity_whenTheGivenInitialCapacityIsNegative() {
   // execute & verification
    expectRunning(() -> ArrayList.withInitialCapacity(-1))
      .throwsException()
      .ofType(NegativeArgumentException.class)
      .withMessage("The given initial capacity '-1' is negative.");
  }
}
