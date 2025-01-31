package ch.nolix.core.container.immutablelist;

import ch.nolix.core.container.base.ContainerTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class ImmutableListTest extends ContainerTest {

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return ImmutableList.withElement(element, elements);
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return ImmutableList.createEmpty();
  }
}
