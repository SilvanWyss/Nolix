package ch.nolix.coretest.containertest.immutablelisttest;

import ch.nolix.core.container.immutablelist.ImmutableList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

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
