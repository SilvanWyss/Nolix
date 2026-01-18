package ch.nolix.coretest.container.containerview;

import ch.nolix.core.container.arraylist.FilterContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coretest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
public final class FilterContainerViewTest extends ContainerTest {
  @Override
  protected <E> IContainer<E> createContainerWithElements(@SuppressWarnings("unchecked") final E... elements) {
    return FilterContainerView.forArrayAndSelector(elements, _ -> true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return FilterContainerView.createEmpty();
  }
}
