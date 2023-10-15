//package declaration
package ch.nolix.coretest.containertest.readcontainertest;

//own imports
import ch.nolix.core.container.linkedlist.LinkedList;
import ch.nolix.core.container.readcontainer.IterableReadContainer;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class IterableReadContainerTest extends ContainerTest {

  // method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
      final E element,
      final @SuppressWarnings("unchecked") E... elements) {
    return IterableReadContainer.forIterable(LinkedList.withElement(element, elements));
  }

  // method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(Class<E> type) {
    return new IterableReadContainer<>();
  }
}
