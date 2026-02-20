/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.arraylist;

import ch.nolix.base.container.arraylist.ArrayList;
import ch.nolix.baseapi.container.iterator.CopyableIterator;
import ch.nolix.basetest.container.base.CopyableIteratorTest;

/**
 * @author Silvan Wyss
 */
final class ArrayListIteratorTest extends CopyableIteratorTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> CopyableIterator<E> createIteratorForEmptyContainerForType(final Class<E> type) {
    final ArrayList<E> arrayList = ArrayList.createEmpty();

    return arrayList.iterator();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> CopyableIterator<E> createIteratorForContainerWithElements(
    final @SuppressWarnings("unchecked") E... elements) {
    final var arrayList = ArrayList.withElements(elements);

    return arrayList.iterator();
  }
}
