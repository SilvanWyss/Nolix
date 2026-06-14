/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.immutablelist;

import ch.nolix.base.container.immutablelist.ImmutableList;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.basetest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class ImmutableListTest extends ContainerTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IWellOrderContainer<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return ImmutableList.fromArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IWellOrderContainer<E> createEmptyContainerForType(final Class<E> type) {
    return ImmutableList.createEmpty();
  }
}
