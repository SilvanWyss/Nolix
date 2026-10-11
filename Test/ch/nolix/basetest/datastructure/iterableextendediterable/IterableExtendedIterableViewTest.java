/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.datastructure.iterableextendediterable;

import ch.nolix.base.datastructure.arraylist.ArrayList;
import ch.nolix.base.datastructure.iterableextendediterableview.IterableExtendedIterableView;
import ch.nolix.baseapi.datastructure.extendediterable.ExtendedIterable;
import ch.nolix.basetest.datastructure.extendediterable.ExtendedIterableTest;

/**
 * @author Silvan Wyss
 */
final class IterableExtendedIterableViewTest extends ExtendedIterableTest {
  /**
   * {@inheritDoc}
   */
  @Override
  @SuppressWarnings("unchecked")
  protected <E> ExtendedIterable<E> createContainerWithElements(E... elements) {
    return IterableExtendedIterableView.forIterable(ArrayList.withElements(elements));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> ExtendedIterable<E> createEmptyContainerForType(Class<E> type) {
    return new IterableExtendedIterableView<>();
  }
}
