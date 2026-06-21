/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.containerview;

import org.junit.jupiter.api.Test;

import ch.nolix.base.datastructure.extendediterableview.MultiContainerView;
import ch.nolix.base.datastructure.immutablelist.ImmutableList;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.basetest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class MultiContainerViewTest extends ContainerTest {
  @Test
  void testCase_forArray() {
    //setup
    final var array1 = new String[] { "x", "xx" };
    final var array2 = new String[] { "y", "yy" };
    final var array3 = new String[] { "z", "zz" };

    //execution
    final var result = MultiContainerView.forArrays(array1, array2, array3);

    //verification
    expect(result).containsExactlyInSameOrder("x", "xx", "y", "yy", "z", "zz");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(
    final @SuppressWarnings("unchecked") E... elements) {
    final var container = ImmutableList.withElements(elements);

    return MultiContainerView.forIterables(container);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(Class<E> type) {
    return MultiContainerView.forEmpty();
  }
}
