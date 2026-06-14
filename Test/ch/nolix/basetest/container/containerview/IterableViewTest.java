/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.containerview;

import ch.nolix.base.container.arraylist.ArrayList;
import ch.nolix.base.container.containerview.IterableContainerView;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.basetest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class IterableViewTest extends ContainerTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IWellOrderContainer<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return IterableContainerView.forIterable(ArrayList.withElements(elements));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IWellOrderContainer<E> createEmptyContainerForType(Class<E> type) {
    return new IterableContainerView<>();
  }
}
