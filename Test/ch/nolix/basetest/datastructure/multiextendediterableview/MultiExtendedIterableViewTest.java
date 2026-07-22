/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.multiextendediterableview;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.base.datastructure.multiextendediterableview.MultiExtendedIterableView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.basetest.datastructure.extendediterable.ExtendedIterableTest;

/**
 * @author Silvan Wyss
 */
final class MultiExtendedIterableViewTest extends ExtendedIterableTest {
  @Test
  void testCase_forArray() {
    // setup
    final var array1 = new String[] { "x", "xx" };
    final var array2 = new String[] { "y", "yy" };
    final var array3 = new String[] { "z", "zz" };

   // execute
    final var result = MultiExtendedIterableView.forArrays(array1, array2, array3);

   // verify
    expect(result).containsExactlyInSameOrder("x", "xx", "y", "yy", "z", "zz");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(
    final @SuppressWarnings("unchecked") E... elements) {
    final var container = ImmutableList.withElements(elements);

    return MultiExtendedIterableView.forIterables(container);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(Class<E> type) {
    return MultiExtendedIterableView.forEmpty();
  }
}
