/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.containerview;

import org.junit.jupiter.api.Test;

import ch.nolix.base.container.containerview.ArrayContainerView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.baseapi.errorcontrol.invalidargumentexception.ArgumentIsNullException;
import ch.nolix.basetest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class ArrayViewTest extends ContainerTest {
  @Test
  void testCase_forArray_whenTheGivenArrayIsNull() {
    //execution & verification
    expectRunning(() -> ArrayContainerView
      .forArray(null)).throwsException()
      .ofType(ArgumentIsNullException.class)
      .withMessage("The given array is null.");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return ArrayContainerView.forArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(Class<E> type) {
    return ArrayContainerView.createEmpty();
  }
}
