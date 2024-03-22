//package declaration
package ch.nolix.coretest.containertest.arraylisttest;

//own imports
import ch.nolix.core.container.arraylist.ArrayList;
import ch.nolix.coreapi.containerapi.baseapi.IContainer;
import ch.nolix.coretest.containertest.basetest.ContainerTest;

//class
public final class ArrayListTest extends ContainerTest {

  //method
  @Override
  protected <E> IContainer<E> createContainerWithElements(
    final E element,
    @SuppressWarnings("unchecked") final E... elements) {
    return ArrayList.withElement(element, elements);
  }

  //method
  @Override
  protected <E> IContainer<E> createEmptyContainerForType(final Class<E> type) {
    return new ArrayList<>();
  }
}
