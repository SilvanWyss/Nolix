/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.containerview;

import ch.nolix.base.datastructure.filterextendediterableview.FilterExtendedIterableView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.basetest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
public final class FilterContainerViewTest extends ContainerTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(@SuppressWarnings("unchecked") final E... elements) {
    return FilterExtendedIterableView.forArrayAndSelector(elements, _ -> true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(final Class<E> type) {
    @SuppressWarnings("unchecked")
    final var emptyArray = (E[]) (new Object[0]);

    return FilterExtendedIterableView.forArrayAndSelector(emptyArray, _ -> true);
  }
}
