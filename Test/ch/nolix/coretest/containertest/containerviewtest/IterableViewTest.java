package ch.nolix.coretest.containertest.containerviewtest;

import ch.nolix.core.container.containerview.IterableView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

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
