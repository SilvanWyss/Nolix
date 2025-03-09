package ch.nolix.core.container.containerview;

import ch.nolix.core.container.base.ContainerTest;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;

public final class FilterContainerViewTest extends ContainerTest {

  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    @SuppressWarnings("unchecked") final E... elements) {
    return FilterContainerView.forElementAndArrayAndSelector(element, elements, x -> true);
  }

  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return FilterContainerView.createEmpty();
  }
}
