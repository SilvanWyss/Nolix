package ch.nolix.coretest.container.containerview;

import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.core.container.containerview.IterableContainerView;
import ch.nolix.coreapi.container.base.IContainer;
import ch.nolix.coretest.container.base.ContainerTest;

final class IterableViewTest extends ContainerTest {
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return IterableContainerView.forIterable(ArrayList.withElement(element, elements));
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new IterableContainerView<>();
  }
}
