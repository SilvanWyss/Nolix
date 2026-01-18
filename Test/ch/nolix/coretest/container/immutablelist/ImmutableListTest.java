package ch.nolix.coretest.container.immutablelist;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coretest.container.base.ContainerTest;

/**
 * @author Silvan Wyss
 */
final class ImmutableListTest extends ContainerTest {
  @Override
  protected <E> IContainer<E> createContainerWithElements(final @SuppressWarnings("unchecked") E... elements) {
    return ImmutableList.fromArray(elements);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return ImmutableList.createEmpty();
  }
}
