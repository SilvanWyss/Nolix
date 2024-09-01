//package declaration
package ch.nolix.coretest.containertest.containerviewtest;

//own imports
import ch.nolix.core.container.containerview.IterableView;
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
final class IterableViewTest extends ContainerTest {

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    final @SuppressWarnings("unchecked") E... elements) {
    return IterableView.forIterable(LinkedList.withElement(element, elements));
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new IterableView<>();
  }
}
