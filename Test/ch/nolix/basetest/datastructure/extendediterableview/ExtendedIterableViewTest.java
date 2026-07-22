/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.extendediterableview;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.extendediterableview.ExtendedIterableView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.basetest.datastructure.extendediterable.ExtendedIterableTest;

/**
 * @author Silvan Wyss
 */
final class ExtendedIterableViewTest extends ExtendedIterableTest {
  @Test
  void testCase_containsEqualing() {
    // setup
    final String[] array1 = { "apple", "banana", "cerish" };
    final String[] array2 = { "antelope", "baboon", "lion" };
    final String[] array3 = { "flower", "tree", "palm" };

   // execute
    final var testUnit = ExtendedIterableView.forArrays(array1, array2, array3);

   // execute
    expect(testUnit.containsEqual("apple")).isTrue();
    expect(testUnit.containsEqual("banana")).isTrue();
    expect(testUnit.containsEqual("cerish")).isTrue();
    expect(testUnit.containsEqual("antelope")).isTrue();
    expect(testUnit.containsEqual("baboon")).isTrue();
    expect(testUnit.containsEqual("lion")).isTrue();
    expect(testUnit.containsEqual("flower")).isTrue();
    expect(testUnit.containsEqual("tree")).isTrue();
    expect(testUnit.containsEqual("palm")).isTrue();
    expect(testUnit.containsEqual("jupiter")).isFalse();
    expect(testUnit.containsEqual("saturn")).isFalse();
    expect(testUnit.containsEqual("uranus")).isFalse();
  }

  @Test
  void testCase_getStoredAtOneBasedIndex() {
    // setup
    final String[] array1 = { "apple", "banana", "cerish" };
    final String[] array2 = { "antelope", "baboon", "elephant" };
    final String[] array3 = { "flower", "tree", "palm" };

   // execute
    final var testUnit = ExtendedIterableView.forArrays(array1, array2, array3);

   // verify
    expect(testUnit.getStoredAtOneBasedIndex(1)).isEqualTo("apple");
    expect(testUnit.getStoredAtOneBasedIndex(2)).isEqualTo("banana");
    expect(testUnit.getStoredAtOneBasedIndex(3)).isEqualTo("cerish");
    expect(testUnit.getStoredAtOneBasedIndex(4)).isEqualTo("antelope");
    expect(testUnit.getStoredAtOneBasedIndex(5)).isEqualTo("baboon");
    expect(testUnit.getStoredAtOneBasedIndex(6)).isEqualTo("elephant");
    expect(testUnit.getStoredAtOneBasedIndex(7)).isEqualTo("flower");
    expect(testUnit.getStoredAtOneBasedIndex(8)).isEqualTo("tree");
    expect(testUnit.getStoredAtOneBasedIndex(9)).isEqualTo("palm");
  }

  @Test
  void testCase_getStoredSelected() {
    // setup
    final String[] array1 = { "A", "AA", "AAA" };
    final String[] array2 = { "B", "BB", "BBB" };
    final String[] array3 = { "C", "CC", "CCC" };

   // execute
    final var testUnit = ExtendedIterableView.forArrays(array1, array2, array3);

   // verify
    expect(testUnit.getStoredSelected(s -> s.length() == 1).toString()).isEqualTo("A,B,C");
    expect(testUnit.getStoredSelected(s -> s.length() == 2).toString()).isEqualTo("AA,BB,CC");
    expect(testUnit.getStoredSelected(s -> s.length() == 3).toString()).isEqualTo("AAA,BBB,CCC");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return ExtendedIterableView.forArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(final Class<E> type) {
    return ExtendedIterableView.createEmpty();
  }
}
