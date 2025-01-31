package ch.nolix.core.container.containerview;

import ch.nolix.core.container.base.ContainerTest;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

final class IterableViewTest extends ContainerTest {

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return IterableView.forIterable(LinkedList.withElement(element, elements));
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new IterableView<>();
  }
}
