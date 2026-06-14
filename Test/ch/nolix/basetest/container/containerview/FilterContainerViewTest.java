/*
 * Copyright © by Silvan Wyss. All rights reserved.
 */
package ch.nolix.basetest.container.containerview;

import ch.nolix.base.container.arraylist.FilterContainerView;
import ch.nolix.baseapi.container.wellordercontainer.IWellOrderContainer;
import ch.nolix.basetest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
public final class FilterContainerViewTest extends ContainerTest {
  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IWellOrderContainer<E> createContainerWithElements(@SuppressWarnings("unchecked") final E... elements) {
    return FilterContainerView.forArrayAndSelector(elements, _ -> true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IWellOrderContainer<E> createEmptyContainerForType(final Class<E> type) {
    return FilterContainerView.createEmpty();
  }
}
