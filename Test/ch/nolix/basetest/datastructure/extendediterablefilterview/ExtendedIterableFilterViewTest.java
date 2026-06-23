/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.extendediterablefilterview;

import ch.nolix.base.datastructure.extendediterablefilterview.ExtendedIterableFilterView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.basetest.datastructure.extendediterable.ExtendedIterableTest;

/**
 * @author Silvan Wyss
 */
public final class ExtendedIterableFilterViewTest extends ExtendedIterableTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createContainerWithElements(@SuppressWarnings("unchecked") final E... elements) {
    return ExtendedIterableFilterView.forArrayAndSelector(elements, _ -> true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(final Class<E> type) {
    @SuppressWarnings("unchecked")
    final var emptyArray = (E[]) (new Object[0]);

    return ExtendedIterableFilterView.forArrayAndSelector(emptyArray, _ -> true);
  }
}
